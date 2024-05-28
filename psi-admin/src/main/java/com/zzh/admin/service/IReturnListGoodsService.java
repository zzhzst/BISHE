package com.zzh.admin.service;

import com.zzh.admin.pojo.ReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.ReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 退货单商品表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface IReturnListGoodsService extends IService<ReturnListGoods> {

    /**
     * 根据查询条件查询退货单商品
     * @param returnListGoodsQuery
     * @return
     */
    public Map<String, Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery);
}
