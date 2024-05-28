package com.zzh.admin.security;

import com.zzh.admin.config.ClassPathTldsLoader;
import com.zzh.admin.filter.CaptchaCodeFilter;
import com.zzh.admin.pojo.User;
import com.zzh.admin.service.IRoleMenuService;
import com.zzh.admin.service.IUserRoleService;
import com.zzh.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity配置类
 */
@SpringBootConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //方法都要添加  @Override，password是小写，写成大写和前端会对应不上登录会一直报错

    @Autowired
    private PsiAuthenticationSuccessHandler psiAuthenticationSuccessHandler;

    @Autowired
    private PsiAuthenticationFailedHandler psiAuthenticationFailedHandler;

    @Autowired
    private PsiLogoutSuccessHandler psiLogoutSuccessHandler;

    @Resource
    private CaptchaCodeFilter captchaCodeFilter;

    @Resource
    private IUserService userService;

    @Resource
    private DataSource dataSource;

    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private IRoleMenuService roleMenuService;


    /**
     * 放行静态资源
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/js/**",
                "/lib/**",
                "/error/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用csrf
        http.csrf().disable()
                //验证码过滤器
                .addFilterBefore(captchaCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 允许frame 页面嵌套
                .headers().frameOptions().disable()
                //登录校验
                .and()
                .formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .successHandler(psiAuthenticationSuccessHandler)
                .failureHandler(psiAuthenticationFailedHandler)
                //退出系统
                .and()
                .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(psiLogoutSuccessHandler)
                //免登录相关
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                //保存在浏览器端的cookie的名称，如果不设置默认也是remember-me
                .rememberMeCookieName("remember-me-cookie")
                //设置token的有效期，即多长时间内可以免除重复登录，单位是秒。
                .tokenValiditySeconds(7 * 24 * 60 * 60)
                //自定义
                .tokenRepository(persistentTokenRepository())
                //放行请求
                .and()
                .authorizeRequests().antMatchers("/index", "/login", "/image").permitAll()
                .anyRequest().authenticated();

    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                // 根据用户名查询用户记录
                User userDetails = userService.findUserByUserName(userName);
                //查询用户分配的角色
                List<String> roleNames = userRoleService.findRolesByUserName(userName);
                //根据用户扮演的角色查询角色拥有的权限记录
                List<String> authorities = new ArrayList<>();
                if (roleNames.size() > 0) {
                    authorities = roleMenuService.findAuthoritiesByRoleName(roleNames);
                }
                roleNames = roleNames.stream().map(role -> "ROLE_" + role).collect(Collectors.toList());
                authorities.addAll(roleNames);
                userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));
                return userDetails;
            }
        };
    }

    /**
     * 配置 SpringSecurity 密码加密 Bean对象
     *
     * @return
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证Service接口与密码加密实现类
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
    }

    /**
     * 配置从数据库中获取token
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    /**
     * 加载 ClassPathTldsLoader
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ClassPathTldsLoader.class)
    public ClassPathTldsLoader classPathTldsLoader() {
        return new ClassPathTldsLoader();
    }
}
