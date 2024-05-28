package com.zzh.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装的返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     *
     * @param message
     * @return
     */
    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }

    /**
     * 成功方法重载，返回带有响应数据
     */
    public static RespBean success(String message, Object obj) {
        return new RespBean(200, message, obj);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @return
     */
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    /**
     * 失败方法重载，返回带有响应数据
     */
    public static RespBean error(String message, Object obj) {
        return new RespBean(500, message, obj);
    }

}
