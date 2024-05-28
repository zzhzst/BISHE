package com.zzh.admin.mapper;

import com.zzh.admin.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-07
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Map<String, Object>> queryAllRoles(Integer userId);

}
