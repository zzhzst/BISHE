package com.zzh.admin.query;

import lombok.Data;

/**
 * 供应商查询条件
 */
@Data
public class SupplierQuery extends BaseQuery {
    private String supplierName;
    private String contact;
    private String phone;

}
