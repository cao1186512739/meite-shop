package com.opendev.member.service.impl;


import com.opendev.common.core.utils.GenerateToken;
import com.opendev.common.core.utils.RedisUtil;
import com.opendev.common.core.utils.RediskeyUtils;
import com.opendev.member.AppMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = AppMember.class)
@RunWith(SpringRunner.class)
public class MemberRegisterServiceImplTest {


    @Autowired
    public RedisUtil redisUtil;

    @Test
    public void TestAddRedis(){
        redisUtil.setString("18163981699", "123456", 120L);
    }


    @Test
    public void TestQueryRedis(){
        redisUtil.getString("18163981699");
    }

    @Test
    public void TestCreateToken(){

        String token = new GenerateToken()
                .createToken(RediskeyUtils.getTokenPrefix("Android"),
                        123 + "", 77776000L);
        System.out.println(token);
    }
}