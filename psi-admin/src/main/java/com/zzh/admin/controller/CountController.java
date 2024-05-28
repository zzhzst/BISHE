package com.zzh.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzh.admin.model.Count;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.model.SaleCount;
import com.zzh.admin.pojo.CustomerReturnList;
import com.zzh.admin.pojo.PurchaseList;
import com.zzh.admin.pojo.ReturnList;
import com.zzh.admin.pojo.SaleList;
import com.zzh.admin.service.*;
import com.zzh.admin.utils.DateUtil;
import com.zzh.admin.utils.MathUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页动态展示控制器
 */
@RequestMapping("/count")
@Controller
public class CountController {


    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuService menuService;

    @Resource
    private ISupplierService supplierService;

    @Resource
    private ICustomerService customerService;

    @Resource
    private IGoodsService goodsService;

    @Resource
    private IPurchaseListService purchaseListService;

    @Resource
    private ISaleListService saleListService;

    @Resource
    private IReturnListService returnListService;

    @Resource
    private ICustomerReturnListService customerReturnListService;

    /**
     * 首页实时刷新
     *
     * @return
     */
    @RequestMapping("count")
    @ResponseBody
    public RespBean count() {
        RespBean respBean = new RespBean();
        Count count = new Count();
        count.setUserCount(userService.count());
        count.setRoleCount(roleService.count());
        count.setMenuCount(menuService.count());
        count.setSupplierCount(supplierService.count());
        count.setCustomerCount(customerService.count());
        count.setGoodsCount(goodsService.count());
        count.setPurchaseCount(purchaseListService.count(
                new QueryWrapper<PurchaseList>().ge("purchase_date", DateUtil.formatDate(new Date(), "yyyy-MM-dd"))));
        count.setSaleCount(saleListService.count(
                new QueryWrapper<SaleList>().ge("sale_date", DateUtil.formatDate(new Date(), "yyyy-MM-dd"))));
        respBean.setObj(count);
        return respBean;
    }

    /**
     * 七天日盈利金额
     *
     * @return
     */
    @RequestMapping("countSaleByDay")
    @ResponseBody
    public List<Float> countSaleByDay() {
        List<Float> result = new ArrayList();
        List<SaleCount> saleCounts = new ArrayList<SaleCount>();
        //获取连续前一天，前七天的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateUtils.addDays(new Date(), -7);
        String begin = sdf.format(date);
        date = DateUtils.addDays(new Date(), -1);
        String end = sdf.format(date);
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
        for (int i = 0; i < 7; i++) {
            result.add(saleCounts.get(i).getAmountProfit());
        }
        return result;
    }

    /**
     * 七日进货单数
     *
     * @return
     */
    @RequestMapping("countPachase")
    @ResponseBody
    public List<Integer> countPachase() {
        List<Integer> result = new ArrayList();
        Date date = DateUtils.addDays(new Date(), -7);
        result.add(purchaseListService.count(
                new QueryWrapper<PurchaseList>().eq("purchase_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -6);
        result.add(purchaseListService.count(
                new QueryWrapper<PurchaseList>().eq("purchase_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -5);
        result.add(purchaseListService.count(
                new QueryWrapper<PurchaseList>().eq("purchase_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -4);
        result.add(purchaseListService.count(
                new QueryWrapper<PurchaseList>().eq("purchase_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -3);
        result.add(purchaseListService.count(
                new QueryWrapper<PurchaseList>().eq("purchase_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -2);
        result.add(purchaseListService.count(
                new QueryWrapper<PurchaseList>().eq("purchase_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -1);
        result.add(purchaseListService.count(
                new QueryWrapper<PurchaseList>().eq("purchase_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        return result;
    }

    /**
     * 七日销售单数
     *
     * @return
     */
    @RequestMapping("countSale")
    @ResponseBody
    public List<Integer> countSale() {
        List<Integer> result = new ArrayList();
        Date date = DateUtils.addDays(new Date(), -7);
        result.add(saleListService.count(
                new QueryWrapper<SaleList>().eq("sale_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -6);
        result.add(saleListService.count(
                new QueryWrapper<SaleList>().eq("sale_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -5);
        result.add(saleListService.count(
                new QueryWrapper<SaleList>().eq("sale_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -4);
        result.add(saleListService.count(
                new QueryWrapper<SaleList>().eq("sale_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -3);
        result.add(saleListService.count(
                new QueryWrapper<SaleList>().eq("sale_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -2);
        result.add(saleListService.count(
                new QueryWrapper<SaleList>().eq("sale_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -1);
        result.add(saleListService.count(
                new QueryWrapper<SaleList>().eq("sale_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        return result;
    }

    /**
     * 七日退货单数
     *
     * @return
     */
    @RequestMapping("countReturn")
    @ResponseBody
    public List<Integer> countReturn() {
        List<Integer> result = new ArrayList();
        Date date = DateUtils.addDays(new Date(), -7);
        result.add(returnListService.count(
                new QueryWrapper<ReturnList>().eq("return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -6);
        result.add(returnListService.count(
                new QueryWrapper<ReturnList>().eq("return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -5);
        result.add(returnListService.count(
                new QueryWrapper<ReturnList>().eq("return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -4);
        result.add(returnListService.count(
                new QueryWrapper<ReturnList>().eq("return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -3);
        result.add(returnListService.count(
                new QueryWrapper<ReturnList>().eq("return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -2);
        result.add(returnListService.count(
                new QueryWrapper<ReturnList>().eq("return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -1);
        result.add(returnListService.count(
                new QueryWrapper<ReturnList>().eq("return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        return result;
    }

    /**
     * 七日客户退货单数
     *
     * @return
     */
    @RequestMapping("countCustomerReturn")
    @ResponseBody
    public List<Integer> countCustomerReturn() {
        List<Integer> result = new ArrayList();
        Date date = DateUtils.addDays(new Date(), -7);
        result.add(customerReturnListService.count(
                new QueryWrapper<CustomerReturnList>().eq("customer_return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -6);
        result.add(customerReturnListService.count(
                new QueryWrapper<CustomerReturnList>().eq("customer_return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -5);
        result.add(customerReturnListService.count(
                new QueryWrapper<CustomerReturnList>().eq("customer_return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -4);
        result.add(customerReturnListService.count(
                new QueryWrapper<CustomerReturnList>().eq("customer_return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -3);
        result.add(customerReturnListService.count(
                new QueryWrapper<CustomerReturnList>().eq("customer_return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -2);
        result.add(customerReturnListService.count(
                new QueryWrapper<CustomerReturnList>().eq("customer_return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        date = DateUtils.addDays(new Date(), -1);
        result.add(customerReturnListService.count(
                new QueryWrapper<CustomerReturnList>().eq("customer_return_date", DateUtil.formatDate(date, "yyyy-MM-dd"))));
        return result;
    }


}
