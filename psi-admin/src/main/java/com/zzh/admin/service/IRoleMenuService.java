package com.zzh.admin.service;

import com.zzh.admin.pojo.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-09
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 根据角色id查询角色拥有的权限
     */

    List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId);

    /**
     *  根据角色名称查询角色用户的对应权限
     */

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
