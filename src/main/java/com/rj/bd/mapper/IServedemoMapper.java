package com.rj.bd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.Technician;
import com.rj.bd.entity.User;

public interface IServedemoMapper extends BaseMapper<Servedemo>{
	@Select(value={"select * from servedemo"})
	public List<Servedemo> queryAll();

	
}