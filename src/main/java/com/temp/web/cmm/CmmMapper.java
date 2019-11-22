package com.temp.web.cmm;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CmmMapper {
	
	@Select("select count(*) from ${param.dbname}")
	public int countDb(Map<?, ?> param);
	
	@Insert("Insert into ${param.dbname}() "
			+ "values ()")
	public int insertDb(Map<?, ?> param);
	
	@Select("select * from ${param.dbname}")
	public int listAllDb(Map<?, ?> param);
	
	@Select("create table ${param.dbname}(${} varchar(10) primary key,"
			+ "${} varchar(10),"
			+ "${} varchar(10),"
			+ "${} varchar(10),"
			+ "${} varchar(10),"
			+ "${} varchar(10),"
			+ "${} varchar(10),"
			+ "${} varchar(10))")
	public int createDb(Map<?, ?> param);
	
	@Select("drop table ${param.dbname}")
	public int dropDb(Map<?, ?> param);
}
