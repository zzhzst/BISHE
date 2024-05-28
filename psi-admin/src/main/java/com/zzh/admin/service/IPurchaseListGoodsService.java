package com.zzh.admin.service;

import com.zzh.admin.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.PurchaseListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-16
 */
public interface IPurchaseListGoodsService extends IService<PurchaseListGoods> {

    /**
     * 根据查询条件查询进货单商品数据
     * @param purchaseListGoodsQuery
     * @return
     */
    public Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery);
}
