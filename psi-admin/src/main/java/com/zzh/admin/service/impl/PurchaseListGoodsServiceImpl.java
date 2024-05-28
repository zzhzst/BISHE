package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.pojo.PurchaseListGoods;
import com.zzh.admin.mapper.PurchaseListGoodsMapper;
import com.zzh.admin.query.PurchaseListGoodsQuery;
import com.zzh.admin.service.IPurchaseListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-16
 */
@Service
public class PurchaseListGoodsServiceImpl extends ServiceImpl<PurchaseListGoodsMapper, PurchaseListGoods> implements IPurchaseListGoodsService {

    @Override
    public Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery) {
        IPage<PurchaseListGoods> page = new Page<PurchaseListGoods>(purchaseListGoodsQuery.getPage(),purchaseListGoodsQuery.getLimit());
        QueryWrapper<PurchaseListGoods> queryWrapper =new QueryWrapper<PurchaseListGoods>();
        if(null != purchaseListGoodsQuery.getPurchaseListId()){
            queryWrapper.eq("purchase_list_id",purchaseListGoodsQuery.getPurchaseListId());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
