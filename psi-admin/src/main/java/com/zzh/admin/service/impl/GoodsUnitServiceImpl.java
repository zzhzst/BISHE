package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.GoodsUnit;
import com.zzh.admin.mapper.GoodsUnitMapper;
import com.zzh.admin.query.GoodsUnitQuery;
import com.zzh.admin.service.IGoodsUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.PageResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品单位表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Service
public class GoodsUnitServiceImpl extends ServiceImpl<GoodsUnitMapper, GoodsUnit> implements IGoodsUnitService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGoodsUnit(GoodsUnit goodsUnit) {
        AssertUtil.isTrue(StringUtils.isBlank(goodsUnit.getName()), "请输入商品单位名称!");
        AssertUtil.isTrue(null != this.findGoodsUnitByName(goodsUnit.getName()), "商品单位已存在!");
        AssertUtil.isTrue(!(this.save(goodsUnit)), "记录添加失败!");
    }

    @Override
    public GoodsUnit findGoodsUnitByName(String name) {
        return this.getOne(new QueryWrapper<GoodsUnit>().eq("name", name));
    }

    @Override
    public Map<String, Object> goodsUnitList(GoodsUnitQuery goodsUnitQuery) {
        IPage<GoodsUnit> page = new Page<GoodsUnit>(goodsUnitQuery.getPage(), goodsUnitQuery.getLimit());
        QueryWrapper<GoodsUnit> queryWrapper = new QueryWrapper<GoodsUnit>();
        if (StringUtils.isNotBlank(goodsUnitQuery.getGoodsUnitName())) {
            queryWrapper.like("name", goodsUnitQuery.getGoodsUnitName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsUnit(GoodsUnit goodsUnit) {
        AssertUtil.isTrue(StringUtils.isBlank(goodsUnit.getName()), "请输入商品单位名称!");
        GoodsUnit temp = this.findGoodsUnitByName(goodsUnit.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(goodsUnit.getId())), "商品单位已存在!");
        AssertUtil.isTrue(!(this.updateById(goodsUnit)), "记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteGoodsUnit(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除记录id");
        List<Integer> goodsUnitList = new ArrayList<>();
        for (Integer id : ids) {
            goodsUnitList.add(id);
        }
        AssertUtil.isTrue(!(this.removeByIds(goodsUnitList)), "记录删除失败!");
    }
}
