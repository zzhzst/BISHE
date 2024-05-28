package com.zzh.admin.model;

import java.time.LocalDateTime;

/**
 * 验证码返回对象
 * 包括验证码和过期时间
 */
public class CaptchaImageModel {

    private String code;

    private LocalDateTime expireTime;


    public CaptchaImageModel(String code, int expireAfterSeconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public String getCode() {
        return code;
    }

    /**
     * 验证码是否失效
     *
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
