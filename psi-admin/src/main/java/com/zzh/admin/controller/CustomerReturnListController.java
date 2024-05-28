package com.zzh.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.CustomerReturnList;
import com.zzh.admin.pojo.CustomerReturnListGoods;
import com.zzh.admin.query.CustomerReturnListQuery;
import com.zzh.admin.service.ICustomerReturnListService;
import com.zzh.admin.service.IUserService;
import com.zzh.admin.utils.AssertUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Controller
@RequestMapping("/customerReturn")
public class CustomerReturnListController {
    @Resource
    private ICustomerReturnListService customerReturnListService;


    @Resource
    private IUserService userService;

    /**
     * 客户退货主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('4020')")
    public String index(Model model) {
        model.addAttribute("customerReturnNumber", customerReturnListService.getNextCustomerReturnNumber());
        return "customerReturn/customer_return";
    }

    /**
     * 新增
     *
     * @param customerReturnList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(CustomerReturnList customerReturnList, String goodsJson, Principal principal) {
        AssertUtil.isTrue(0 == customerReturnList.getCustomerId(), "客户不能为空");
        AssertUtil.isTrue(null == customerReturnList.getCustomerReturnDate(), "退货日期不能为空");
        String userName = principal.getName();
        customerReturnList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<CustomerReturnListGoods> slgList = gson.fromJson(goodsJson, new TypeToken<List<CustomerReturnListGoods>>() {
        }.getType());
        AssertUtil.isTrue(null == slgList || slgList.size() == 0, "商品明细不能为空");
        customerReturnListService.saveCustomerReturnList(customerReturnList, slgList);
        return RespBean.success("商品退货入库成功!");
    }


    /**
     * 退货单查询页
     *
     * @return
     */
    @RequestMapping("searchPage")
    @PreAuthorize("hasAnyAuthority('4040')")
    public String searchPage() {
        return "customerReturn/customer_return_search";
    }


    /**
     * 退货单列表
     *
     * @param customerReturnListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery) {
        return customerReturnListService.customerReturnList(customerReturnListQuery);
    }

}
