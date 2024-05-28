package com.zzh.admin.controller;


import com.zzh.admin.query.PurchaseListGoodsQuery;
import com.zzh.admin.service.IPurchaseListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 进货单商品表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-16
 */
@Controller
@RequestMapping("/purchaseListGoods")
public class PurchaseListGoodsController {

    @Resource
    private IPurchaseListGoodsService purchaseListGoodsService;

    /**
     * 根据条件查询进货单商品数据
     * @param purchaseListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery) {
        return purchaseListGoodsService.purchaseListGoodsList(purchaseListGoodsQuery);
    }
}
