package com.zzh.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zzh
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;

    @ApiModelProperty(value = "头像")
    private String tx;

    @ApiModelProperty(value = "性别")
    private String sex;

    @TableField(exist = false)
    private String roleIds;

    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
