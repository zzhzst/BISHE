package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.DamageList;
import com.zzh.admin.mapper.DamageListMapper;
import com.zzh.admin.pojo.DamageListGoods;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.query.DamageListQuery;
import com.zzh.admin.service.IDamageListGoodsService;
import com.zzh.admin.service.IDamageListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.DateUtil;
import com.zzh.admin.utils.PageResultUtil;
import com.zzh.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-19
 */
@Service
public class DamageListServiceImpl extends ServiceImpl<DamageListMapper, DamageList> implements IDamageListService {
    @Resource
    private IGoodsService goodsService;

    @Resource
    private IDamageListGoodsService damageListGoodsService;

    @Override
    public String getNextDamageNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("BS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getNextDamageNumber();
            if (null != saleNumber) {
                stringBuffer.append(StringUtil.formatCode(saleNumber));
            } else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveDamageList(DamageList damageList, List<DamageListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(damageList)), "记录添加失败!");
        DamageList temp = this.getOne(new QueryWrapper<DamageList>().eq("damage_number", damageList.getDamageNumber())
                .eq("damage_date", damageList.getDamageDate()));
        plgList.forEach(plg -> {
            plg.setDamageListId(temp.getId());
            Goods goods = goodsService.getById(plg.getGoodsId());
            //减去库存
            goods.setInventoryQuantity(goods.getInventoryQuantity() - plg.getNum());
            //修改库存平均成本价
            goods.setAverage((goods.getAmount()) / (goods.getInventoryQuantity()));
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)), "记录添加失败!");
            AssertUtil.isTrue(!(damageListGoodsService.save(plg)), "记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> damageList(DamageListQuery damageListQuery) {
        IPage<DamageList> page = new Page<DamageList>(damageListQuery.getPage(), damageListQuery.getLimit());
        page = this.baseMapper.damageList(page, damageListQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

}
