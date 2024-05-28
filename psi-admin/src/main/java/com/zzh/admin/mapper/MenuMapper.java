package com.zzh.admin.mapper;

import com.zzh.admin.model.TreeDto;
import com.zzh.admin.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<TreeDto> queryAllMenus();

}
