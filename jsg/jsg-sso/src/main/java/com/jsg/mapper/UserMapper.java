package com.jsg.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jsg.pojo.User;
@Mapper
public interface UserMapper extends BaseMapper<User>{

}
