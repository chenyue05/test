package com.jsg.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * 实现spring容器管理redis配置类
 * @author liwen
 *
 */
@Configuration//表示配置类
public class RedisConfig {


	@Bean("redisSet")
	public Set<HostAndPort> redisSet(){
		Set<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.88.129", 7000));
		nodes.add(new HostAndPort("192.168.88.129", 7001));
		nodes.add(new HostAndPort("192.168.88.129", 7002));
		nodes.add(new HostAndPort("192.168.88.129", 7003));
		nodes.add(new HostAndPort("192.168.88.129", 7004));
		nodes.add(new HostAndPort("192.168.88.129", 7005));
		return nodes;
	}
	@Bean
	@Scope("prototype")
	public JedisCluster jedisCluster(@Qualifier("redisSet")Set<HostAndPort> redisSet) {
		
		return new JedisCluster(redisSet);
	}
	
}
