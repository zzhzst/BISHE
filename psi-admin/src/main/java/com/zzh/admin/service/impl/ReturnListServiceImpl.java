package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.pojo.ReturnList;
import com.zzh.admin.mapper.ReturnListMapper;
import com.zzh.admin.pojo.ReturnListGoods;
import com.zzh.admin.query.ReturnListQuery;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.service.IReturnListGoodsService;
import com.zzh.admin.service.IReturnListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.DateUtil;
import com.zzh.admin.utils.PageResultUtil;
import com.zzh.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货单表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Service
public class ReturnListServiceImpl extends ServiceImpl<ReturnListMapper, ReturnList> implements IReturnListService {

    @Resource
    private IReturnListGoodsService returnListGoodsService;

    @Resource
    private IGoodsService goodsService;

    @Override
    public String getNextReturnNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("TH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String returnNumber = this.baseMapper.getNextReturnNumber();
            if (null != returnNumber) {
                stringBuffer.append(StringUtil.formatCode(returnNumber));
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
    public void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList) {
        AssertUtil.isTrue(!(this.save(returnList)), "记录添加失败!");
        ReturnList temp = this.getOne(new QueryWrapper<ReturnList>().eq("id", returnList.getId()));
        rlgList.forEach(rlg -> {
            rlg.setReturnListId(temp.getId());

            Goods goods = goodsService.getById(rlg.getGoodsId());
            AssertUtil.isTrue(goods.getInventoryQuantity() - rlg.getNum() < 0, "退货数不允许大于库存数");

            //减少库存
            goods.setInventoryQuantity(goods.getInventoryQuantity() - rlg.getNum());
            //重新计算库存总金额和库商品成本
            goods.setAmount(goods.getAmount() + rlg.getPrice() * rlg.getNum());
            goods.setAverage((goods.getAmount()) / (goods.getInventoryQuantity()));
            goods.setState(2);
            goodsService.updateById(goods);

        });
        AssertUtil.isTrue(!(returnListGoodsService.saveBatch(rlgList)), "记录添加失败!");
    }

    @Override
    public Map<String, Object> returnList(ReturnListQuery returnListQuery) {
        IPage<ReturnList> page = new Page<ReturnList>(returnListQuery.getPage(), returnListQuery.getLimit());
        page = this.baseMapper.returnList(page, returnListQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteReturnList(Integer id) {
        AssertUtil.isTrue(!(returnListGoodsService.remove(new QueryWrapper<ReturnListGoods>().eq("return_list_id", id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)), "记录删除失败!");
    }
}
