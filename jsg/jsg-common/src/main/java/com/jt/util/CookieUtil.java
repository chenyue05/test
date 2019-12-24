package com.jt.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * 1.获取Cookie对象
	 */
	public static Cookie getCookie(HttpServletRequest request,String cookieKey) {
		Cookie cookie=null;
		Cookie[] cookies = request.getCookies();
		if(cookies.length>0&&cookies!=null) {
			for (Cookie cookie1 : cookies) {
				if(cookie1.getName().equals(cookieKey)) {

					cookie=cookie1;
				}
			}
		}
		return cookie;
	}
	public static void deleteCookie(HttpServletResponse response,String cookieName, int maxAge,String domain,String path) {
		Cookie jtCookie=new Cookie(cookieName,"");
		jtCookie.setMaxAge(maxAge);
		jtCookie.setDomain(domain);
		jtCookie.setPath(path);
		response.addCookie(jtCookie);
		
	}
}
