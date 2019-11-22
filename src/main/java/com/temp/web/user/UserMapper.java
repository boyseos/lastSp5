package com.temp.web.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	
	@Select("select count(*) from user")
	public int countUser();
	
	@Insert("insert into user(uid,upw) "
			+ "values(#{uid},#{upw})")
	public void insertUser(Map<?, ?> param);
	
	@Select("create table if not exists user("
			+ "uid varchar(16) primary key"
			+ ",upw varchar(16))")
	public void createUserDb();
	
	@Select("drop table if exists user")
	public void dropUserDb();
	
	@Select("select * from user")
	public List<Map<String,?>> allUserList();
}
