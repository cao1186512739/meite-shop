package com.opendev.pay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.opendev.pay.entity.TransactionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 支付交易  Mapper 接口
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-21
 */
public interface TransactionDao extends BaseMapper<TransactionEntity> {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO `payment_transaction` VALUES (null, #{payAmount}, '0', #{userId}, #{orderId}, null, null, now(), null, now(),null,#{paymentId});")
    public int insertPaymentTransaction(TransactionEntity transactionEntity);

    @Select("SELECT ID AS ID ,pay_Amount AS payAmount,payment_Status AS paymentStatus,user_ID AS userId, order_Id AS orderId , created_Time as createdTime ,partypay_Id as partyPayId , payment_Id as paymentId FROM payment_transaction WHERE ID=#{id};")
    public TransactionEntity selectById(Long id);


    @Select("SELECT ID AS ID ,pay_Amount AS payAmount,payment_Status AS paymentStatus,user_ID AS userId, order_Id AS orderId , created_Time as createdTime ,partypay_Id as partyPayId , payment_Id as paymentId FROM payment_transaction WHERE PAYMENT_ID=#{paymentId};")
    public TransactionEntity selectByPaymentId(String paymentId);

    @Update("update payment_transaction SET PAYMENT_STATUS=#{paymentStatus}  WHERE PAYMENT_ID=#{paymentId};")
    public int updatePaymentStatus(@Param("paymentStatus") String paymentStatus, @Param("paymentId") String paymentId);

    @Update("update payment_transaction SET PAYMENT_STATUS=#{paymentStatus},payment_channel=#{paymentChannel}   WHERE PAYMENT_ID=#{paymentId}; ")
    public int updatePaymentStatus(@Param("paymentStatus") String paymentStatus, @Param("paymentId") String paymentId,
                                   @Param("paymentChannel") String paymentChannel);

    @Select("SELECT ID AS ID ,pay_Amount AS payAmount,payment_Status AS paymentStatus,user_ID AS userId, order_Id AS orderId , created_Time as createdTime ,partypay_Id as partyPayId , payment_Id as paymentId ,payment_channel as paymentChannel FROM payment_transaction WHERE PAYMENT_ID=#{paymentId} and paymentStatus=0 ;")
    public TransactionEntity selectByPaymentNoPayment(String paymentId);

    @Select("SELECT ID AS ID ,pay_Amount AS payAmount,payment_Status AS paymentStatus,user_ID AS userId, order_Id AS orderId , created_Time as createdTime ,partypay_Id as partyPayId , payment_Id as paymentId ,payment_channel as paymentChannel FROM payment_transaction WHERE paymentStatus=0 ;")
    public List<TransactionEntity> selectByStatusStay();
}
