package com.zzh.admin.service;

import com.zzh.admin.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-07
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据登录用户名查询分配的角色
     * @param userName
     * @return
     */
    List<String> findRolesByUserName(String userName);
}
