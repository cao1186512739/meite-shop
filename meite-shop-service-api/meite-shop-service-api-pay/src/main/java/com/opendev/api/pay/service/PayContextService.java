package com.opendev.api.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.opendev.common.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  根据支付渠道和支付的token进行支付过程
 */
@FeignClient(value = "app-opendev-member")
public interface PayContextService {


	@GetMapping("/toPayHtml")
	public BaseResponse<JSONObject> toPayHtml(@RequestParam("channelId") String channelId, @RequestParam("payToken") String payToken);

}
