package com.jsg.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jsg.pojo.User;
import com.jsg.service.DubboUserService;
import com.jsg.util.CookieUtil;
import com.jsg.util.IPUtil;
import com.jsg.util.ObjectMapperUtil;
import com.jsg.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
public class UserController {
	@Autowired
	private JedisCluster jedis;
	@Reference(check = false)
	private DubboUserService userService;
	@RequestMapping("security")
	public String Security(String phone) {
		String security="";
		try {
			security = userService.checkSecurity(phone);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return security;
	}
	@RequestMapping("/register")
	public SysResult register(User user) {
		userService.insert(user);
		return SysResult.success("注册成功",null);
	}
	@RequestMapping("/checkPhone")
	public SysResult checkPhone(String phoneNum) {
		System.out.println(phoneNum);
		User user = userService.checkPhone(phoneNum);
		if(user!=null)return SysResult.fail();
		return SysResult.success();
	}
	@RequestMapping("/doLogin")
	public SysResult doLogin(User user,HttpServletResponse response,HttpServletRequest request) {
		
		String ip = IPUtil.getIpAddr(request);
		String ticket=userService.doLogin(user,ip);
		if(StringUtils.isEmpty(ticket)) {
			//表示账号密码错误
			return SysResult.fail();
		}
		Cookie ticketCookie=new Cookie("JXG_TICKET",ticket);
		ticketCookie.setMaxAge(7*24*60*60);
		ticketCookie.setPath("/");//表示根目录有效
		ticketCookie.setDomain("jxg.com");
		response.addCookie(ticketCookie);
		
		user.setPassword("555你信吗?");
		return SysResult.success("登录成功",user);
	}
	@RequestMapping("/user/query")
	public SysResult checkeLogin(String ticket,HttpServletRequest request,HttpServletResponse response) {
		if(StringUtils.isEmpty(ticket)) {
			return SysResult.fail();
		}
		if(!jedis.exists(ticket)) {
			//如果ticket不为null,但是redis中没有该数据
			//则表示cookie中的数据有误,应该删除该cookie
			CookieUtil.deleteCookie(response, "JXG_TICKET", 0, "jxg.com", "/");
			return SysResult.fail();
		};
		String nowIp = IPUtil.getIpAddr(request);
		String oldIp=jedis.hget(ticket, "JXG_USER_IP"); 
		if(!nowIp.equals(oldIp)) {
			
			return SysResult.fail();
		}
		System.out.println("成功了");
		//说明用户的信息是正确的
		String userJson=jedis.hget(ticket, "JXG_USER");
		User user=ObjectMapperUtil.toObject(userJson, User.class);
		return SysResult.success(user);
	}
}
