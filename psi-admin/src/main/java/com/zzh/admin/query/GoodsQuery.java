package com.zzh.admin.query;

import lombok.Data;

import java.util.List;

/**
 * 商品查询条件
 */
@Data
public class GoodsQuery extends BaseQuery {
    private String goodsName;
    private Integer typeId;

    private List<Integer> typeIds;

    // 查询类型 区分库存量是否大于0查询
    /**
     * 1 库存量=0
     * 2 库存量>0
     * 3 库存量<库存下限
     */
    private Integer type;

}
