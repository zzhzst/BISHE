package com.zzh.admin.query;

import lombok.Data;

/**
 * 退货单查询条件
 */
@Data
public class ReturnListQuery extends BaseQuery{

    private String returnNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;
}
