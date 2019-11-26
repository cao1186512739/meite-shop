package com.opendev.pay.service.impl;


import com.opendev.api.pay.service.PayMentTransacInfoService;
import com.opendev.common.base.BaseApiService;
import com.opendev.common.base.BaseResponse;
import com.opendev.common.core.utils.GenerateToken;
import com.opendev.common.core.utils.MiteBeanUtils;
import com.opendev.pay.dao.TransactionDao;
import com.opendev.pay.entity.TransactionEntity;
import com.opendev.pay.input.out.dto.PayMentTransacDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证token的合法性
 */
@RestController
public class PayMentTransacInfoServiceImpl extends BaseApiService<PayMentTransacDTO> implements PayMentTransacInfoService {

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private TransactionDao paymentTransactionMapper;

    @Override
    public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(String token) {
        // 1.验证token是否为空
        if (StringUtils.isEmpty(token)) {
            return setResultError("token参数不能空!");
        }
        // 2.使用token查询redisPayMentTransacID
        String value = generateToken.getToken(token);
        if (StringUtils.isEmpty(value)) {
            return setResultError("该Token可能已经失效或者已经过期");
        }
        // 3.转换为整数类型
        Long transactionId = Long.parseLong(value);
        // 4.使用transactionId查询支付信息
        TransactionEntity paymentTransaction = paymentTransactionMapper.selectById(transactionId);
        if (paymentTransaction == null) {
            return setResultError("未查询到该支付信息");
        }
        return setResultSuccess(MiteBeanUtils.E2T(paymentTransaction, PayMentTransacDTO.class));
    }

}
