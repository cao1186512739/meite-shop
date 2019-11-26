package com.opendev.common.base;


import lombok.ToString;

/**
 * @Author: xxm.
 * @Description:
 * @Date:Created in 2019/3/27 13:33.
 * @Modified By:
 */

@ToString
public enum CommonCode implements ResultCode{
    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    SAME_NAME_ERROR(false,10002,"存在同名的用户！"),
    INVALID_PARAM(false,10003,"非法参数！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}