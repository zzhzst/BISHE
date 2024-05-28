package com.zzh.admin.query;

import lombok.Data;

/**
 * 报损单查询条件
 */
@Data
public class DamageListQuery extends BaseQuery {

    private String startDate;
    private String endDate;
}
