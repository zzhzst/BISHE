package com.zzh.admin.service;

import com.zzh.admin.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.RoleQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-07
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询当前使用的所有角色
     *
     * @param roleQuery
     * @return
     */
    public Map<String, Object> roleList(RoleQuery roleQuery);

    /**
     * 新增角色
     *
     * @param role
     */
    public void saveRole(Role role);

    /**
     * 更新角色信息
     *
     * @param role
     */
    public void updateRole(Role role);

    /**
     * 根据角色名称查找角色
     *
     * @param roleName
     * @return
     */
    public Role findRoleByRoleName(String roleName);

    /**
     * 根据id删除角色
     *
     * @param ids
     */
    public void deleteRole(Integer[] ids);

    /**
     * 根据用户id查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> queryAllRoles(Integer userId);

    /**
     * 给用户授权
     *
     * @param roleId
     * @param mids
     */
    public void addGrant(Integer roleId, Integer[] mids);
}
