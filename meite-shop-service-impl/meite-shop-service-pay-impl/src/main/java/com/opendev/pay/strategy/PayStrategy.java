package com.opendev.pay.strategy;


import com.opendev.pay.entity.ChannelEntity;
import com.opendev.pay.input.out.dto.PayMentTransacDTO;

/**
 * @description: 支付接口共同实现行为算法
 */
public interface PayStrategy {

	/**
	 * 
	 * @param pymentChannel
	 *            渠道参数
	 * @param payMentTransacDTO
	 *            支付参数
	 * @return
	 */
	public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO);

}
