package com.zzh.admin.controller;


import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.Role;
import com.zzh.admin.query.RoleQuery;
import com.zzh.admin.service.IRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-03-07
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;


    /**
     * 角色管理主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('1020')")
    public String index() {
        return "role/role";
    }


    /**
     * 角色列表查询
     *
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102001')")
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        return roleService.roleList(roleQuery);
    }

    /**
     * 添加|更新角色页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    @PreAuthorize("hasAnyAuthority('102002','102003')")
    public String addOrUpdatePage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("role", roleService.getById(id));
        }
        return "role/add_update";
    }

    /**
     * 角色记录添加
     *
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102002')")
    public RespBean saveRole(Role role) {
        roleService.saveRole(role);
        return RespBean.success("角色记录添加成功!");
    }


    /**
     * 角色记录更新
     *
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102003')")
    public RespBean updateRole(Role role) {
        roleService.updateRole(role);
        return RespBean.success("角色记录更新成功!");
    }

    /**
     * 角色记录删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102004')")
    public RespBean deleteRole(Integer[] ids) {
        roleService.deleteRole(ids);
        return RespBean.success("角色记录删除成功!");
    }

    /**
     * 查询所有角色
     *
     * @param userId
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102001')")
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleService.queryAllRoles(userId);
    }

    /**
     * 权限添加页面
     *
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("toAddGrantPage")
    @PreAuthorize("hasAnyAuthority('102005')")
    public String toAddGrantPage(Integer roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "role/grant";
    }

    /**
     * 角色授权
     *
     * @param roleId
     * @param mids
     * @return
     */
    @RequestMapping("addGrant")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102005')")
    public RespBean addGrant(Integer roleId, Integer[] mids) {
        roleService.addGrant(roleId, mids);
        return RespBean.success("角色记录授权成功!");
    }


}
