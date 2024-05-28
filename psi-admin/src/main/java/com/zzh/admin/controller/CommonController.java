package com.zzh.admin.controller;

import com.zzh.admin.model.GoodsModel;
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
 * 公共的控制器
 */
@Controller
@RequestMapping("common")
public class CommonController {
    @Resource
    private IGoodsService goodsService;


    /**
     * 添加商品-选择商品页
     *
     * @return
     */
    @RequestMapping("toSelectGoodsPage")
    public String toSelectGoodsPage() {
        return "common/goods";
    }

    /**
     * 添加商品-商品信息添加页(单价、进货数量)
     *
     * @param gid
     * @param model
     * @return
     */
    @RequestMapping("toAddGoodsInfoPage")
    public String toGoodsInfoPage(Integer gid, Model model) {
        model.addAttribute("goods", goodsService.getGoodsInfoById(gid));
        return "common/goods_add_update";
    }


    /**
     * 修改商品-商品信息修改页(单价、进货数量)
     *
     * @param goodsModel
     * @param model
     * @return
     */
    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(GoodsModel goodsModel, Model model) {
        Goods goods = goodsService.getGoodsInfoById(goodsModel.getId());
        goodsModel.setCode(goods.getCode());
        goodsModel.setModel(goods.getModel());
        goodsModel.setName(goods.getName());
        goodsModel.setUnit(goods.getUnitName());
        goodsModel.setTypeId(goods.getTypeId());
        goodsModel.setTypeName(goods.getTypeName());
        goodsModel.setLastPurchasingPrice(goods.getLastPurchasingPrice());
        goodsModel.setInventoryQuantity(goods.getInventoryQuantity());
        model.addAttribute("goods", goodsModel);
        model.addAttribute("flag", 1);
        return "common/goods_add_update";
    }


    /**
     * 当前库存页
     *
     * @return
     */
    @RequestMapping("toGoodsStockPage")
    @PreAuthorize("hasAnyAuthority('5050')")
    public String toGoodsStockPage() {
        return "common/stock_search";
    }


    /**
     * 查询库存数据
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("stockList")
    @ResponseBody
    public Map<String, Object> stockLick(GoodsQuery goodsQuery) {
        return goodsService.stockList(goodsQuery);
    }

    /**
     * 商品报损|报溢查询页
     *
     * @return
     */
    @RequestMapping("toDamageOverflowSearchPage")
    @PreAuthorize("hasAnyAuthority('5040')")
    public String toDamageOverflowSearchPage() {
        return "common/damage_overflow_search";
    }


    /**
     * 库存报警页
     *
     * @return
     */
    @RequestMapping("alarmPage")
    @PreAuthorize("hasAnyAuthority('5030')")
    public String alarmPage() {
        return "common/alarm";
    }


    /**
     * 库存报警查询接口
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listAlarm")
    @ResponseBody
    public Map<String, Object> listAlarm(GoodsQuery goodsQuery) {
        goodsQuery.setType(3);
        return goodsService.goodsList(goodsQuery);
    }

}
