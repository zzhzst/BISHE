package com.zzh.admin.service;

import com.zzh.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.query.UserQuery;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-02-28
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     *使用安全框架后登录接口不需要了
     * @param userName
     * @param password
     * @return
     */
    /*public User login(String userName, String password);*/

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName);

    /**
     * 更新用户基础信息
     *
     * @param user
     */
    public void updateUserInfo(User user);

    /**
     * 更新用户密码
     *
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    public void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword);

    /**
     * 查询当前在使用的所有用户
     *
     * @param userQuery
     * @return
     */
    public Map<String, Object> userList(UserQuery userQuery);

    /**
     * 新增用户
     *
     * @param user
     */
    public void saveUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * 删除用户
     *
     * @param ids
     */
    public void deleteUser(Integer[] ids);
}
