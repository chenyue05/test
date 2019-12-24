package com.jsg.pojo;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("order")
@Accessors(chain = true)
public class Order extends BasePojo{
	@TableField(exist = false)
	private OrderShipping orderShipping;
	@TableField(exist = false)
	private List<OrderItem> orderItems;
	@TableId
	private String orderId;
	private Integer userId;
	private Long payment;
	private Integer paymentType;
	private Integer status;
	

}
