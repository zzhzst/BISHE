package com.zzh.admin.service;

import com.zzh.admin.pojo.GoodsUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.pojo.Supplier;
import com.zzh.admin.query.GoodsUnitQuery;

import java.util.Map;

/**
 * <p>
 * 商品单位表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
public interface IGoodsUnitService extends IService<GoodsUnit> {

    /**
     * 新增商品单位
     *
     * @param goodsUnit
     */
    public void saveGoodsUnit(GoodsUnit goodsUnit);

    /**
     * 更新商品单位
     *
     * @param goodsUnit
     */
    public void updateGoodsUnit(GoodsUnit goodsUnit);

    /**
     * 删除商品单位
     *
     * @param ids
     */
    public void deleteGoodsUnit(Integer[] ids);

    /**
     * 根据商品单位名称查找商品单位
     *
     * @param name
     * @return
     */
    public GoodsUnit findGoodsUnitByName(String name);

    /**
     * 查询所有商品单位
     * @param goodsUnitQuery
     * @return
     */
    public Map<String, Object> goodsUnitList(GoodsUnitQuery goodsUnitQuery);
}
