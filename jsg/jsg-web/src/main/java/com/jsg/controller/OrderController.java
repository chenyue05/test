package com.jsg.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.jsg.pojo.Cart;
import com.jsg.pojo.Order;
import com.jsg.vo.SysResult;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	//private DubboCartService cartService;
	
	
	@RequestMapping("/create")
	public String createOrder() {
		//Long userId = 7L;
		//List<Cart> cartList = cartService.findCartListByUserId(userId);
		//model.addAttribute("carts", cartList);
		return "udai_order";
	}
	
//	//order/submit 订单入库
//		@RequestMapping("/submit")
//		@ResponseBody
//		public SysResult insertOrder(Order order) {
//			Long userId = 7L;
//			order.setUserId(userId);
//			String orderId=orderService.insertOrder(order);
//			if(StringUtils.isEmpty(orderId)) {
//				return SysResult.fail();
//			}
//			return SysResult.success(orderId);
//		}
//		
//		//${order.orderId}
//		//返回一个成功的页面http://www.jt.com/order/success.html?id=71577173534339
//		@RequestMapping("/success")
//		public String findOrderById(String id,Model model) {
//			Order order= orderService.findOrderById(id);
//			model.addAttribute("order", order);
//			return "success";
//		}
}
