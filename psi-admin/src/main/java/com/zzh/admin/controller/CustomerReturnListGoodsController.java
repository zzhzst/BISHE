package com.zzh.admin.controller;


import com.zzh.admin.query.CustomerReturnListGoodsQuery;
import com.zzh.admin.service.ICustomerReturnListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Controller
@RequestMapping("/customerReturnListGoods")
public class CustomerReturnListGoodsController {

    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;

    /**
     * 根据条件查询客户退货单商品
     *
     * @param customerReturnListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> customerReturnListGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery) {
        return customerReturnListGoodsService.customerReturnListGoodsList(customerReturnListGoodsQuery);
    }

}
