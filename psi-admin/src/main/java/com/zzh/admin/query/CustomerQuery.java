package com.zzh.admin.query;

import lombok.Data;

/**
 * 客户查询条件
 */
@Data
public class CustomerQuery extends BaseQuery {
    private String customerName;
    private String contact;
    private String phone;
}
