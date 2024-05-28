package com.zzh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.Customer;
import com.zzh.admin.query.CustomerQuery;
import com.zzh.admin.service.ICustomerService;
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
 * 客户表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private ICustomerService customerService;

    /**
     * 客户管理主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('2020')")
    public String index() {
        return "customer/customer";
    }


    /**
     * 客户列表查询
     *
     * @param customerQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2020001')")
    public Map<String, Object> customerList(CustomerQuery customerQuery) {
        return customerService.customerList(customerQuery);
    }

    /**
     * 添加更新客户页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateCustomerPage")
    @PreAuthorize("hasAnyAuthority('2020002','2020003')")
    public String addRolePage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("customer", customerService.getById(id));
        }
        return "customer/add_update";
    }


    /**
     * 保存客户
     *
     * @param Customer
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2020002')")
    public RespBean saveCustomer(Customer Customer) {
        customerService.saveCustomer(Customer);
        return RespBean.success("记录添加成功");
    }

    /**
     * 更新客户
     *
     * @param Customer
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2020003')")
    public RespBean updateCustomer(Customer Customer) {
        customerService.updateCustomer(Customer);
        return RespBean.success("记录更新成功");
    }

    /**
     * 删除客户
     *
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('2020004')")
    public RespBean deleteCustomer(Integer[] ids) {
        customerService.deleteCustomer(ids);
        return RespBean.success("客户记录删除成功");
    }

    /**
     * 查询所有客户
     * @return
     */
    @RequestMapping("allCustomers")
    @ResponseBody
    public List<Customer> allCustomers(){
        return customerService.list(new QueryWrapper<Customer>().eq("is_del",0));
    }
}
