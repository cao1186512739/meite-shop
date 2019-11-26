package com.opendev.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opendev.common.base.BaseApiService;
import com.opendev.common.base.BaseResponse;
import com.opendev.common.constants.Constants;
import com.opendev.common.core.utils.MD5Util;
import com.opendev.common.core.utils.MiteBeanUtils;
import com.opendev.member.dao.UserDao;

import com.opendev.member.entity.UserEntity;
import com.opendev.member.feign.WeiXinServiceFeign;
import com.opendev.member.input.dto.UserInpDTO;
import com.opendev.member.service.MemberRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;


    /**
     * 注册会员接口
     *
     * @param userInpDTO
     * @param registCode
     * @return
     */
    @Override
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String registCode) {

        if (userInpDTO == null) {
            return setResultError("实体数据错误");
        }

        if (StringUtils.isEmpty(registCode)) {
            return setResultError("验证码没有输入");
        }
        String password = userInpDTO.getPassword();
        if (org.apache.commons.lang.StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }

        log.info(">>>>>>>>>>>>开始远程调用<<<<<<<<<<<<<<<<<<<<");
        BaseResponse<JSONObject> response = weiXinServiceFeign.VerifyCode(userInpDTO.getMobile(), registCode);//此时这里的注册码为手机号码
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(response.toString());
        if (!response.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(response.getMsg());
        }
        // 3.对用户的密码进行加密 // MD5 可以解密 暴力破解
        String newPassword = MD5Util.MD5(password);
        userInpDTO.setPassword(newPassword);
        UserEntity userEntity = MiteBeanUtils.E2T(userInpDTO, UserEntity.class);
        // 4.调用数据库插入数据
        try {
            Integer row = userDao.insert(userEntity);
            return row > 0 ? setResultSuccess("注册成功") : setResultError("注册失败!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("插入数据库异常");
            return setResultError("注册失败!");
        }
    }

}
