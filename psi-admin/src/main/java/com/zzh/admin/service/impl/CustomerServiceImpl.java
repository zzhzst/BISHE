package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.Customer;
import com.zzh.admin.mapper.CustomerMapper;
import com.zzh.admin.query.CustomerQuery;
import com.zzh.admin.service.ICustomerService;
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
 * 客户表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public Map<String, Object> customerList(CustomerQuery customerQuery) {
        IPage<Customer> page = new Page<Customer>(customerQuery.getPage(), customerQuery.getLimit());
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(customerQuery.getCustomerName())) {
            queryWrapper.like("name", customerQuery.getCustomerName());
        }
        if (StringUtils.isNotBlank(customerQuery.getContact())) {
            queryWrapper.like("contact", customerQuery.getContact());
        }
        if (StringUtils.isNotBlank(customerQuery.getPhone())) {
            queryWrapper.like("phone", customerQuery.getPhone());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveCustomer(Customer customer) {
        AssertUtil.isTrue(StringUtils.isBlank(customer.getName()), "请输入客户名称!");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getContact()), "请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getPhone()), "请输入联系电话!");
        AssertUtil.isTrue(null != this.findCustomerByName(customer.getName()), "客户已存在!");
        customer.setIsDel(0);
        AssertUtil.isTrue(!(this.save(customer)), "记录添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateCustomer(Customer customer) {
        AssertUtil.isTrue(null == this.getById(customer.getId()), "请选择客户记录!");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getName()), "请输入客户名称!");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getContact()), "请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getPhone()), "请输入联系电话!");
        Customer temp = this.findCustomerByName(customer.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(customer.getId())), "客户已存在!");
        AssertUtil.isTrue(!(this.updateById(customer)), "记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomer(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除记录id");
        List<Customer> customerList = new ArrayList<Customer>();
        for (Integer id : ids) {
            Customer temp = this.getById(id);
            temp.setIsDel(1);
            customerList.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(customerList)), "记录删除失败!");
    }

    @Override
    public Customer findCustomerByName(String name) {
        return this.getOne(new QueryWrapper<Customer>().eq("is_del", 0).eq("name", name));
    }
}
