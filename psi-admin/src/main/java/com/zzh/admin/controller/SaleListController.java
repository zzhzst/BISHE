package com.zzh.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.model.SaleCount;
import com.zzh.admin.pojo.SaleList;
import com.zzh.admin.pojo.SaleListGoods;
import com.zzh.admin.query.SaleListQuery;
import com.zzh.admin.service.ISaleListService;
import com.zzh.admin.service.IUserService;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.DateUtil;
import com.zzh.admin.utils.MathUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Controller
@RequestMapping("/sale")
public class SaleListController {

    @Resource
    private ISaleListService saleListService;

    @Resource
    private IUserService userService;

    /**
     * 销售出库主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('4010')")
    public String index(Model model) {
        model.addAttribute("saleNumber", saleListService.getNextSaleNumber());
        return "sale/sale";
    }


    /**
     * 新增销售单
     *
     * @param saleList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(SaleList saleList, String goodsJson, Principal principal) {
        AssertUtil.isTrue(0 == saleList.getCustomerId(), "客户不能为空");
        AssertUtil.isTrue(null == saleList.getSaleDate(), "销售日期不能为空");
        String userName = principal.getName();
        saleList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<SaleListGoods> slgList = gson.fromJson(goodsJson, new TypeToken<List<SaleListGoods>>() {
        }.getType());
        AssertUtil.isTrue(null == slgList || slgList.size() == 0, "商品明细不能为空");
        saleListService.saveSaleList(saleList, slgList);
        return RespBean.success("商品销售出库成功!");
    }

    /**
     * 销售单查询页
     *
     * @return
     */
    @RequestMapping("searchPage")
    @PreAuthorize("hasAnyAuthority('4030')")
    public String searchPage() {
        return "sale/sale_search";
    }

    /**
     * 销售单列表
     *
     * @param saleListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> saleList(SaleListQuery saleListQuery) {
        return saleListService.saleList(saleListQuery);
    }


    /**
     * 销售单结算
     *
     * @param id
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Integer id) {
        saleListService.updateSaleListState(id);
        return RespBean.success("销售单结算成功!");
    }

    /**
     * 商品销售统计
     *
     * @param saleListQuery
     * @return
     */
    @RequestMapping("countSale")
    @ResponseBody
    public Map<String, Object> countSale(SaleListQuery saleListQuery) {
        return saleListService.countSale(saleListQuery);
    }


    /**
     * 日销售统计
     *
     * @param begin
     * @param end
     * @return
     */
    @RequestMapping("countSaleByDay")
    @ResponseBody
    public Map<String, Object> countDaySale(String begin, String end) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<SaleCount> saleCounts = new ArrayList<SaleCount>();
        /**
         * 2021-03-15  -  2021-03-30
         */
        List<Map<String, Object>> list = saleListService.countDaySale(begin, end);
        /**
         * 根据传入的时间段 生成日期列表
         */
        List<String> datas = DateUtil.getRangeDates(begin, end);
        for (String data : datas) {
            SaleCount saleCount = new SaleCount();
            saleCount.setDate(data);
            boolean flag = true;
            for (Map<String, Object> map : list) {
                String dd = map.get("saleDate").toString().substring(0, 10);
                if (data.equals(dd)) {
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    saleCount.setAmountProfit(MathUtil.format2Bit(saleCount.getAmountSale() - saleCount.getAmountCost()));
                    flag = false;
                }
            }
            if (flag) {
                saleCount.setAmountProfit(0F);
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
            }
            saleCounts.add(saleCount);
        }

        result.put("count", saleCounts.size());
        result.put("data", saleCounts);
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }


    /**
     * 商品月销售统计
     *
     * @param begin
     * @param end
     * @return
     */
    @RequestMapping("countSaleByMonth")
    @ResponseBody
    public Map<String, Object> countSaleByMonth(String begin, String end) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<SaleCount> saleCounts = new ArrayList<SaleCount>();
        /**
         * 2021-03  -  2021-03
         */
        List<Map<String, Object>> list = saleListService.countMonthSale(begin, end);
        /**
         * 根据传入的时间段 生成日期列表
         */
        List<String> datas = DateUtil.getRangeMonth(begin, end);
        for (String data : datas) {
            SaleCount saleCount = new SaleCount();
            saleCount.setDate(data);
            boolean flag = true;
            for (Map<String, Object> map : list) {
                String dd = map.get("saleDate").toString().substring(0, 7);
                if (data.equals(dd)) {
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    saleCount.setAmountProfit(MathUtil.format2Bit(saleCount.getAmountSale() - saleCount.getAmountCost()));
                    flag = false;
                }
            }
            if (flag) {
                saleCount.setAmountProfit(0F);
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
            }
            saleCounts.add(saleCount);
        }

        result.put("count", saleCounts.size());
        result.put("data", saleCounts);
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

}
