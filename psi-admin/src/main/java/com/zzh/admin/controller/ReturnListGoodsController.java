package com.zzh.admin.controller;


import com.zzh.admin.query.ReturnListGoodsQuery;
import com.zzh.admin.service.IReturnListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 退货单商品表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Controller
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {

    @Resource
    private IReturnListGoodsService returnListGoodsService;


    /**
     * 根据查询条件查询退货单商品
     * @param returnListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery){
        return returnListGoodsService.returnListGoodsList(returnListGoodsQuery);
    }
}
