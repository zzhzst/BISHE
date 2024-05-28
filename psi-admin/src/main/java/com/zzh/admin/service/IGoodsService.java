package com.zzh.admin.service;

import com.zzh.admin.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.GoodsQuery;

import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-12
 */
public interface IGoodsService extends IService<Goods> {


    /**
     * 根据条件查询所有商品
     *
     * @param goodsQuery
     * @return
     */
    public Map<String, Object> goodsList(GoodsQuery goodsQuery);


    /**
     * 生成商品代码
     *
     * @return
     */
    public String genGoodsCode();

    /**
     * 新增商品
     *
     * @param goods
     */
    public void saveGoods(Goods goods);

    /**
     * 更新商品
     *
     * @param goods
     */
    public void updateGoods(Goods goods);

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteGoods(Integer id);

    /**
     * 更新期初库存
     *
     * @param goods
     */
    public void updateStock(Goods goods);

    /**
     * 删除期初库存
     *
     * @param id
     */
    public void deleteStock(Integer id);

    /**
     * 根据商品id查询商品
     *
     * @param gid
     * @return
     */
    Goods getGoodsInfoById(Integer gid);

    /**
     * 查询库存数据
     *
     * @param goodsQuery
     * @return
     */
    Map<String, Object> stockList(GoodsQuery goodsQuery);
}
