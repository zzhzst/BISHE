package com.zzh.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器，放行登录请求和静态资源
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    //使用安全框架后，拦截器需要去除
/*    @Bean
    public NoLoginInterceptor noLoginInterceptor() {
        return new NoLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index", "/user/login",
                        "/css/**", "/error/**", "/images/**", "/js/**", "/lib/**");
    }*/
}
