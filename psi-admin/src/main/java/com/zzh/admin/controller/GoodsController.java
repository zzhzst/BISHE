package com.zzh.admin.controller;


import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.query.GoodsQuery;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.service.IGoodsTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    @Resource
    private IGoodsTypeService goodsTypeService;

    /**
     * 商品主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('2030')")
    public String index() {
        return "goods/goods";
    }


    /**
     * 查询所有商品
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2030001')")
    public Map<String, Object> goodsList(GoodsQuery goodsQuery) {
        return goodsService.goodsList(goodsQuery);
    }


    /**
     * 添加|更新商品页
     *
     * @param id
     * @param typeId
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateGoodsPage")
    @PreAuthorize("hasAnyAuthority('2030002','2030003')")
    public String addOrUpdateGoodsPage(Integer id, Integer typeId, Model model) {
        if (null != id) {
            Goods goods = goodsService.getById(id);
            // 更新处理
            model.addAttribute("goods", goods);
            model.addAttribute("goodsType", goodsTypeService.getById(goods.getTypeId()));
        } else {
            //添加处理
            if (null != typeId) {
                model.addAttribute("goodsType", goodsTypeService.getById(typeId));
            }

        }

        return "goods/add_update";
    }

    /**
     * 商品类别选择页
     *
     * @param typeId
     * @param model
     * @return
     */
    @RequestMapping("toGoodsTypePage")
    public String toGoodsTypePage(Integer typeId, Model model) {
        if (null != typeId) {
            model.addAttribute("typeId", typeId);
        }
        return "goods/goods_type";
    }

    /**
     * 添加商品接口
     *
     * @param goods
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2030002')")
    public RespBean saveGoods(Goods goods) {
        goodsService.saveGoods(goods);
        return RespBean.success("商品记录添加成功");
    }


    /**
     * 更新商品接口
     *
     * @param goods
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2030003')")
    public RespBean updateGoods(Goods goods) {
        goodsService.updateGoods(goods);
        return RespBean.success("商品记录更新成功");
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2030004')")
    public RespBean deleteGoods(Integer id) {
        goodsService.deleteGoods(id);
        return RespBean.success("商品记录删除成功");
    }

}
