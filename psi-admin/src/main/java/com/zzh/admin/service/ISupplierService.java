package com.zzh.admin.service;

import com.zzh.admin.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.SupplierQuery;

import java.util.Map;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
public interface ISupplierService extends IService<Supplier> {


    /**
     * 查询供应商列表
     *
     * @param supplierQuery
     * @return
     */
    public Map<String, Object> supplierList(SupplierQuery supplierQuery);

    /**
     * 根据供应商名称查找供应商
     *
     * @param name
     * @return
     */
    public Supplier findSupplierByName(String name);

    /**
     * 新增供应商
     *
     * @param supplier
     */
    public void saveSupplier(Supplier supplier);

    /**
     * 更改供应商
     *
     * @param supplier
     */
    public void updateSupplier(Supplier supplier);

    /**
     * 根据id删除供应商
     *
     * @param ids
     */
    public void deleteSupplier(Integer[] ids);

}
