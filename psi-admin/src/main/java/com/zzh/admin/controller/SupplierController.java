package com.zzh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.Supplier;
import com.zzh.admin.query.SupplierQuery;
import com.zzh.admin.service.ISupplierService;
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
 * 供应商表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;

    /**
     * 供应商管理主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('2010')")
    public String index() {
        return "supplier/supplier";
    }

    /**
     * 供应商列表查询接口
     *
     * @param supplierQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2010001')")
    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        return supplierService.supplierList(supplierQuery);
    }

    /**
     * 添加|更新供应商页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateSupplierPage")
    @PreAuthorize("hasAnyAuthority('2010002','2010003')")
    public String addOrUpdateSupplierPage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("supplier", supplierService.getById(id));
        }
        return "supplier/add_update";
    }

    /**
     * 添加供应商
     *
     * @param supplier
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2010002')")
    public RespBean saveSupplier(Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return RespBean.success("供应商记录添加成功!");
    }


    /**
     * 更新供应商
     *
     * @param supplier
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2010003')")
    public RespBean updateSupplier(Supplier supplier) {
        supplierService.updateSupplier(supplier);
        return RespBean.success("供应商记录更新成功!");
    }

    /**
     * 删除供应商记录
     *
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2010004')")
    public RespBean deleteSupplier(Integer[] ids) {
        supplierService.deleteSupplier(ids);
        return RespBean.success("供应商记录删除成功!");
    }

    /**
     * 查询所有供应商
     *
     * @return
     */
    @RequestMapping("allGoodsSuppliers")
    @ResponseBody
    public List<Supplier> allGoodsSuppliers() {
        return supplierService.list(new QueryWrapper<Supplier>().eq("is_del", 0));
    }
}
