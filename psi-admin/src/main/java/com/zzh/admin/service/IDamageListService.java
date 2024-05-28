package com.zzh.admin.service;

import com.zzh.admin.pojo.DamageList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.pojo.DamageListGoods;
import com.zzh.admin.query.DamageListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-19
 */
public interface IDamageListService extends IService<DamageList> {

    /**
     * 获取报损单号
     *
     * @return
     */
    public String getNextDamageNumber();

    /**
     * 新增报损单
     *
     * @param damageList
     * @param plgList
     */
    public void saveDamageList(DamageList damageList, List<DamageListGoods> plgList);

    /**
     * 查询报损单
     *
     * @param damageListQuery
     * @return
     */
    public Map<String, Object> damageList(DamageListQuery damageListQuery);
}
