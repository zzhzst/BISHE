package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.mapper.GoodsMapper;
import com.zzh.admin.query.GoodsQuery;
import com.zzh.admin.service.ICustomerReturnListGoodsService;
import com.zzh.admin.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.service.IGoodsTypeService;
import com.zzh.admin.service.ISaleListGoodsService;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.PageResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    private IGoodsTypeService goodsTypeService;

    @Resource
    private ISaleListGoodsService saleListGoodsService;

    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;

    @Override
    public Map<String, Object> goodsList(GoodsQuery goodsQuery) {
        IPage<Goods> page = new Page<Goods>(goodsQuery.getPage(), goodsQuery.getLimit());

        if (null != goodsQuery.getTypeId()) {
            goodsQuery.setTypeIds(goodsTypeService.queryAllSubTypeIdsByTypeId(goodsQuery.getTypeId()));
        }
        page = this.baseMapper.queryGoodsByParams(page, goodsQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    public String genGoodsCode() {
        Goods goods = this.baseMapper.selectOne(new QueryWrapper<Goods>().select("max(code) as code"));
        String maxGoodsCode = null;
        if (goods != null) {
            maxGoodsCode = goods.getCode();
        }
        if (StringUtils.isNotEmpty(maxGoodsCode)) {
            Integer code = Integer.valueOf(maxGoodsCode) + 1;
            String codes = code.toString();
            int length = codes.length();
            for (int i = 4; i > length; i--) {
                codes = "0" + codes;
            }
            return codes;
        } else {
            return "0001";
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGoods(Goods goods) {
        AssertUtil.isTrue(StringUtils.isBlank(goods.getName()), "请指定商品名称!");
        AssertUtil.isTrue(null == goods.getTypeId(), "请指定商品类别!");
        AssertUtil.isTrue(StringUtils.isBlank(goods.getUnit()), "请指定商品单位!");
        goods.setCode(genGoodsCode());
        goods.setInventoryQuantity(0);
        goods.setState(0);
        goods.setLastPurchasingPrice(0F);
        goods.setAverage(0F);
        goods.setAmount(0F);
        goods.setIsDel(0);
        AssertUtil.isTrue(!(this.save(goods)), "记录添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoods(Goods goods) {
        AssertUtil.isTrue(StringUtils.isBlank(goods.getName()), "请指定商品名称!");
        AssertUtil.isTrue(null == goods.getTypeId(), "请指定商品类别!");
        AssertUtil.isTrue(StringUtils.isBlank(goods.getUnit()), "请指定商品单位!");
        AssertUtil.isTrue(!(this.updateById(goods)), "记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteGoods(Integer id) {
        Goods goods = this.getById(id);
        AssertUtil.isTrue(null == goods, "待删除的商品记录不存在!");
        AssertUtil.isTrue(goods.getState() == 1, "该商品已经期初入库，不能删除!");
        AssertUtil.isTrue(goods.getState() == 2, "该商品已经单据，不能删除!");
        goods.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(goods)), "商品删除失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateStock(Goods goods) {
        Goods temp = this.getById(goods.getId());
        AssertUtil.isTrue(null == goods, "待更新的商品记录不存在!");
        AssertUtil.isTrue(goods.getInventoryQuantity() <= 0, "库存量必须>0");
        AssertUtil.isTrue(goods.getPurchasingPrice() <= 0, "成本价必须>0");
        AssertUtil.isTrue(!(this.updateById(goods)), "商品更新失败!");
    }

    @Override
    public void deleteStock(Integer id) {
        Goods temp = this.getById(id);
        AssertUtil.isTrue(null == temp, "待更新的商品记录不存在!");
        AssertUtil.isTrue(temp.getState() == 2, "该商品已经发生单据，不可删除!");
        temp.setInventoryQuantity(0);
        AssertUtil.isTrue(!(this.updateById(temp)), "商品删除失败!");
    }

    @Override
    public Goods getGoodsInfoById(Integer gid) {
        return this.baseMapper.getGoodsInfoById(gid);
    }


    @Override
    public Map<String, Object> stockList(GoodsQuery goodsQuery) {
        IPage<Goods> page = new Page<Goods>(goodsQuery.getPage(), goodsQuery.getLimit());
        if (null != goodsQuery.getTypeId()) {
            goodsQuery.setTypeIds(goodsTypeService.queryAllSubTypeIdsByTypeId(goodsQuery.getTypeId()));
        }
        page = this.baseMapper.queryGoodsByParams(page, goodsQuery);

        List<Goods> goodsList = page.getRecords();
        goodsList.forEach(g -> {
            //销售数量减去退货数量
            g.setSaleTotal(
                    saleListGoodsService.getSaleTotalByGoodsId(g.getId()) -
                            customerReturnListGoodsService.getReturnTotalByGoodsId(g.getId())
            );
        });
        return PageResultUtil.getResult(page.getTotal(), goodsList);
    }
}
