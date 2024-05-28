package com.zzh.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.PurchaseList;
import com.zzh.admin.pojo.PurchaseListGoods;
import com.zzh.admin.query.PurchaseListQuery;
import com.zzh.admin.service.IPurchaseListService;
import com.zzh.admin.service.IUserService;
import com.zzh.admin.utils.AssertUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-16
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseListController {

    @Resource
    private IPurchaseListService purchaseListService;

    @Resource
    private IUserService userService;

    /**
     * 进货入库主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('3010')")
    public String index(Model model) {
        // 获取进货单号
        String purchaseNumber = purchaseListService.getNextPurchaseNumber();
        model.addAttribute("purchaseNumber", purchaseNumber);
        return "purchase/purchase";
    }

    /**
     * 保存进货单
     *
     * @param purchaseList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(PurchaseList purchaseList, String goodsJson, Principal principal) {
        AssertUtil.isTrue(0 == purchaseList.getSupplierId(), "供货商不能为空");
        AssertUtil.isTrue(null == purchaseList.getPurchaseDate(), "进货日期不能为空");
        String userName = principal.getName();
        purchaseList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        //数量为0有bug
        List<PurchaseListGoods> plgList = gson.fromJson(goodsJson, new TypeToken<List<PurchaseListGoods>>() {
        }.getType());
        AssertUtil.isTrue(null == plgList || plgList.size() == 0, "商品明细不能为空");
        purchaseListService.savePurchaseList(purchaseList, plgList);
        return RespBean.success("商品进货入库成功!");
    }

    /**
     * 进货单查询页
     *
     * @return
     */
    @RequestMapping("searchPage")
    @PreAuthorize("hasAnyAuthority('3030')")
    public String searchPage() {
        return "purchase/purchase_search";
    }

    /**
     * 进货单审核页
     *
     * @return
     */
    @RequestMapping("checkPage")
    @PreAuthorize("hasAnyAuthority('3030002')")
    public String checkPage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("purchase", purchaseListService.getById(id));
        }
        return "purchase/purchase_check";
    }

    /**
     * 进货单审核
     *
     * @return
     */
    @RequestMapping("updateCheck")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('3030002')")
    public RespBean updateCheck(Integer id, String checkState,String remarks) {
        purchaseListService.updateCheck(id,checkState,remarks);
        return RespBean.success("操作成功!");
    }


    /**
     * 根据条件查询进货单
     *
     * @param purchaseListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery) {
        return purchaseListService.purchaseList(purchaseListQuery);
    }

    /**
     * 删除进货单记录
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id) {
        purchaseListService.deletePurchaseList(id);
        return RespBean.success("删除成功");
    }

    /**
     * 供应商供货结算
     *
     * @param id
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Integer id) {
        purchaseListService.updatePurchaseListState(id);
        return RespBean.success("结算成功!");
    }

    /**
     * 商品采购统计
     *
     * @param purchaseListQuery
     * @return
     */
    @RequestMapping("countPurchase")
    @ResponseBody
    public Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery) {
        return purchaseListService.countPurchase(purchaseListQuery);
    }


}
