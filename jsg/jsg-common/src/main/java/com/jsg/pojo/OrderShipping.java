package com.jsg.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("order_shipping")
public class OrderShipping extends BasePojo{
	@TableId
	private String orderId;
	private String addrName;
	private String addrLocation;
	private String addrDetail;
	private String addrPhone;
}
