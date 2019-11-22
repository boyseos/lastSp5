package com.temp.web.user;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.temp.web.pxy.Json;
import com.temp.web.pxy.Proxy;

@RestController
@RequestMapping("/users")
public class UserCtrl {
	@Autowired UserMapper userMapper;
	@Autowired Json<Object> m;
	@Autowired Proxy pxy;

	@GetMapping("/count")
	public Map<?,?> countUser(){
		Supplier<Integer> s = userMapper :: countUser;
		m.put("count", s.get());
		System.out.println(s.get());
		return m.get();
	}
	@GetMapping("/{uid}")
	public Map<?,?> selectUser(@PathVariable String uid){
		System.out.println(uid);
		m.put("result", "success");
		return m.get();
	}
	@PostMapping("/insert")
	public Map<?,?> insertUser(@RequestBody Map<String,String> param){
		System.out.println(param.toString());
		for(int i = 0; i< 100; i++) {
			param.put("uid",i+"12f" );
			param.put("upw", "1");
			userMapper.insertUser(param);
		}
		m.put("result", "success");
		return m.get();
	}
	@GetMapping("/createdb")
	public Map<?,?> createUserDb(){
		Runnable r = userMapper :: createUserDb;
		r.run();
		System.out.println(r);
		m.put("result", "success");
		return m.get();
	}
	@GetMapping("/dropdb")
	public Map<?,?> dropUserDb(){
		Runnable r = userMapper :: dropUserDb;
		r.run();
		m.put("result", "success");
		return m.get();
	}
	@GetMapping("/list")
	public Map<?,?> allUserList(){
		Supplier<?> s = userMapper :: allUserList;
		m.put("list",s.get());
		return m.get();
	}
}
