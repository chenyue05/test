package com.jsg.service;

import com.jsg.pojo.User;

public interface DubboUserService {

	String checkSecurity(String phone) throws Exception;

	void insert(User user);

	User checkPhone(String phoneNum);

	String doLogin(User user, String ip);

}
