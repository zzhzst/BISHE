package com.zzh.admin.mapper;

import com.zzh.admin.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-07
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> findRolesByUserName(String userName);
}
