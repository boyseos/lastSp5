package com.temp.web.test;

import java.util.Arrays;

import com.temp.web.pxy.Proxy;

public class Test {

	public static void main(String[] args) {
		Proxy zz = new Proxy();
		System.out.println(zz.text(Arrays.asList("asdf","xxdf")));
	}
}
