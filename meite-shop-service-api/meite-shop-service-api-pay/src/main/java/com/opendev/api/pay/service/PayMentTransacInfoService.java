package com.opendev.api.pay.service;

import com.opendev.common.base.BaseResponse;
import com.opendev.pay.input.out.dto.PayMentTransacDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 验证token的合法性
 */
@FeignClient(value = "app-opendev-member")
public interface PayMentTransacInfoService {

    @GetMapping("/tokenByPayMentTransac")
    public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
