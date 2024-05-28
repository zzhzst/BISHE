package com.zzh.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzh.admin.pojo.DamageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.admin.query.DamageListQuery;

/**
 * <p>
 * 报损单表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-19
 */
public interface DamageListMapper extends BaseMapper<DamageList> {

    String getNextDamageNumber();

    IPage<DamageList> damageList(IPage<DamageList> page, DamageListQuery damageListQuery);
}
