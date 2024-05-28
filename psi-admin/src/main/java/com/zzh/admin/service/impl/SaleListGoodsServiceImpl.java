package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.SaleListGoods;
import com.zzh.admin.mapper.SaleListGoodsMapper;
import com.zzh.admin.query.saleListGoodsQuery;
import com.zzh.admin.service.ISaleListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Service
public class SaleListGoodsServiceImpl extends ServiceImpl<SaleListGoodsMapper, SaleListGoods> implements ISaleListGoodsService {

    @Override
    public Integer getSaleTotalByGoodsId(Integer id) {
        SaleListGoods saleListGoods = this.getOne(new QueryWrapper<SaleListGoods>().select("sum(num) as num").eq("goods_id", id));
        return null == saleListGoods ? 0 : saleListGoods.getNum();
    }

    @Override
    public Map<String, Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery) {
        IPage<SaleListGoods> page = new Page<SaleListGoods>(saleListGoodsQuery.getPage(), saleListGoodsQuery.getLimit());
        QueryWrapper<SaleListGoods> queryWrapper = new QueryWrapper<SaleListGoods>();
        if (null != saleListGoodsQuery.getSaleListId()) {
            queryWrapper.eq("sale_list_id", saleListGoodsQuery.getSaleListId());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }
}
