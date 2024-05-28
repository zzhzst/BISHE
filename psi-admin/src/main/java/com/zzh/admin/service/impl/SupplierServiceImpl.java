package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.Supplier;
import com.zzh.admin.mapper.SupplierMapper;
import com.zzh.admin.query.SupplierQuery;
import com.zzh.admin.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.PageResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Override
    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        IPage<Supplier> page = new Page<Supplier>(supplierQuery.getPage(), supplierQuery.getLimit());
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<Supplier>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(supplierQuery.getSupplierName())) {
            queryWrapper.like("name", supplierQuery.getSupplierName());
        }
        if (StringUtils.isNotBlank(supplierQuery.getContact())) {
            queryWrapper.like("contact", supplierQuery.getContact());
        }
        if (StringUtils.isNotBlank(supplierQuery.getPhone())) {
            queryWrapper.like("phone", supplierQuery.getPhone());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    public Supplier findSupplierByName(String name) {
        return this.getOne(new QueryWrapper<Supplier>().eq("is_del", 0).eq("name", name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveSupplier(Supplier supplier) {
        AssertUtil.isTrue(StringUtils.isBlank(supplier.getName()), "请输入供应商名称!");
        AssertUtil.isTrue(StringUtils.isBlank(supplier.getContact()), "请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(supplier.getPhone()), "请输入联系电话!");
        AssertUtil.isTrue(null != this.findSupplierByName(supplier.getName()), "供应商已存在!");
        supplier.setIsDel(0);
        AssertUtil.isTrue(!(this.save(supplier)), "记录添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateSupplier(Supplier supplier) {
        AssertUtil.isTrue(null == this.getById(supplier.getId()), "请选择供应商记录!");
        AssertUtil.isTrue(StringUtils.isBlank(supplier.getName()), "请输入供应商名称!");
        AssertUtil.isTrue(StringUtils.isBlank(supplier.getContact()), "请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(supplier.getPhone()), "请输入联系电话!");
        Supplier temp = this.findSupplierByName(supplier.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(supplier.getId())), "供应商已存在!");
        AssertUtil.isTrue(!(this.updateById(supplier)), "记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteSupplier(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除记录id");
        List<Supplier> supplierList = new ArrayList<Supplier>();
        for (Integer id : ids) {
            Supplier temp = this.getById(id);
            temp.setIsDel(1);
            supplierList.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(supplierList)), "记录删除失败!");
    }
}
