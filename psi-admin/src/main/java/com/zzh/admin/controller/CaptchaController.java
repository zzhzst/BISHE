package com.zzh.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zzh.admin.model.CaptchaImageModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 控制器输出验证码到浏览器
 */
@RestController
public class CaptchaController {

    @Resource
    public DefaultKaptcha defaultKaptcha;


    /**
     * 验证码配置方法
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 设置IE扩展的HTTP / 1.1无缓存标头（使用addHeader）。
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 设置标准的HTTP / 1.0无缓存标头。
        response.setHeader("Pragma", "no-cache");
        // 返回一张图片
        response.setContentType("image/jpeg");
        // 验证码文字
        String capText = defaultKaptcha.createText();
        // 将验证码存入session 并设置2分钟后过期
        session.setAttribute("captcha_key", new CaptchaImageModel(capText, 2 * 60));
        // 输出流
        ServletOutputStream out = response.getOutputStream();
        //根据文本验证码内容创建图形验证码
        BufferedImage bufferedImage = defaultKaptcha.createImage(capText);
        ////输出流输出图片，格式为jpg
        ImageIO.write(bufferedImage, "jpg", out);
        out.flush();
    }
}
