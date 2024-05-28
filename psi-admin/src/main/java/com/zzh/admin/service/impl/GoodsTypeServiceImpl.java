package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zzh.admin.model.TreeDto;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.pojo.GoodsType;
import com.zzh.admin.mapper.GoodsTypeMapper;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.service.IGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.PageResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品类别表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {

    @Resource
    private IGoodsService goodsService;

    @Override
    public List<TreeDto> queryAllGoodsTypes(Integer typeId) {
        List<TreeDto> treeDtos = this.baseMapper.queryAllGoodsTypes();
        if (null != typeId) {
            for (TreeDto treeDto : treeDtos) {
                if (treeDto.getId().equals(typeId)) {
                    // 设置节点选中
                    treeDto.setChecked(true);
                    break;
                }
            }
        }
        return treeDtos;
    }

    @Override
    public List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId) {
        GoodsType goodsType = this.getById(typeId);
        if (goodsType.getpId() == -1) {
            // 所有类别
            return this.list().stream().map(GoodsType::getId).collect(Collectors.toList());
        }

        List<Integer> result = new ArrayList<Integer>();
        result.add(typeId);
        return getSubTypeIds(typeId, result);
    }

    @Override
    public Map<String, Object> goodsTypeList() {
        List<GoodsType> menus = this.list();
        return PageResultUtil.getResult((long) menus.size(), menus);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGoodsType(GoodsType goodsType) {
        AssertUtil.isTrue(StringUtils.isBlank(goodsType.getName()), "类别名称不能为空!");
        AssertUtil.isTrue(null == goodsType.getpId(), "请指定父类别!");
        goodsType.setState(0);
        AssertUtil.isTrue(!(this.save(goodsType)), "记录添加失败!");
        GoodsType parent = this.getById(goodsType.getpId());
        if (parent.getState() == 0) {
            parent.setState(1);
        }
        AssertUtil.isTrue(!(this.updateById(parent)), "记录添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteGoodsType(Integer id) {
        GoodsType temp = this.getById(id);
        AssertUtil.isTrue(null == temp, "待删除的记录不存在!");
        int count = this.count(new QueryWrapper<GoodsType>().eq("p_id", id));
        AssertUtil.isTrue(count > 0, "存在子类别，暂不支持级联删除!");
        //查询类别是否被商品引用
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("type_id", temp.getId()));
        AssertUtil.isTrue(null != goods, "类别被商品引用,请先处理商品");
        count = this.count(new QueryWrapper<GoodsType>().eq("p_id", temp.getpId()));
        if (count == 1) {
            //父节点类型更新为子节点
            AssertUtil.isTrue(!(this.update(new UpdateWrapper<GoodsType>().set("state", 0).eq("id", temp.getpId()))), "类别删除失败!");
        }
        AssertUtil.isTrue(!(this.removeById(id)), "类别删除失败!");
    }

    private List<Integer> getSubTypeIds(Integer typeId, List<Integer> result) {
        //获取子类别
        List<GoodsType> goodsTypes = this.baseMapper.selectList(new QueryWrapper<GoodsType>().eq("p_id", typeId));
        //递归获取
        if (CollectionUtils.isNotEmpty(goodsTypes)) {
            goodsTypes.forEach(gt -> {
                result.add(gt.getId());
                getSubTypeIds(gt.getId(), result);
            });
        }
        return result;
    }
}
