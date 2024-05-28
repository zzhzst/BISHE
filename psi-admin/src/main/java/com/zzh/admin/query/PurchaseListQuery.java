package com.zzh.admin.query;

import lombok.Data;

import java.util.List;

/**
 * 进货单查询条件
 */
@Data
public class PurchaseListQuery  extends BaseQuery{

    private String purchaseNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;
    private String goodsName;
    private Integer typeId;
    private List<Integer> typeIds;
    public Integer index;
    public Boolean checkState;
}
