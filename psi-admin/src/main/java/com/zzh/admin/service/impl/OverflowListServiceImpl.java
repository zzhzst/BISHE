package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.pojo.OverflowList;
import com.zzh.admin.mapper.OverflowListMapper;
import com.zzh.admin.pojo.OverflowListGoods;
import com.zzh.admin.query.OverFlowListQuery;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.service.IOverflowListGoodsService;
import com.zzh.admin.service.IOverflowListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * 报溢单表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-20
 */
@Service
public class OverflowListServiceImpl extends ServiceImpl<OverflowListMapper, OverflowList> implements IOverflowListService {

    @Resource
    private IGoodsService goodsService;

    @Resource
    private IOverflowListGoodsService overflowListGoodsService;

    @Override
    public String getOverflowNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("BY");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getOverflowNumber();
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
    public void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(overflowList)), "记录添加失败!");
        OverflowList temp = this.getOne(new QueryWrapper<OverflowList>().eq("overflow_number", overflowList.getOverflowNumber())
                .eq("overflow_date", overflowList.getOverflowDate()));
        plgList.forEach(plg -> {
            plg.setOverflowListId(temp.getId());
            Goods goods = goodsService.getById(plg.getGoodsId());
            //修改库存
            goods.setInventoryQuantity(goods.getInventoryQuantity() + plg.getNum());
            //修改库存平均成本价
            goods.setAverage((goods.getAmount()) / (goods.getInventoryQuantity()));
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)), "记录添加失败!");
            AssertUtil.isTrue(!(overflowListGoodsService.save(plg)), "记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery) {
        IPage<OverflowList> page = new Page<OverflowList>(overFlowListQuery.getPage(), overFlowListQuery.getLimit());
        page = this.baseMapper.overFlowList(page, overFlowListQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }
}
