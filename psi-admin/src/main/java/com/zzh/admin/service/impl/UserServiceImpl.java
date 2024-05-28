package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.User;
import com.zzh.admin.mapper.UserMapper;
import com.zzh.admin.pojo.UserRole;
import com.zzh.admin.query.UserQuery;
import com.zzh.admin.service.IUserRoleService;
import com.zzh.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.PageResultUtil;
import com.zzh.admin.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private IUserRoleService userRoleService;

/*    @Override
    public User login(String userName, String password) {
        //校验参数是否合法
        AssertUtil.isTrue(StringUtil.isEmpty(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtil.isEmpty(password), "密码不能为空");
        User user = this.findUserByUserName(userName);
        AssertUtil.isTrue(null == user, "用户不存在或者已注销！");
        // 明文密码对比
        AssertUtil.isTrue(!password.equals(user.getPassword()), "密码不正确");
        return user;
    }*/

    @Override
    public User findUserByUserName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("is_del", 0).eq("user_name", userName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUsername()), "用户名不能为空");
        User temp = this.findUserByUserName(user.getUsername());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已存在!");
        AssertUtil.isTrue(!this.updateById(user), "用户信息更新失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword) {
        User user = this.findUserByUserName(userName);
        AssertUtil.isTrue(null == user, "用户不存在或未登录!");
        AssertUtil.isTrue(StringUtil.isEmpty(oldPassword), "请输入原始密码!");
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword), "请输入新密码!");
        AssertUtil.isTrue(StringUtil.isEmpty(confirmPassword), "请输入确认密码!");
        //使用加密后，使用密文加密对比,不使用明文对比
        //AssertUtil.isTrue(!(user.getPassword().equals(oldPassword)), "原始密码输入错误!");
        AssertUtil.isTrue(!(passwordEncoder.matches(oldPassword, user.getPassword())), "原始密码输入错误!");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "新密码输入不一致!");
        AssertUtil.isTrue(newPassword.equals(oldPassword), "新密码与原始密码不能一致!");
        //加密后存储密文
        //user.setPassword(newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        AssertUtil.isTrue(!(this.updateById(user)), "用户密码更新失败!");
    }

    @Override
    public Map<String, Object> userList(UserQuery userQuery) {
        IPage<User> page = new Page<User>(userQuery.getPage(), userQuery.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(userQuery.getUserName())) {
            queryWrapper.like("user_name", userQuery.getUserName());
        }
        if (StringUtils.isNotBlank(userQuery.getTrueName())) {
            queryWrapper.like("true_name", userQuery.getTrueName());
        }
        if (StringUtils.isNotBlank(userQuery.getSex())) {
            queryWrapper.like("sex", userQuery.getSex());
        }
        if (StringUtils.isNotBlank(userQuery.getPhone())) {
            queryWrapper.like("phone", userQuery.getPhone());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        //使用分页工具类封装成一个map
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveUser(User user) {
        /**
         * 用户名
         *   非空  不可重复
         * 用户密码默认123456
         * 用户默认有效
         */
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空!");
        AssertUtil.isTrue(null != this.findUserByUserName(user.getUsername()), "用户名已存在!");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setIsDel(0);
        AssertUtil.isTrue(!(this.save(user)), "用户记录添加失败!");
        // 重新查询用户记录
        User temp = this.findUserByUserName(user.getUsername());
        /**
         * 给用户分配角色
         */
        relationUserRole(temp.getId(), user.getRoleIds());
    }

    private void relationUserRole(Integer userId, String roleIds) {
        //先删除后添加
        int count = userRoleService.count(new QueryWrapper<UserRole>().eq("user_id", userId));
        if (count > 0) {
            AssertUtil.isTrue(!(userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", userId))), "用户角色分配失败!");
        }
        if (StringUtils.isNotBlank(roleIds)) {
            /**
             * 分配角色
             */
            List<UserRole> userRoles = new ArrayList<UserRole>();
            for (String s : roleIds.split(",")) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(s));
                userRoles.add(userRole);
            }
            AssertUtil.isTrue(!(userRoleService.saveBatch(userRoles)), "用户角色分配失败!");

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(User user) {
        /**
         * 用户名
         *    非空  不可重复
         */
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空!");
        User temp = this.findUserByUserName(user.getUsername());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已存在!");
        relationUserRole(user.getId(), user.getRoleIds());
        AssertUtil.isTrue(!(this.updateById(user)), "用户记录更新失败!");

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的记录id!");
        //先删除用户角色
        int count = userRoleService.count(new QueryWrapper<UserRole>().in("user_id", Arrays.asList(ids)));
        if (count > 0) {
            AssertUtil.isTrue(!(userRoleService.remove(new QueryWrapper<UserRole>().in("user_id", Arrays.asList(ids)))),
                    "用户记录删除失败!");
        }
        List<User> users = new ArrayList<User>();
        for (Integer id : ids) {
            User temp = this.getById(id);
            temp.setIsDel(1);
            users.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(users)), "用户记录删除失败!");
    }


}
