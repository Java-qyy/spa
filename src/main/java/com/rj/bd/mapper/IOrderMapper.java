package com.rj.bd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Order;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.User;

public interface IOrderMapper extends BaseMapper<Order>{
	@Select(value={"select * from `order`"})
	public List<Order> queryAll();
	
	@Select(value={"select u.user,s.servemoney,o.orderstate,s.serve,t.technicianname,o.ordertime from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid"})
	public List<Map<String, Object>> query();
	
	@Select(value={"select u.user,s.servemoney,o.orderstate ,s.serve,t.technicianname,o.ordertime from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid where orderstate=#{orderstate}"})
	public List<Map<String, Object>> queryOne(int orderstate);
	
	@Select(value={"select count(*) from `order`"})
	public int queryStateA();
	@Select(value={"select count(*) from `order` where orderstate=0"})
	public int queryStateY();
	@Select(value={"select count(*) from `order` where orderstate=1"})
	public int queryStateP();
	@Select(value={"select count(*) from `order` where orderstate=2"})
	public int queryStateC();
	
}
