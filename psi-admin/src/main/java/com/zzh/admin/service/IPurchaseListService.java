package com.zzh.admin.service;

import com.zzh.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.pojo.PurchaseListGoods;
import com.zzh.admin.query.PurchaseListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-16
 */
public interface IPurchaseListService extends IService<PurchaseList> {

    /**
     * 生成进货单号
     *
     * @return
     */
    public String getNextPurchaseNumber();

    /**
     * 保存单据
     *
     * @param purchaseList
     * @param plgList
     */
    public void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList);

    /**
     * 查询进货单
     *
     * @param purchaseListQuery
     * @return
     */
    public Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery);

    /**
     * 删除进货单
     *
     * @param id
     */
    public void deletePurchaseList(Integer id);

    /**
     * 供应商供货结算
     *
     * @param id
     */
    void updatePurchaseListState(Integer id);

    /**
     * 商品进货统计
     *
     * @param purchaseListQuery
     * @return
     */
    Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery);

    void updateCheck(Integer id, String checkState,String remarks);
}
