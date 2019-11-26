package com.opendev.weixin.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.opendev.common.base.BaseApiService;
import com.opendev.common.base.BaseResponse;
import com.opendev.common.core.utils.RandomUtils;
import com.opendev.common.core.utils.RedisUtil;
import com.opendev.weixin.service.WeiXinCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WeiXinCodeImpl extends BaseApiService<JSONObject> implements WeiXinCode {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据传进来的手机号码生成code
     *
     * @param phone
     * @return
     */
    @Override
    public BaseResponse<JSONObject> CreateCode(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return setResultError("手机号码为空!");
        }
        if (!StringUtils.isEmpty(redisUtil.getString(phone))) {
            return setResultError("验证码已发，请查收之后再点击获取");
        }
        String randomNumber = RandomUtils.getRandomNumber(6);//随机生成6位的注册码
        redisUtil.setString(phone, randomNumber, 120L);
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("data", randomNumber);
        return setResultSuccess(jsonpObject);
    }


    /**
     * 验证二维码
     *
     * @param phone
     * @return
     */
    @Override
    public BaseResponse<JSONObject> VerifyCode(String phone,String registCode) {

        if (StringUtils.isEmpty(phone)) {
            return setResultError("手机号码为空!");
        }
        String re = redisUtil.getString(phone);
        if (StringUtils.isEmpty(re)) {
            return setResultError("验证码过期!");
        }
        if(!re.equals(registCode)){
            return setResultError("二维码输入错误!");
        }
        return setResultSuccess();
    }

}
