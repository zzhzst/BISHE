package com.zzh.admin.query;

import lombok.Data;

/**
 * 用户资料查询条件
 */
@Data
public class UserQuery extends BaseQuery {
    private String userName;
    private String trueName;
    private String sex;
    private String phone;
}
