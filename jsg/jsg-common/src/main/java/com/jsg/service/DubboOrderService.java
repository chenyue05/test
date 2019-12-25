package com.jsg.service;

import com.jsg.pojo.Order;

public interface DubboOrderService {
	public String insertOrder(Order order);
	public Order findOrderById(String id);
}
