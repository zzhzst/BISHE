package com.zzh.admin.query;

import lombok.Data;

/**
 * 报溢单查询条件
 */
@Data
public class OverFlowListQuery extends BaseQuery {

    private String startDate;
    private String endDate;
}
