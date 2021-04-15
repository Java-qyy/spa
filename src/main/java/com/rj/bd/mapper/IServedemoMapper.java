package com.rj.bd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.Technician;
import com.rj.bd.entity.User;

public interface IServedemoMapper extends BaseMapper<Servedemo>{
	@Select(value={"select * from servedemo"})
	public List<Servedemo> queryAll();

	@Select(value={"select * from servedemo where serve like '%${serve}%'"})
	public List<Servedemo> queryOne(@Param("serve") String serve);

	@Select(value={"select * from servedemo where serveid=#{serveid}"})
	public List<Servedemo> queryById(int serveid);

	
	@Select(value={"select serveavatar from servedemo where serveid=#{id}"})
	public String queryByIdAvatar(@Param("id") int id);

	
}
