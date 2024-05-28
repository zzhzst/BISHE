package com.zzh.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.OverflowList;
import com.zzh.admin.pojo.OverflowListGoods;
import com.zzh.admin.query.OverFlowListQuery;
import com.zzh.admin.service.IOverflowListService;
import com.zzh.admin.service.IUserService;
import com.zzh.admin.utils.AssertUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-20
 */
@Controller
@RequestMapping("/overflow")
public class OverflowListController {
    @Resource
    private IOverflowListService overflowListService;

    @Resource
    private IUserService userService;

    /**
     * 商品报溢主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('5020')")
    public String index(Model model) {
        model.addAttribute("overflowNumber", overflowListService.getOverflowNumber());
        return "overflow/overflow";
    }


    /**
     * 新增商品报溢单
     *
     * @param overflowList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(OverflowList overflowList, String goodsJson, Principal principal) {
        Date overflowDate = overflowList.getOverflowDate();
        AssertUtil.isTrue(null == overflowDate, "开单日期不能为空");
        String userName = principal.getName();
        overflowList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<OverflowListGoods> plgList = gson.fromJson(goodsJson, new TypeToken<List<OverflowListGoods>>() {
        }.getType());
        AssertUtil.isTrue(null == plgList || plgList.size() == 0, "商品明细不能为空");
        overflowListService.saveOverflowList(overflowList, plgList);
        return RespBean.success("商品报溢入库成功!");
    }


    /**
     * 根据条件查询商品报损单
     *
     * @param overFlowListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery) {
        return overflowListService.overFlowList(overFlowListQuery);
    }
}
