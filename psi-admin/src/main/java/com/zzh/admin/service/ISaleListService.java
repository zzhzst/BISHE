package com.zzh.admin.service;

import com.zzh.admin.pojo.SaleList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.pojo.SaleListGoods;
import com.zzh.admin.query.SaleListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface ISaleListService extends IService<SaleList> {

    /**
     * 获取销售单号
     *
     * @return
     */
    public String getNextSaleNumber();

    /**
     * 新增销售单
     *
     * @param saleList
     * @param slgList
     */
    public void saveSaleList(SaleList saleList, List<SaleListGoods> slgList);

    /**
     * 查询销售单
     *
     * @param saleListQuery
     * @return
     */
    public Map<String, Object> saleList(SaleListQuery saleListQuery);

    /**
     * 更新销售单状态
     *
     * @param id
     */
    public void updateSaleListState(Integer id);

    /**
     * 商品销售统计
     *
     * @param saleListQuery
     * @return
     */
    public Map<String, Object> countSale(SaleListQuery saleListQuery);

    /**
     * 日销售统计
     *
     * @param begin
     * @param end
     * @return
     */
    public List<Map<String, Object>> countDaySale(String begin, String end);

    /**
     * 月销售统计
     *
     * @param begin
     * @param end
     * @return
     */
    public List<Map<String, Object>> countMonthSale(String begin, String end);
}
