package com.rj.bd.util;

import java.util.HashMap;
import java.util.Map;

public class Json {
	/**
	 * 封装的json工具类
	 * @return
	 */
	public static Map<String, Object> MyPrint(int code, String msg,Object data ){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("code", code);
		hashMap.put("msg",msg);
		if (data == null) {
			return hashMap;
		}
		hashMap.put("data", data);
		return hashMap;
	}
}
