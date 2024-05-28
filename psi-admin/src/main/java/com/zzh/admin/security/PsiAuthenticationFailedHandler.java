package com.zzh.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzh.admin.model.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 */
@Component
public class PsiAuthenticationFailedHandler extends SimpleUrlAuthenticationFailureHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                RespBean.error("用户名或密码错误")));
    }
}

