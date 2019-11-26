package com.opendev.common.core.utils;


import com.opendev.common.constants.Constants;

/**
 * @author xxm
 * @create 2019-06-09 15:08
 */
public class RediskeyUtils {

    // 微信注册码存放rediskey
    public static String getWeixinCode(String content){
        return Constants.WEIXINCODE_KEY + content;
    }


    // 微信注册码存放rediskey
    public static String getTokenPrefix(String loginType){
        return Constants.MEMBER_TOKEN_KEYPREFIX+loginType+":";
    }
}
