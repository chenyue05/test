package com.jsg.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("item_desc")
@Accessors(chain = true)
public class ItemDesc extends BasePojo{
	
	private Integer itemId;
	private String itemDesc;
	
}
