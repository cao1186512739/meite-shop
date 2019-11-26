package com.opendev.pay.strategy.impl;

import com.opendev.pay.entity.ChannelEntity;
import com.opendev.pay.input.out.dto.PayMentTransacDTO;
import com.opendev.pay.strategy.PayStrategy;

public class XiaoPayStrategy implements PayStrategy {

    @Override
    public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {
        return "小米支付from表单提交";
    }
}
