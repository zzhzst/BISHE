package com.zzh.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzh.admin.model.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 */
@Component
public class PsiAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                RespBean.success("登录成功")));
    }
}
