package com.jsg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("order_item")
public class OrderItem extends BasePojo{
	@TableId
	private String orderId;
	private Integer itemId;
	private Integer itemNum;
	private String itemTitle;
	private Integer itemPrice;
	private Long payment;
	private String itemImgurl;
}
