package com.jsg.util;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	/**
	 * 需要将JSon串与对象互转'
	 */
	private static final ObjectMapper MAPPER =new ObjectMapper();
	public static String toJSON(Object obj) {
		String json=null;
		try {
			json=MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return json;
	}
	//将json串转对象
	public static <T> T toObject(String json,Class<T> targetClass) {
		T obj=null;
		try {
			obj=MAPPER.readValue(json,targetClass);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return obj;
	}
}
