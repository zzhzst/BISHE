package com.zzh.admin.service;

import com.zzh.admin.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.CustomerQuery;

import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
public interface ICustomerService extends IService<Customer> {

    /**
     * 查询所有用户
     *
     * @param customerQuery
     * @return
     */
    public Map<String, Object> customerList(CustomerQuery customerQuery);

    /**
     * 保存客户
     *
     * @param customer
     */
    public void saveCustomer(Customer customer);

    /**
     * 更新客户
     *
     * @param customer
     */
    public void updateCustomer(Customer customer);

    /**
     * 删除用户
     *
     * @param ids
     */
    public void deleteCustomer(Integer[] ids);

    /**
     * 根据名称查找客户
     *
     * @param name
     * @return
     */
    public Customer findCustomerByName(String name);

}
