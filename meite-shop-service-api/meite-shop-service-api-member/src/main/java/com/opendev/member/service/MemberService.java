package com.opendev.member.service;

import com.opendev.common.base.BaseResponse;
import com.opendev.member.input.dto.UserLoginInpDTO;
import com.opendev.member.output.dto.UserOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 会员服务接口
 * @author xxm
 * @create 2019-05-29 18:44
 */
@Api(tags = "会员服务接口")
@FeignClient("app-opendev-member")
public interface MemberService {

    /**
     * 根据手机号码查询是否已经存在,如果存在返回当前用户信息
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "根据手机号码查询是否已经存在")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "mobile", dataType = "String", required = true, value = "用户手机号码"), })
    @PostMapping("/existMobile")
    BaseResponse<UserOutDTO> existMobile(@RequestParam("mobile") String mobile);


    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "/getUserInfo")
    BaseResponse<UserOutDTO> getInfo(@RequestParam("token") String token);


    /**
     * SSO认证系统登陆接口
     *
     * @param userLoginInpDTO
     * @return
     */
    @PostMapping("/ssoLogin")
    public BaseResponse<UserOutDTO> ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO);
}
