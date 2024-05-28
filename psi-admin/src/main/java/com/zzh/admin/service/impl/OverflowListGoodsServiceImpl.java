package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.OverflowListGoods;
import com.zzh.admin.mapper.OverflowListGoodsMapper;
import com.zzh.admin.query.OverflowListGoodsQuery;
import com.zzh.admin.service.IOverflowListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 报溢单商品表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-20
 */
@Service
public class OverflowListGoodsServiceImpl extends ServiceImpl<OverflowListGoodsMapper, OverflowListGoods> implements IOverflowListGoodsService {

    @Override
    public Map<String, Object> overflowListGoodsList(OverflowListGoodsQuery overflowListGoodsQuery) {
        IPage<OverflowListGoods> page = new Page<OverflowListGoods>(overflowListGoodsQuery.getPage(), overflowListGoodsQuery.getLimit());
        QueryWrapper<OverflowListGoods> queryWrapper = new QueryWrapper<OverflowListGoods>();
        if (null != overflowListGoodsQuery.getOverflowListId()) {
            queryWrapper.eq("overflow_list_id", overflowListGoodsQuery.getOverflowListId());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }
}
