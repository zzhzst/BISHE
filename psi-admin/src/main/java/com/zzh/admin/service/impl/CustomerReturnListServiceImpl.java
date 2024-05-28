package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.Customer;
import com.zzh.admin.pojo.CustomerReturnList;
import com.zzh.admin.mapper.CustomerReturnListMapper;
import com.zzh.admin.pojo.CustomerReturnListGoods;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.query.CustomerQuery;
import com.zzh.admin.query.CustomerReturnListQuery;
import com.zzh.admin.service.ICustomerReturnListGoodsService;
import com.zzh.admin.service.ICustomerReturnListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.DateUtil;
import com.zzh.admin.utils.PageResultUtil;
import com.zzh.admin.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Service
public class CustomerReturnListServiceImpl extends ServiceImpl<CustomerReturnListMapper, CustomerReturnList> implements ICustomerReturnListService {

    @Resource
    private IGoodsService goodsService;

    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;


    @Override
    public String getNextCustomerReturnNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("XT");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String customerReturnNumber = this.baseMapper.getNextCustomerReturnNumber();
            if (null != customerReturnNumber) {
                stringBuffer.append(StringUtil.formatCode(customerReturnNumber));
            } else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList) {
        AssertUtil.isTrue(!(this.save(customerReturnList)), "记录添加失败!");
        CustomerReturnList temp = this.getOne(new QueryWrapper<CustomerReturnList>().eq("id", customerReturnList.getId()));
        slgList.forEach(slg -> {
            slg.setCustomerReturnListId(temp.getId());
            Goods goods = goodsService.getById(slg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity() + slg.getNum());
            //重新计算库存总金额和库商品成本
            goods.setAmount(goods.getAmount() - slg.getPrice() * slg.getNum());
            goods.setAverage((goods.getAmount()) / (goods.getInventoryQuantity()));
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)), "记录添加失败!");
            AssertUtil.isTrue(!(customerReturnListGoodsService.save(slg)), "记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery) {
        IPage<CustomerReturnList> page = new Page<CustomerReturnList>(customerReturnListQuery.getPage(), customerReturnListQuery.getLimit());
        page = this.baseMapper.customerReturnList(page, customerReturnListQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }
}
