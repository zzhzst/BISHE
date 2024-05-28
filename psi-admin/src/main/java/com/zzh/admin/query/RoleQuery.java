package com.zzh.admin.query;

import lombok.Data;

/**
 * 权限查询条件
 */
@Data
public class RoleQuery extends BaseQuery {
    private String roleName;
}
