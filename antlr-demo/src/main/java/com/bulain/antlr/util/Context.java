package com.bulain.antlr.util;

import java.math.BigDecimal;
import java.util.Map;

public class Context {
	private static ThreadLocal<Map<String, BigDecimal>> threadLocal = new ThreadLocal<Map<String, BigDecimal>>();

	public static void set(Map<String, BigDecimal> map){
	    threadLocal.set(map);
	}
	
	public static BigDecimal get(String str) {
		Map<String, BigDecimal> map = threadLocal.get();
		return map.get(str);
	}
}
