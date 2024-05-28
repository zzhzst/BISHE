package com.zzh.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzh.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.admin.query.OverFlowListQuery;

/**
 * <p>
 * 报溢单表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-20
 */
public interface OverflowListMapper extends BaseMapper<OverflowList> {

    String getOverflowNumber();

    IPage<OverflowList> overFlowList(IPage<OverflowList> page, OverFlowListQuery overFlowListQuery);
}
