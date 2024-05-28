package com.zzh.admin.controller;


import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.GoodsUnit;
import com.zzh.admin.query.GoodsUnitQuery;
import com.zzh.admin.query.SupplierQuery;
import com.zzh.admin.service.IGoodsUnitService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品单位表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Controller
@RequestMapping("/goodsUnit")
public class GoodsUnitController {

    @Resource
    private IGoodsUnitService goodsUnitService;

    @RequestMapping("allGoodsUnits")
    @ResponseBody
    public List<GoodsUnit> allGoodsUnits() {
        return goodsUnitService.list();
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> goodsUnitList(GoodsUnitQuery goodsUnitQuery) {
        return goodsUnitService.goodsUnitList(goodsUnitQuery);
    }

    /**
     * 商品单位界面
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('2050')")
    public String index() {
        return "goodsUnit/goods_unit";
    }

    /**
     * 添加|更新商品单位页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateGoodsUnitPage")
    @PreAuthorize("hasAnyAuthority('2050002','2050003')")
    public String addOrUpdateSupplierPage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("goodsUnit", goodsUnitService.getById(id));
        }
        return "goodsUnit/add_update";
    }

    /**
     * 添加商品类别
     *
     * @param goodsUnit
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2050002')")
    public RespBean saveSupplier(GoodsUnit goodsUnit) {
        goodsUnitService.saveGoodsUnit(goodsUnit);
        return RespBean.success("商品单位记录添加成功!");
    }

    /**
     * 更新商品类别
     *
     * @param goodsUnit
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2050003')")
    public RespBean updateSupplier(GoodsUnit goodsUnit) {
        goodsUnitService.updateGoodsUnit(goodsUnit);
        return RespBean.success("商品单位记录更新成功!");
    }

    /**
     * 删除商品单位
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2050004')")
    public RespBean deleteSupplier(Integer[] ids) {
        goodsUnitService.deleteGoodsUnit(ids);
        return RespBean.success("商品单位记录删除成功!");
    }

}
