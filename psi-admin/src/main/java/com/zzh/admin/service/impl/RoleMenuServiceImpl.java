package com.zzh.admin.service.impl;

import com.zzh.admin.pojo.RoleMenu;
import com.zzh.admin.mapper.RoleMenuMapper;
import com.zzh.admin.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-09
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId) {
        return this.baseMapper.queryRoleHasAllMenusByRoleId(roleId);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return this.baseMapper.findAuthoritiesByRoleName(roleNames);
    }

}
