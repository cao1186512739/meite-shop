package com.opendev.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opendev.api.pay.service.PayContextService;
import com.opendev.api.pay.service.PayMentTransacInfoService;
import com.opendev.common.base.BaseApiService;
import com.opendev.common.base.BaseResponse;
import com.opendev.pay.StrategyFactory.StrategyFactory;
import com.opendev.pay.dao.ChannelDao;
import com.opendev.pay.entity.ChannelEntity;
import com.opendev.pay.input.out.dto.PayMentTransacDTO;
import com.opendev.pay.strategy.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PayContextServiceImpl extends BaseApiService<JSONObject> implements PayContextService {

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private PayMentTransacInfoService payMentTransacInfoServiceFeign;


    @Override
    public BaseResponse<JSONObject> toPayHtml(String channelId, String payToken) {

        // 1.使用渠道id获取渠道信息 classAddres
        ChannelEntity pymentChannel = channelDao.selectBychannelId(channelId);
        if (pymentChannel == null) {
            return setResultError("没有查询到该渠道信息");
        }
        // 2.使用payToken获取支付参数
        BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoServiceFeign.tokenByPayMentTransac(payToken);
        if (!isSuccess(tokenByPayMentTransac)) {
            return setResultError(tokenByPayMentTransac.getMsg());
        }
        PayMentTransacDTO payMentTransacDTO = tokenByPayMentTransac.getData();
        // 3.执行具体的支付渠道的算法获取html表单数据 策略设计模式 使用java反射机制 执行具体方法
        String classAddres = pymentChannel.getClassAddres();
        PayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres); //todo 策略者模式
        String payHtml = payStrategy.toPayHtml(pymentChannel, payMentTransacDTO);//todo  支付过程
        // 4.直接返回html
        JSONObject data = new JSONObject();
        data.put("payHtml", payHtml);
        return setResultSuccess(data);
    }
}
