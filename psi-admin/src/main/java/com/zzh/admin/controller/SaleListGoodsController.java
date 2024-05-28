package com.zzh.admin.controller;


import com.zzh.admin.query.saleListGoodsQuery;
import com.zzh.admin.service.ISaleListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 销售单商品表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Controller
@RequestMapping("/saleListGoods")
public class SaleListGoodsController {


    @Resource
    private ISaleListGoodsService saleListGoodsService;

    /**
     * 根据条件查询所有商品
     *
     * @param saleListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery) {
        return saleListGoodsService.saleListGoodsList(saleListGoodsQuery);
    }

}
