package com.zzh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zzh.admin.model.TreeDto;
import com.zzh.admin.pojo.Menu;
import com.zzh.admin.mapper.MenuMapper;
import com.zzh.admin.pojo.RoleMenu;
import com.zzh.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.admin.service.IRoleMenuService;
import com.zzh.admin.utils.AssertUtil;
import com.zzh.admin.utils.PageResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2023-03-09
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public List<TreeDto> queryAllMenus(Integer roleId) {
        List<TreeDto> treeDtos = this.baseMapper.queryAllMenus();
        List<Integer> roleHasMenuIds = roleMenuService.queryRoleHasAllMenusByRoleId(roleId);
        //总的菜单目录和角色拥有的菜单目录取并集，让界面勾选
        if (CollectionUtils.isNotEmpty(roleHasMenuIds)) {
            treeDtos.forEach(treeDto -> {
                if (roleHasMenuIds.contains(treeDto.getId())) {
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtos;
    }

    @Override
    public Map<String, Object> menuList() {
        List<Menu> menus = this.list(new QueryWrapper<Menu>().eq("is_del", 0));
        return PageResultUtil.getResult((long) menus.size(), menus);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveMenu(Menu menu) {
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()), "请输入菜单名!");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法!");
        AssertUtil.isTrue(null != this.findMenuByNameAndGrade(menu.getName(), menu.getGrade()), "该层级下菜单已存在!");
        AssertUtil.isTrue(null != this.findMenuByAclValue(menu.getAclValue()), "权限码已存在!");
        AssertUtil.isTrue(null == menu.getpId() ||
                null == this.findMenuById(menu.getpId()), "请指定上级菜单!");
        if (grade == 1) {
            // 二级菜单
            AssertUtil.isTrue(null != this.findMenuByGradeAndUrl(menu.getUrl(), menu.getGrade()), "该层级下url 不可重复!");
        }

        menu.setIsDel(0);
        menu.setState(0);
        AssertUtil.isTrue(!(this.save(menu)), "菜单添加失败!");
    }

    @Override
    public void updateMenu(Menu menu) {
        AssertUtil.isTrue(null == menu.getId() || null == this.findMenuById(menu.getId()), "待更新的记录不存在!");
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()), "菜单名不能为空!");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法!");
        Menu temp = this.findMenuByNameAndGrade(menu.getName(), menu.getGrade());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(menu.getId())), "该层级下菜单已存在!");
        temp = this.findMenuByAclValue(menu.getAclValue());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(menu.getId())), "权限码已存在!");
        AssertUtil.isTrue(null == menu.getpId() ||
                null == this.findMenuById(menu.getpId()), "请指定上级菜单!");
        if (grade == 1) {
            // 二级菜单
            temp = this.findMenuByGradeAndUrl(menu.getUrl(), menu.getGrade());
            AssertUtil.isTrue(null != temp && !(temp.getId().equals(menu.getId())), "该层级下url 不可重复!");
        }
        AssertUtil.isTrue(!(this.updateById(menu)), "菜单记录更新失败!");
    }

    @Override
    public void deleteMenuById(Integer id) {
        Menu menu = this.findMenuById(id);
        AssertUtil.isTrue(null == id || null == menu, "待删除的记录不存在!");
        int count = this.count(new QueryWrapper<Menu>().eq("is_del", 0).eq("p_id", id));
        AssertUtil.isTrue(count > 0, "存在子菜单，不允许直接删除!");
        count = roleMenuService.count(new QueryWrapper<RoleMenu>().eq("menu_id", id));
        if (count > 0) {
            AssertUtil.isTrue(!(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("menu_id", id))), "菜单删除失败!");
        }
        menu.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(menu)), "菜单删除失败!");
    }

    @Override
    public Menu findMenuByNameAndGrade(String menuName, Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del", 0).eq("name", menuName).eq("grade", grade));
    }

    @Override
    public Menu findMenuByAclValue(String aclValue) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del", 0).eq("acl_value", aclValue));
    }

    @Override
    public Menu findMenuById(Integer id) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del", 0).eq("id", id));
    }

    @Override
    public Menu findMenuByGradeAndUrl(String url, Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del", 0).eq("url", url).eq("grade", grade));
    }
}
