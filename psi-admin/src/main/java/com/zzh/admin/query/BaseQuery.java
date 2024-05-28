package com.zzh.admin.query;


import lombok.Data;

/**
 * 基本分页条件
 */
@Data
public class BaseQuery {
    private Integer page = 1;
    private Integer limit = 10;

}
