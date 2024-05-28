package com.zzh.admin.mapper;

import com.zzh.admin.pojo.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-09
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
