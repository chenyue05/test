package com.jsg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("cart")
@Accessors(chain = true)
public class Cart extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer cartId;
	private String itemTitle;
	private String itemColor;
	private String itemImage;
	private String itemSize;
	private Integer userId;
	private Integer itemPrice;
}
