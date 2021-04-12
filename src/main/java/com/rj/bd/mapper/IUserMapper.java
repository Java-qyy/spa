package com.rj.bd.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.User;

public interface IUserMapper extends BaseMapper<User>{

	@Select(value={"select * from user"})
	public List<User> queryAll();
}
