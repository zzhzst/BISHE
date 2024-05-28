package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.model.CountResultModel;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.pojo.SaleList;
import com.zzh.admin.mapper.SaleListMapper;
import com.zzh.admin.pojo.SaleListGoods;
import com.zzh.admin.query.SaleListQuery;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.service.IGoodsTypeService;
import com.zzh.admin.service.ISaleListGoodsService;
import com.zzh.admin.service.ISaleListService;
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
 * 销售单表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Service
public class SaleListServiceImpl extends ServiceImpl<SaleListMapper, SaleList> implements ISaleListService {

    @Resource
    private IGoodsService goodsService;

    @Resource
    private ISaleListGoodsService saleListGoodsService;

    @Resource
    private IGoodsTypeService goodsTypeService;

    @Override
    public String getNextSaleNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("XS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getNextSaleNumber();
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
    public void saveSaleList(SaleList saleList, List<SaleListGoods> slgList) {
        AssertUtil.isTrue(!(this.save(saleList)), "记录添加失败!");
        SaleList temp = this.getOne(new QueryWrapper<SaleList>().eq("id", saleList.getId()));
        slgList.forEach(slg -> {
            slg.setSaleListId(temp.getId());
            Goods goods = goodsService.getById(slg.getGoodsId());
            //不允许出现负库存
            AssertUtil.isTrue((goods.getInventoryQuantity() - slg.getNum()) < 0, goods.getName() + "商品的库存量不足，请先不出库存");
            //销售完后更新库存
            goods.setInventoryQuantity(goods.getInventoryQuantity() - slg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)), "记录添加失败!");
            AssertUtil.isTrue(!(saleListGoodsService.save(slg)), "记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> saleList(SaleListQuery saleListQuery) {
        IPage<SaleList> page = new Page<SaleList>(saleListQuery.getPage(), saleListQuery.getLimit());
        page = this.baseMapper.saleList(page, saleListQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateSaleListState(Integer id) {
        SaleList saleList = this.getById(id);
        AssertUtil.isTrue(null == saleList, "待结算的销售单记录不存在!");
        AssertUtil.isTrue(saleList.getState() == 1, "该销售单已结算!");
        saleList.setState(1);
        AssertUtil.isTrue(!(this.updateById(saleList)), "销售单结算失败!");
    }


    @Override
    public Map<String, Object> countSale(SaleListQuery saleListQuery) {
        /**
         * 分页查询
         *   查总数
         *   查当前页列表
         */
        if (null != saleListQuery.getTypeId()) {
            List<Integer> typeIds = goodsTypeService.queryAllSubTypeIdsByTypeId(saleListQuery.getTypeId());
            saleListQuery.setTypeIds(typeIds);
        }
        /**
         *  page
         *    1-->0
         *    2-->10
         *    3-->20
         */
        saleListQuery.setIndex((saleListQuery.getPage() - 1) * saleListQuery.getLimit());
        Long count = this.baseMapper.countSaleTotal(saleListQuery);
        List<CountResultModel> list = this.baseMapper.saleListQueryList(saleListQuery);
        return PageResultUtil.getResult(count, list);
    }

    @Override
    public List<Map<String, Object>> countDaySale(String begin, String end) {
        return this.baseMapper.countDaySale(begin, end);
    }

    @Override
    public List<Map<String, Object>> countMonthSale(String begin, String end) {
        return this.baseMapper.countMonthSale(begin, end);
    }

}
