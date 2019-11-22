package com.temp.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("cal")
public class Calculator {
	@Autowired Json<Integer> m;
	
	public int sum(int x, int y) {
		m.put("c", x+y);
		return m.get("c");
	}
	public int sub(int x, int y) {
		return x - y;
	}
	public int divide(int x, int y) {
		return x / y;
	}
	public int multi(int x, int y) {
		return x * y;
	}
	public int abs(int x) {
		return Math.abs(x);
	}
}
