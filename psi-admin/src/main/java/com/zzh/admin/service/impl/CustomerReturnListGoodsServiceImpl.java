package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.CustomerReturnListGoods;
import com.zzh.admin.mapper.CustomerReturnListGoodsMapper;
import com.zzh.admin.query.CustomerReturnListGoodsQuery;
import com.zzh.admin.service.ICustomerReturnListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Service
public class CustomerReturnListGoodsServiceImpl extends ServiceImpl<CustomerReturnListGoodsMapper, CustomerReturnListGoods> implements ICustomerReturnListGoodsService {

    @Override
    public Integer getReturnTotalByGoodsId(Integer id) {
        CustomerReturnListGoods customerReturnListGoods =
                this.getOne(new QueryWrapper<CustomerReturnListGoods>().select("sum(num) as num").eq("goods_id", id));
        return null == customerReturnListGoods ? 0 : customerReturnListGoods.getNum();
    }

    @Override
    public Map<String, Object> customerReturnListGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery) {
        IPage<CustomerReturnListGoods> page = new Page<CustomerReturnListGoods>(customerReturnListGoodsQuery.getPage(), customerReturnListGoodsQuery.getLimit());
        QueryWrapper<CustomerReturnListGoods> queryWrapper = new QueryWrapper<CustomerReturnListGoods>();
        if (null != customerReturnListGoodsQuery.getCustomerReturnListId()) {
            queryWrapper.eq("customer_return_list_id", customerReturnListGoodsQuery.getCustomerReturnListId());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }
}
