package com.jsg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("item")
@Accessors(chain = true)
public class Item extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer itemId;
	private Integer sortId;
	private Integer itemPrice;
	private String itemColor;
	private String itemSize;
	private Integer itemNum;
	private String itemTitle;
	private String itemImgurl;
	
}
