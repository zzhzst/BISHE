package com.zzh.admin.service;

import com.zzh.admin.model.TreeDto;
import com.zzh.admin.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-09
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据角色id查询角色的所有菜单
     *
     * @param roleId
     * @return
     */
    public List<TreeDto> queryAllMenus(Integer roleId);

    /**
     * 查询正在使用的所有菜单
     *
     * @return
     */
    public Map<String, Object> menuList();

    /**
     * 新增菜单
     *
     * @param menu
     */
    public void saveMenu(Menu menu);

    /**
     * 更新菜单
     *
     * @param menu
     */
    public void updateMenu(Menu menu);

    /**
     * 根源id删除菜单
     *
     * @param id
     */
    public void deleteMenuById(Integer id);

    /**
     * 根据菜单名称和菜单层级查找菜单
     *
     * @param menuName
     * @param grade
     * @return
     */
    public Menu findMenuByNameAndGrade(String menuName, Integer grade);

    /**
     * 根据权限码查找菜单
     *
     * @param aclValue
     * @return
     */
    public Menu findMenuByAclValue(String aclValue);

    /**
     * 根据菜单id查找菜单
     *
     * @param id
     * @return
     */
    public Menu findMenuById(Integer id);

    /**
     * 根据菜单层级和菜单url查找菜单
     *
     * @param url
     * @param grade
     * @return
     */
    public Menu findMenuByGradeAndUrl(String url, Integer grade);
}
