package com.opendev.pay.service.impl;


import com.opendev.api.pay.service.PaymentChannelService;
import com.opendev.common.base.BaseApiService;
import com.opendev.common.core.utils.mapper.MapperUtils;
import com.opendev.pay.dao.ChannelDao;
import com.opendev.pay.entity.ChannelEntity;
import com.opendev.pay.input.out.dto.PaymentChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查询多个支付渠道
 */
@RestController
public class PaymentChannelServiceImpl extends BaseApiService<List<PaymentChannelDTO>> implements PaymentChannelService {
    @Autowired
    private ChannelDao paymentChannelMapper;

    @Override
    public List<PaymentChannelDTO> selectAll() {
        List<ChannelEntity> paymentChannelList = paymentChannelMapper.selectList(null);
        return MapperUtils.mapAsList(paymentChannelList, PaymentChannelDTO.class);
    }

}
