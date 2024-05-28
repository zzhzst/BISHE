package com.zzh.admin.service;

import com.zzh.admin.pojo.SaleListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.saleListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface ISaleListGoodsService extends IService<SaleListGoods> {

    /**
     * 根据商品id查询销售数量
     *
     * @param id
     * @return
     */
    public Integer getSaleTotalByGoodsId(Integer id);

    /**
     * 根据条件查询所有商品
     *
     * @param saleListGoodsQuery
     * @return
     */
    public Map<String, Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery);
}
