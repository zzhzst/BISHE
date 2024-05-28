package com.zzh.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzh.admin.model.CountResultModel;
import com.zzh.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.admin.query.PurchaseListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 进货单 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-16
 */
public interface PurchaseListMapper extends BaseMapper<PurchaseList> {

    String getNextPurchaseNumber();

    IPage<PurchaseList> purchaseList(IPage<PurchaseList> page, @Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);

    Long countPurchaseTotal(@Param("purchaseListQuery")PurchaseListQuery purchaseListQuery);

    List<CountResultModel> countPurchaseList(@Param("purchaseListQuery")PurchaseListQuery purchaseListQuery);
}
