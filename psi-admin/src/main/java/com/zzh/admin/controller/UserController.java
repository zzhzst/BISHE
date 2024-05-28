package com.zzh.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzh.admin.model.RespBean;
import com.zzh.admin.pojo.User;
import com.zzh.admin.query.UserQuery;
import com.zzh.admin.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2023-02-28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    /**
     * 用户登录
     *由安全框架处理
     *
     * @param userName
     * @param password
     * @param session
     * @return
     */
/*    @RequestMapping("login")
    @ResponseBody
    public RespBean login(String userName, String password, HttpSession session) {
        //异常由全局处理
        User user = userService.login(userName, password);
        session.setAttribute("user", user);
        return RespBean.success("用户登录成功");

    }*/

    /**
     * 用户信息设置
     *
     * @return
     */
    @RequestMapping("setting")
    //不设权限码
    public String setting(Principal principal, Model model) {
        //重置session
        //User user = (User) session.getAttribute("user");
        //session.setAttribute("user", userService.getById(user.getId()));
        User user = userService.findUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user/setting";
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    //不设权限码
    public RespBean updateUserInfo(User user) {
        userService.updateUserInfo(user);
        return RespBean.success("用户信息更新成功");
    }

    /**
     * 密码修改页
     *
     * @return
     */
    @RequestMapping("password")
    @PreAuthorize("hasAnyAuthority('101001')")
    public String password() {
        return "user/password";
    }

    /**
     * 修改密码
     *
     * @param principal
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101001')")
    public RespBean updateUserPassword(Principal principal, String oldPassword, String newPassword, String confirmPassword) {
        //使用spring security不再使用session
        //User user = (User) session.getAttribute("user");
        userService.updateUserPassword(principal.getName(), oldPassword, newPassword, confirmPassword);
        return RespBean.success("修改密码成功");

    }

    /**
     * 用户管理主页
     *
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('1010')")
    public String index() {
        return "user/user";
    }


    /**
     * 用户列表查询接口
     *
     * @param userQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101003')")
    public Map<String, Object> userList(UserQuery userQuery) {
        return userService.userList(userQuery);
    }

    /**
     * 添加和更新用户页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateUserPage")
    @PreAuthorize("hasAnyAuthority('101004','101005')")
    public String addOrUpdatePage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("user", userService.getById(id));
        }
        return "user/add_update";
    }


    /**
     * 用户记录添加接口
     *
     * @param user
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101004')")
    public RespBean saveUser(User user) {
        userService.saveUser(user);
        return RespBean.success("用户记录添加成功!");
    }


    /**
     * 用户记录更新接口
     *
     * @param user
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101005')")
    public RespBean updateUser(User user) {
        userService.updateUser(user);
        return RespBean.success("用户记录更新成功!");
    }


    /**
     * 用户记录删除接口
     *
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101006')")
    public RespBean deleteUser(Integer[] ids) {
        userService.deleteUser(ids);
        return RespBean.success("用户记录删除成功!");
    }

    //图片上传
    @ResponseBody
    @RequestMapping("/uploadFile")
    public JSON uploadFile(MultipartFile file, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        //路径
        String filePath = System.getProperty("user.dir") + "/psi-admin/src/main/resources/public/images/";//上传到这个文件夹
        String filePath2 = System.getProperty("user.dir") + "/psi-admin/target/classes/public/images/";//上传到这个文件夹
        File file1 = new File(filePath);
        //本地路径如果没有的话创建一个
        if (!file1.exists()) {
            file1.mkdirs();
        }
        File file2 = new File(filePath2);
        //target目录没有的话创建一个
        if (!file2.exists()) {
            file2.mkdirs();
        }
        //路径+文件名
        //文件名：file.getOriginalFilename()
        String finalFilePath = filePath + file.getOriginalFilename().trim();
        File desFile = new File(finalFilePath);
        if (desFile.exists()) {
            desFile.delete();
        }

        String finalFilePath2 = filePath2 + file.getOriginalFilename().trim();
        File desFile2 = new File(finalFilePath2);
        if (desFile2.exists()) {
            desFile.delete();
        }

        try {
            file.transferTo(desFile2);
            json.put("code", 0);
            //将文件名放在msg中，前台提交表单时需要用到
            json.put("msg", file.getOriginalFilename().trim());
            JSONObject json2 = new JSONObject();
            json2.put("src", finalFilePath);
            json2.put("title", "");
            json.put("Data", json2);
            Files.copy(desFile2.toPath(), desFile.toPath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            json.put("code", 1);
        }
        System.out.println(json);
        return json;
    }
}
