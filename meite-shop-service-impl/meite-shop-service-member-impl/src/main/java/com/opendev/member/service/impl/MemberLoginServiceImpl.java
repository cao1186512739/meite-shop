package com.opendev.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opendev.common.base.BaseApiService;
import com.opendev.common.base.BaseResponse;
import com.opendev.common.constants.Constants;
import com.opendev.common.core.utils.MD5Util;
import com.opendev.member.dao.UserDao;
import com.opendev.member.entity.UserEntity;
import com.opendev.member.handle.MemberLoginHandle;
import com.opendev.member.input.dto.UserLoginInpDTO;
import com.opendev.member.service.MemberLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class MemberLoginServiceImpl extends BaseApiService implements MemberLoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MemberLoginHandle memberLoginHandle;


    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 2.用户名称与密码登陆
        UserEntity userDo = userDao.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称与密码错误!");
        }

        //登入成功之后给用户发送token
        JSONObject tokenData = null;
        try {
            tokenData = memberLoginHandle.doLogin(userLoginInpDTO, loginType, deviceInfor, userDo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("====登录异常====");
            return setResultError("系统错误!");
        }
        return setResultSuccess(tokenData);
    }


    @Override
    public BaseResponse<JSONObject> delToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        Boolean flag = true;
        try {
            flag = memberLoginHandle.doDelKey(token);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag ? setResultSuccess("删除成功") : setResultError("删除失败!");
    }

}
