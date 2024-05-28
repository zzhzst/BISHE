package com.zzh.admin.mapper;

import com.zzh.admin.model.TreeDto;
import com.zzh.admin.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品类别表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<TreeDto> queryAllGoodsTypes();
}
