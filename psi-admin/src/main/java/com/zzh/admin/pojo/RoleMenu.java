package com.zzh.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author zzh
 * @since 2023-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role_menu")
@ApiModel(value = "RoleMenu对象", description = "角色菜单表")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;


}
