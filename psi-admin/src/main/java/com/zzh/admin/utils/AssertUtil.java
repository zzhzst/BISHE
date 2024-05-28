package com.zzh.admin.utils;

import com.zzh.admin.exceptions.ParamsException;

/**
 * 断言工具类
 */
public class AssertUtil {

    /**
     *
     * @param flag 表达式结果
     * @param msg  返回消息
     * @return
     */
    public  static void isTrue(Boolean flag,String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }
}
