package com.jingdianyy.subject.common.util;


import com.jingdianyy.subject.common.context.LoginContextHolder;

/**
 * 用户登录util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }

}
