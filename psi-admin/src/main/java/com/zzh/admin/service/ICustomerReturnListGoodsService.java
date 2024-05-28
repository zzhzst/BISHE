package com.zzh.admin.service;

import com.zzh.admin.pojo.CustomerReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.CustomerReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface ICustomerReturnListGoodsService extends IService<CustomerReturnListGoods> {

    /**
     * 根据商品id查询退货数量
     *
     * @param id
     * @return
     */
    public Integer getReturnTotalByGoodsId(Integer id);

    /**
     * 根据查询条件查询退货商品
     *
     * @param customerReturnListGoodsQuery
     * @return
     */
    public Map<String, Object> customerReturnListGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery);
}
