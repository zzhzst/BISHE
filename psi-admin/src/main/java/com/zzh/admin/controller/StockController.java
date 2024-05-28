package com.zzh.admin.controller;


import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.query.GoodsQuery;
import com.zzh.admin.service.IGoodsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 期初库存控制器
 */
@Controller
@RequestMapping("stock")
public class StockController {

    @Resource
    private IGoodsService goodsService;

    /**
     * 期初库存主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('2060')")
    public String index() {
        return "stock/stock";
    }


    /**
     * 库存量为0 的商品记录
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listNoInventoryQuantity")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2060001')")
    public Map<String, Object> listNoInventoryQuantity(GoodsQuery goodsQuery) {
        //type = 1  查初库存为0
        goodsQuery.setType(1);
        return goodsService.goodsList(goodsQuery);
    }

    /**
     * 库存量大于0 的商品记录
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listHasInventoryQuantity")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2060003')")
    public Map<String, Object> listHasInventoryQuantity(GoodsQuery goodsQuery) {
        //type = 2  查初库存大于0
        goodsQuery.setType(2);
        return goodsService.goodsList(goodsQuery);
    }


    /**
     * 添加商品到仓库页面
     *
     * @param gid
     * @param model
     * @return
     */
    @RequestMapping("toUpdateGoodsInfoPage")
    @PreAuthorize("hasAnyAuthority('2060002')")
    public String toUpdateGoodsInfoPage(Integer gid, Model model) {
        model.addAttribute("goods", goodsService.getById(gid));
        return "stock/goods_update";
    }

    /**
     * 更新期初库存
     *
     * @param goods
     * @return
     */
    @RequestMapping("updateStock")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2060002')")
    public RespBean updateGoods(Goods goods) {
        goodsService.updateStock(goods);
        return RespBean.success("商品记录更新成功!");
    }


    /**
     * 删除期初库存
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteStock")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2060005')")
    public RespBean deleteStock(Integer id) {
        goodsService.deleteStock(id);
        return RespBean.success("商品记录删除成功!");
    }

}
