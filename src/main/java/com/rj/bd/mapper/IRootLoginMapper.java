package com.rj.bd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Order;
import com.rj.bd.entity.RootLogin;

public interface IRootLoginMapper extends BaseMapper<RootLogin>{

	@Select(value={"select * from rootlogin"})
	public List<RootLogin> queryAll();
	
	@Select(value={"select o.rootuser,r.logintime,r.rootip,r.temp from rootlogin r left join root o on o.rootid=r.rootid LIMIT #{page},#{size}"})
	public List<Map<String, Object>> query(@Param("page") int page,@Param("size") int size);

	
	
}
