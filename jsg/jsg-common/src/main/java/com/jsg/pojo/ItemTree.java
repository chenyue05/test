package com.jsg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("item_tree")
@Accessors(chain = true)
public class ItemTree extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer sortId;
	private String name;
	private Integer parentId;
	private Integer status;
	private Integer isParent;
	
}
