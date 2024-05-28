package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.model.CountResultModel;
import com.zzh.admin.pojo.Goods;
import com.zzh.admin.pojo.PurchaseList;
import com.zzh.admin.mapper.PurchaseListMapper;
import com.zzh.admin.pojo.PurchaseListGoods;
import com.zzh.admin.query.PurchaseListQuery;
import com.zzh.admin.service.IGoodsService;
import com.zzh.admin.service.IGoodsTypeService;
import com.zzh.admin.service.IPurchaseListGoodsService;
import com.zzh.admin.service.IPurchaseListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.DateUtil;
import com.zzh.admin.utils.PageResultUtil;
import com.zzh.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-16
 */
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements IPurchaseListService {
    @Resource
    private IPurchaseListGoodsService purchaseListGoodsService;

    @Resource
    private IGoodsService goodsService;

    @Resource
    private IGoodsTypeService goodsTypeService;


    @Override
    public String getNextPurchaseNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            //前缀
            stringBuffer.append("JH");
            //获取当前日期年月日
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String purchaseNumber = this.baseMapper.getNextPurchaseNumber();
            if (null != purchaseNumber) {
                stringBuffer.append(StringUtil.formatCode(purchaseNumber));
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
    public void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList) {
        purchaseList.setCheckState("未审核");
        AssertUtil.isTrue(!(this.save(purchaseList)), "记录添加失败!");
        PurchaseList temp = this.getOne(new QueryWrapper<PurchaseList>().eq("id", purchaseList.getId()));
        plgList.forEach(plg -> {
            plg.setPurchaseListId(temp.getId());
           /* //需要改成审核通过再更新
            Goods goods = goodsService.getById(plg.getGoodsId());
            //增加库存
            goods.setInventoryQuantity(goods.getInventoryQuantity() + plg.getNum());
            //重新计算库存总金额和库商品成本
            goods.setAmount(goods.getAmount() + plg.getPrice() * plg.getNum());
            goods.setAverage((goods.getAmount()) / (goods.getInventoryQuantity()));
            goods.setLastPurchasingPrice(plg.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);*/
        });
        AssertUtil.isTrue(!(purchaseListGoodsService.saveBatch(plgList)), "记录添加失败!");
    }

    @Override
    public Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery) {
        IPage<PurchaseList> page = new Page<PurchaseList>(purchaseListQuery.getPage(), purchaseListQuery.getLimit());
        page = this.baseMapper.purchaseList(page, purchaseListQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deletePurchaseList(Integer id) {
        AssertUtil.isTrue(!(purchaseListGoodsService.remove(new QueryWrapper<PurchaseListGoods>().eq("purchase_list_id", id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)), "记录删除失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updatePurchaseListState(Integer id) {
        PurchaseList purchaseList = this.getById(id);
        AssertUtil.isTrue(null == purchaseList, "待结算的记录不存在!");
        AssertUtil.isTrue(purchaseList.getState() == 1, "记录已支付!");
        purchaseList.setState(1);
        AssertUtil.isTrue(!(this.updateById(purchaseList)), "记录结算失败!");
    }

    @Override
    public Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery) {
        if (null != purchaseListQuery.getTypeId()) {
            List<Integer> typeIds = goodsTypeService.queryAllSubTypeIdsByTypeId(purchaseListQuery.getTypeId());
            purchaseListQuery.setTypeIds(typeIds);
        }
        purchaseListQuery.setIndex((purchaseListQuery.getPage() - 1) * purchaseListQuery.getLimit());
        Long count = this.baseMapper.countPurchaseTotal(purchaseListQuery);
        List<CountResultModel> list = this.baseMapper.countPurchaseList(purchaseListQuery);
        return PageResultUtil.getResult(count, list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateCheck(Integer id, String checkState,String remarks) {
        PurchaseList purchaseList = this.getById(id);
        purchaseList.setRemarks(remarks);
        if (checkState.equals("审核未通过")) {
            purchaseList.setCheckState("审核未通过");
        } else {
            purchaseList.setCheckState(checkState);
            List<PurchaseListGoods> purchaseListGoodsList = purchaseListGoodsService.list(new QueryWrapper<PurchaseListGoods>().eq("purchase_list_id", id));
            purchaseListGoodsList.forEach(plg -> {
                Goods goods = goodsService.getById(plg.getGoodsId());
                //增加库存
                goods.setInventoryQuantity(goods.getInventoryQuantity() + plg.getNum());
                //重新计算库存总金额和库商品成本
                goods.setAmount(goods.getAmount() + plg.getPrice() * plg.getNum());
                goods.setAverage((goods.getAmount()) / (goods.getInventoryQuantity()));
                goods.setLastPurchasingPrice(plg.getPrice());
                goods.setState(2);
                goodsService.updateById(goods);
            });
        }
        this.updateById(purchaseList);


    }
}
