package com.zzh.admin;

import com.zzh.admin.exceptions.ParamsException;
import com.zzh.admin.model.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理参数异常和其他异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //认证异常，权限不足
    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(AccessDeniedException e) {
        return "403";
    }

    //参数异常
    @ExceptionHandler(ParamsException.class)
    @ResponseBody
    public RespBean paramsExceptionHandler(ParamsException e) {
        return RespBean.error(e.getMsg());
    }

    //其他异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBean exceptionHandler(Exception e) {
        return RespBean.error((e.getMessage()));
    }

}
