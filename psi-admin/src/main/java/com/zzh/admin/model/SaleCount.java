package com.zzh.admin.model;

import lombok.Data;

/**
 * 统计报表商品销售返回对象
 */
@Data
public class SaleCount {
    private float amountCost; // 成本总金额

    private float amountSale; // 销售总金额

    private float amountProfit; // 销售利润

    private String date; // 日期
}
