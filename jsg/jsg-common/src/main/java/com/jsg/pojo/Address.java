package com.jsg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("address")
public class Address extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer addrId;
	private Integer userId;
	private String addrName;
	private String addrLocation;
	private String addrDetail;
	private String addrPhone;
	private Integer addrDefault;
	
}
