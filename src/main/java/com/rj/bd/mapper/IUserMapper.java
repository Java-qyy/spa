package com.rj.bd.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.User;

public interface IUserMapper extends BaseMapper<User>{

	@Select(value={"select * from user"})
	public List<User> queryAll();
	
	@Select(value={"select * from user where id=#{id}"})
	public List<User> queryById(int id);
	
	@Select(value={"select * from user where user like  '%#{name}%'"})
	public List<User> queryOne(String name);


	
}
