package com.zzh.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统计报表控制器
 */
@Controller
@RequestMapping("report")
public class ReportController {


    /**
     * 供应商统计页
     *
     * @return
     */
    @RequestMapping("countSupplier")
    @PreAuthorize("hasAnyAuthority('6010')")
    public String countSupplierPage() {
        return "count/supplier";
    }

    /**
     * 客户统计页
     *
     * @return
     */
    @RequestMapping("countCustomer")
    @PreAuthorize("hasAnyAuthority('6020')")
    public String countCustomerPage() {
        return "count/customer";
    }

    /**
     * 商品采购统计
     *
     * @return
     */
    @RequestMapping("countPurchase")
    @PreAuthorize("hasAnyAuthority('6030')")
    public String countPurchase() {
        return "count/purchase";
    }

    /**
     * 商品销售统计
     *
     * @return
     */
    @RequestMapping("countSale")
    @PreAuthorize("hasAnyAuthority('6040')")
    public String countSale() {
        return "count/sale";
    }


    /**
     * 日销售统计
     *
     * @return
     */
    @RequestMapping("countDaySale")
    @PreAuthorize("hasAnyAuthority('6050')")
    public String countDaySale() {
        return "count/day_sale";
    }

    /**
     * 月销售统计
     *
     * @return
     */
    @RequestMapping("countMonthSale")
    @PreAuthorize("hasAnyAuthority('6060')")
    public String countMonthSale() {
        return "count/month_sale";
    }


}
