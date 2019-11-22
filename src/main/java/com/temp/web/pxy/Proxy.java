package com.temp.web.pxy;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component("pxy")
public class Proxy {
	public int integer(String x) {
		Function<String, Integer> f = Integer :: parseInt;
		return f.apply(x);
	}
	
	public Boolean equal(String x, String y) {
		BiPredicate<String,String> p = String :: equals;
		return p.test(x, y);
	}
	
	public void pt(Object x) {
		Consumer<Object> p = System.out :: print;
		p.accept(x);
	}
	
	public String text(Object xx) {
		Function<Object, String> f = String :: valueOf;
		return f.apply(xx);
	}
	public int ranNum(int x, int y) {
		BiFunction<Integer, Integer, Integer> bf = (a,b) -> (int)Math.random()*b+a;
		return bf.apply(x, y);
	}
	public String ranEng(int x) {
		return "";
	}
}
