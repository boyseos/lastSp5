package com.temp.web.user;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.temp.web.pxy.Proxy;

@Component
public class UserService extends Proxy{
	@Autowired UserMapper userMapper;
	public String selectall() {
		String test = "안녕";
		//Arrays.asList(1,2,3,4,5,).stream().forEach(System.out :: print);
		//IntStream.rangeClosed(101,200).forEach(System.out :: println);
		//new Random().ints().limit(5).forEach(System.out::println);
		userMapper.allUserList().stream().sorted().forEach(System.out :: println);
		return "5";
	}
}
