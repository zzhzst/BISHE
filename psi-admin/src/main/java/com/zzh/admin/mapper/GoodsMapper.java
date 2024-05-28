package com.zzh.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzh.admin.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.admin.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage<Goods> queryGoodsByParams(IPage<Goods> page, @Param("goodsQuery") GoodsQuery goodsQuery);

    Goods getGoodsInfoById(Integer gid);
}
