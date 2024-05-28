package com.zzh.admin.controller;


import com.zzh.admin.query.OverflowListGoodsQuery;
import com.zzh.admin.service.IOverflowListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 报溢单商品表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-20
 */
@Controller
@RequestMapping("/overflowListGoods")
public class OverflowListGoodsController {

    @Resource
    private IOverflowListGoodsService overflowListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overflowListGoodsList(OverflowListGoodsQuery overflowListGoodsQuery){
        return overflowListGoodsService.overflowListGoodsList(overflowListGoodsQuery);
    }

}
