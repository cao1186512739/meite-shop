package com.opendev.api.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.opendev.common.base.BaseResponse;
import com.opendev.pay.input.dto.PayCratePayTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 创建支付令牌
 */
@FeignClient(value = "app-opendev-member")
public interface PayMentTransacTokenService {

    /**
     * 创建支付令牌
     *
     * @return
     */
    @GetMapping("/cratePayToken")
    public BaseResponse<JSONObject> cratePayToken(@Validated PayCratePayTokenDto payCratePayTokenDto);

}
