package com.opendev.member.feign;

import com.opendev.weixin.service.WeiXinCode;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-opendev-weixin")
public interface WeiXinServiceFeign extends WeiXinCode {
}
