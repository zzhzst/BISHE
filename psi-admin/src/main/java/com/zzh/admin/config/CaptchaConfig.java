package com.zzh.admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

/**
 * 验证码配置类
 */
@Configuration
@PropertySource(value = {"classpath:kaptcha.properties"})
public class CaptchaConfig {
    @Value("${kaptcha.border}")
    private String border;
    @Value("${kaptcha.border.color}")
    private String borderColor;
    @Value("${kaptcha.textproducer.font.color}")
    private String fontColor;
    @Value("${kaptcha.image.width}")
    private String imageWidth;
    @Value("${kaptcha.image.height}")
    private String imageHeight;
    @Value("${kaptcha.session.key}")
    private String sessionKey;
    @Value("${kaptcha.textproducer.char.length}")
    private String charLength;
    @Value("${kaptcha.textproducer.font.names}")
    private String fontNames;
    @Value("${kaptcha.textproducer.font.size}")
    private String fontSize;

    /**
     * 自定义 验证码生成器
     *
     * @return
     */
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {

        //验证码生成器
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        //加载验证码配置
        Properties properties = new Properties();
        //是否有边框
        properties.setProperty("kaptcha.border", border);
        //设置边框颜色
        properties.setProperty("kaptcha.border.color", borderColor);
        //设置字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", fontColor);
        //验证码图片宽度
        properties.setProperty("kaptcha.image.width", imageWidth);
        //验证码图片高度
        properties.setProperty("kaptcha.image.height", imageHeight);
        //验证码
        properties.setProperty("kaptcha.session.key", sessionKey);
        //验证码文本字符长度
        properties.setProperty("kaptcha.textproducer.char.length", charLength);
        //设置字体样式
        properties.setProperty("kaptcha.textproducer.font.names", fontNames);
        //设置字体大小
        properties.setProperty("kaptcha.textproducer.font.size", fontSize);
        // 这里 是去掉 噪点颜色
        properties.setProperty("kaptcha.noise.color", "255,96,0");
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
