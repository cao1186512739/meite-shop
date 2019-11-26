package com.opendev.pay.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.opendev.api.pay.service.PayMentTransacTokenService;
import com.opendev.common.base.BaseApiService;
import com.opendev.common.base.BaseResponse;
import com.opendev.common.core.utils.GenerateToken;
import com.opendev.common.core.utils.twitter.SnowflakeIdUtils;
import com.opendev.pay.dao.TransactionDao;
import com.opendev.pay.entity.TransactionEntity;
import com.opendev.pay.input.dto.PayCratePayTokenDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预支付过程----->>>根据订单号码生成一个token
 */
@RestController
public class PayMentTransacTokenServiceImpl extends BaseApiService<JSONObject> implements PayMentTransacTokenService {

    @Autowired
    private TransactionDao paymentTransactionMapper;

    @Autowired
    private GenerateToken generateToken;

    @Override
    public BaseResponse<JSONObject> cratePayToken(@RequestBody PayCratePayTokenDto payCratePayTokenDto) {
        String orderId = payCratePayTokenDto.getOrderId();
        if (StringUtils.isEmpty(orderId)) {
            return setResultError("订单号码不能为空!");
        }
        Long payAmount = payCratePayTokenDto.getPayAmount();
        if (payAmount == null) {
            return setResultError("金额不能为空!");
        }
        Long userId = payCratePayTokenDto.getUserId();
        if (userId == null) {
            return setResultError("userId不能为空!");
        }
        // 2.将输入插入数据库中 待支付记录
        TransactionEntity paymentTransactionEntity = new TransactionEntity();
        paymentTransactionEntity.setOrderId(orderId);
        paymentTransactionEntity.setPayAmount(payAmount);
        paymentTransactionEntity.setUserId(userId);
        // 使用雪花算法 生成全局id
        paymentTransactionEntity.setPaymentId(SnowflakeIdUtils.nextId());
        int result = paymentTransactionMapper.insertPaymentTransaction(paymentTransactionEntity);//todo 生成待支付状态数据
        if (!toDaoResult(result)) {
            return setResultError("系统错误!");
        }
        // 获取主键id
        Long payId = paymentTransactionEntity.getId();
        if (payId == null) {
            return setResultError("系统错误!");
        }

        // 3.生成对应支付令牌
        String keyPrefix = "pay_";
        String token = generateToken.createToken(keyPrefix, payId + "");
        JSONObject dataResult = new JSONObject();
        dataResult.put("token", token);
        return setResultSuccess(dataResult);
    }
}