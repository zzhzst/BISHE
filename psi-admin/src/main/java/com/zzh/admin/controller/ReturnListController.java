package com.zzh.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.ReturnList;
import com.zzh.admin.pojo.ReturnListGoods;
import com.zzh.admin.query.ReturnListQuery;
import com.zzh.admin.service.IReturnListService;
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
 * 退货单表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Controller
@RequestMapping("/return")
public class ReturnListController {

    @Resource
    private IReturnListService returnListService;

    @Resource
    private IUserService userService;

    /**
     * 退货单据页面
     * @param model
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('3020')")
    public String index(Model model){
        model.addAttribute("returnNumber",returnListService.getNextReturnNumber());
        return "return/return";
    }


    /**
     *保存退货单据
     * @param returnList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(ReturnList returnList, String goodsJson, Principal principal){
        AssertUtil.isTrue(0 == returnList.getSupplierId(), "供货商不能为空");
        AssertUtil.isTrue(null == returnList.getReturnDate(), "收货日期不能为空");
        String userName = principal.getName();
        returnList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<ReturnListGoods> rlgList = gson.fromJson(goodsJson,new TypeToken<List<ReturnListGoods>>(){}.getType());
        AssertUtil.isTrue(null == rlgList || rlgList.size() == 0,"商品明细不能为空");
        returnListService.saveReturnList(returnList,rlgList);
        return RespBean.success("商品退货出库成功!");
    }

    /**
     * 退货单据查询页
     * @return
     */
    @RequestMapping("searchPage")
    @PreAuthorize("hasAnyAuthority('3040')")
    public String searchPage(){
        return "return/return_search";
    }

    /**
     * 根据条件查询退货但
     * @param returnListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> returnList(ReturnListQuery returnListQuery){
        return returnListService.returnList(returnListQuery);
    }

    /**
     * 根据id删除退货单
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        returnListService.deleteReturnList(id);
        return RespBean.success("删除成功");
    }

}
