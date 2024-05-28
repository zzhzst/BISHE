package com.zzh.admin.service;

import com.zzh.admin.pojo.OverflowListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.OverflowListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 报溢单商品表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-20
 */
public interface IOverflowListGoodsService extends IService<OverflowListGoods> {

    /**
     * 根据条件查询报溢单商品数据
     * @param overflowListGoodsQuery
     * @return
     */
    Map<String, Object> overflowListGoodsList(OverflowListGoodsQuery overflowListGoodsQuery);
}
