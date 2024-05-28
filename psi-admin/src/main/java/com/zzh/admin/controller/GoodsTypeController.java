package com.zzh.admin.controller;


import com.zzh.admin.model.RespBean;
import com.zzh.admin.model.TreeDto;
import com.zzh.admin.pojo.GoodsType;
import com.zzh.admin.service.IGoodsTypeService;
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
 * 商品类别表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Resource
    private IGoodsTypeService goodsTypeService;

    /**
     * 查询所有商品类别，树形
     *
     * @param typeId
     * @return
     */
    @RequestMapping("queryAllGoodsTypes")
    @ResponseBody
    public List<TreeDto> queryAllGoodsTypes(Integer typeId) {
        return goodsTypeService.queryAllGoodsTypes(typeId);
    }


    /**
     * 商品类别界面
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('2040')")
    public String index() {
        return "goodsType/goods_type";
    }


    /**
     * 查询所有商品类别
     *
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2040001')")
    public Map<String, Object> goodsTypeList() {
        return goodsTypeService.goodsTypeList();
    }


    /**
     * 添加类别
     *
     * @param pId
     * @param model
     * @return
     */
    @RequestMapping("addGoodsTypePage")
    @PreAuthorize("hasAnyAuthority('2040002')")
    public String addGoodsTypePage(Integer pId, Model model) {
        model.addAttribute("pId", pId);
        return "goodsType/add";
    }

    /**
     * 新增类别
     *
     * @param goodsType
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2040002')")
    public RespBean saveGoodsType(GoodsType goodsType) {
        goodsTypeService.saveGoodsType(goodsType);
        return RespBean.success("商品类别添加成功");
    }


    /**
     * 删除类别
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2040004')")
    public RespBean deleteGoodsType(Integer id) {
        goodsTypeService.deleteGoodsType(id);
        return RespBean.success("商品类别删除成功");
    }
}
