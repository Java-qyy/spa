package com.rj.bd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Order;
import com.rj.bd.entity.Root;
import com.rj.bd.entity.RootLogin;

import net.sf.jsqlparser.parser.Token;

public interface IRootMapper extends BaseMapper<Root>{
	@Select(value={"select * from root"})
	public List<Root> queryAll();

	@Select(value={"select * from root where rootuser=#{rootuser} and rootpassword=#{rootpassword}"})
	public List<Root> selectquery(@Param("rootuser") String rootuser, @Param("rootpassword") String rootpassword);

	@Select(value={"select count(*) from root  where token=#{token} and rootid=#{id} "})
	public int queryToken(@Param("token") String token,@Param("id") String id);
	
	
	
	
}
