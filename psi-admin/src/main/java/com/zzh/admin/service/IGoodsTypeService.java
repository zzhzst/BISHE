package com.zzh.admin.service;

import com.zzh.admin.model.TreeDto;
import com.zzh.admin.pojo.GoodsType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
public interface IGoodsTypeService extends IService<GoodsType> {


    /**
     * 查询商品类别，树形
     *
     * @param typeId
     * @return
     */
    List<TreeDto> queryAllGoodsTypes(Integer typeId);

    /**
     * 通过点击的类别查询类别和其子类别
     *
     * @param typeId
     * @return
     */
    List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId);

    /**
     * 查询所有类型
     *
     * @return
     */
    Map<String, Object> goodsTypeList();

    /**
     * 新增商品类别
     *
     * @param goodsType
     */
    void saveGoodsType(GoodsType goodsType);

    /**
     * 删除商品类别
     *
     * @param id
     */
    void deleteGoodsType(Integer id);

}
