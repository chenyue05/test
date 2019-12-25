package com.jsg.service;




import java.util.Date;
import java.util.UUID;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsg.mapper.UserMapper;
import com.jsg.pojo.User;
import com.jsg.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;

@Service
public class DubboUserServiceImpl implements DubboUserService {
	@Autowired
	private JedisCluster jedis;
	@Autowired
	private UserMapper userMapper;
	@Override
	public String checkSecurity(String phone) throws Exception {
		System.out.println(phone);
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码 
		NameValuePair[] data = { new NameValuePair("Uid", "sqw1234567"),//注册的用户名
				new NameValuePair("Key", "d41d8cd98f00b204e980"),//注册成功后,登录网站使用的密钥
				new NameValuePair("smsMob",phone),//手机号码
				new NameValuePair("smsText", "【中山吉祥购有限公司】验证码: 5205")};//设置短信内容
		post.setRequestBody(data);
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode=post.getStatusCode();
		System.out.println("statusCode:"+statusCode);
		for (Header header : headers) {
			System.out.println(header.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result);
		post.releaseConnection();
		return "5205";
	}

	@Override
	public void insert(User user) {
		String password =DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(password)
		.setCreated(new Date())
		.setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	@Override
	public User checkPhone(String phoneNum) {
		QueryWrapper<User> q=new QueryWrapper<>();
		q.eq("phone_num", phoneNum);
		User user = userMapper.selectOne(q);
		return user;
	}

	@Override
	public String doLogin(User user, String ip) {
		QueryWrapper<User> q=new QueryWrapper<>();
		q.eq("phone_num", user.getPhoneNum());
		q.eq("password", DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		User usr = userMapper.selectOne(q);
		if(usr==null) {
			return null;
		}
		String ticket=DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
		usr.setPassword("aaaaaaa");
		String userJSON = ObjectMapperUtil.toJSON(usr);
		//6.防止用户重复登录,需要将之前的cookie删除
		if(jedis.exists("JXG_USERNAME"+usr.getPhoneNum())) {
			String oldTicket=jedis.get("JXG_USERNAME"+usr.getPhoneNum());
			String oldTicket1=jedis.get("JT_USERNAME"+usr.getPhoneNum());
			jedis.del(oldTicket);
			jedis.del(oldTicket1);
		}
		//5.将数据保存到redis
		jedis.hset(ticket, "JXG_USER", userJSON);
		jedis.hset(ticket, "JXG_USER_IP", ip);
		jedis.expire(ticket, 7*24*3600);
		//实现用户与 ticket绑定
		jedis.setex("JXG_USERNAME"+usr.getPhoneNum(),7*24*3600,ticket);


		return ticket;
	}


}
