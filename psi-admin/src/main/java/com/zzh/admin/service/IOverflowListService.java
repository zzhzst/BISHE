package com.zzh.admin.service;

import com.zzh.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.pojo.OverflowListGoods;
import com.zzh.admin.query.OverFlowListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-20
 */
public interface IOverflowListService extends IService<OverflowList> {

    /**
     * 获取下一个报溢单号
     *
     * @return
     */
    public String getOverflowNumber();

    /**
     * 新增报溢单
     *
     * @param overflowList
     * @param plgList
     */
    public void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList);

    /**
     * 查询报溢单
     *
     * @param overFlowListQuery
     * @return
     */
    public Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery);
}
