package com.jsg.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("user")
public class User extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer userId;
	private String sex;
	private Date bir;
	private String userimg;
	private String phoneNum;
	private Integer money;
	private String password;

}
