package com.opendev.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.opendev.common.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 模拟微信服务器与自己生成注册码的过程
 */
@Api(tags = "微信生成注册码")
public interface WeiXinCode {

    /**
     * 生成随机注册验证码
     */
    @ApiOperation(value = "根据手机号码生成验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "手机号码") })
    @GetMapping("/getCode")
    public BaseResponse<JSONObject> CreateCode(@RequestParam("phone") String phone);


    /**
     * 验证注册码
     */
    @ApiOperation(value = "验证注册码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "手机号码") })
    @GetMapping("/VerifyCode")
    public BaseResponse<JSONObject> VerifyCode(@RequestParam("phone") String phone,@RequestParam("registCode") String registCode);


}
