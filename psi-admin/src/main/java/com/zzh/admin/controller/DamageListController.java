package com.zzh.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.DamageList;
import com.zzh.admin.pojo.DamageListGoods;
import com.zzh.admin.query.DamageListQuery;
import com.zzh.admin.service.IDamageListService;
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
 * 报损单表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-19
 */
@Controller
@RequestMapping("/damage")
public class DamageListController {
    @Resource
    private IDamageListService damageListService;

    @Resource
    private IUserService userService;


    /**
     * 商品报损主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('5010')")
    public String index(Model model) {
        model.addAttribute("damageNumber", damageListService.getNextDamageNumber());
        return "damage/damage";
    }

    /**
     * 新增商品报损单
     *
     * @param damageList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(DamageList damageList, String goodsJson, Principal principal) {
        Date damageDate = damageList.getDamageDate();
        AssertUtil.isTrue(null == damageDate, "开单日期不能为空");
        String userName = principal.getName();
        damageList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<DamageListGoods> plgList = gson.fromJson(goodsJson, new TypeToken<List<DamageListGoods>>() {
        }.getType());
        AssertUtil.isTrue(null == plgList || plgList.size() == 0, "商品明细不能为空");
        damageListService.saveDamageList(damageList, plgList);
        return RespBean.success("商品报损出库成功!");
    }


    /**
     * 根据条件查询商品报损单
     *
     * @param damageListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> damageList(DamageListQuery damageListQuery) {
        return damageListService.damageList(damageListQuery);
    }
}
