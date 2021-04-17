package com.rj.bd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Order;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.User;

public interface IOrderMapper extends BaseMapper<Order>{
	@Select(value={"select * from `order`"})
	public List<Order> queryAll();
	
	@Select(value={"select u.user,s.servemoney as money ,o.orderstate,s.serve,t.technicianname,o.ordertime from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid  LIMIT #{page},#{size} "})
	public List<Map<String, Object>> query(@Param("page") int page,@Param("size") int size);
	
	@Select(value={"select u.user,s.servemoney as money,o.orderstate ,s.serve,t.technicianname,o.ordertime from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid where orderstate=#{orderstate}  LIMIT #{page},#{size}"})
	public List<Map<String, Object>> queryOne(int orderstate,@Param("page") int page,@Param("size") int size);
	
	@Select(value={"select count(*) from `order`"})
	public int queryStateA();
	@Select(value={"select count(*) from `order` where orderstate=0"})
	public int queryStateY();
	@Select(value={"select count(*) from `order` where orderstate=1"})
	public int queryStateP();
	@Select(value={"select count(*) from `order` where orderstate=2"})
	public int queryStateC();
	
	@Select(value={"select count(orderstate) as orderstatesum ,ordertime  from `order` where ordertime>=#{first} and ordertime<=#{end} and orderstate=0 group by ordertime "})
	public List<Map<String, Object>> queryNumber(@Param("first") String first ,@Param("end") String end);

	 
	@Select(value={"select sum(money) as moneysum,ordertime  from `order` where ordertime>=#{first} and ordertime<=#{end}  group by ordertime "})
	public List<Map<String, Object>> queryshouru(@Param("first") String first ,@Param("end") String end);

	
	@Select(value={"select o.orderid ,u.user,s.servemoney as money,o.orderstate ,s.serve,t.technicianname,o.ordertime from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid where u.user like '%${user}%' and s.serve  like '%${serve}%'  and t.technicianname like '%${technicianname}%' and  o.ordertime like '%${ordertime}%'  LIMIT #{page},#{size}    "})
	public List<Map<String, Object>> querymore(@Param("user") String user,@Param("technicianname") String technicianname,@Param("serve") String serve,@Param("ordertime") String ordertime,@Param("page") int page,@Param("size") int size);

	@Select(value={"select u.user,s.servemoney as money ,o.orderstate,s.serve,t.technicianname,o.ordertime from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid"})
	public List<Map<String, Object>> querymany();
	@Select(value={"select o.orderid ,u.user,s.servemoney as money,o.orderstate ,s.serve,t.technicianname,o.ordertime  from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid where u.user like '%${user}%' and s.serve  like '%${serve}%'  and t.technicianname like '%${technicianname}%' and  o.ordertime like '%${ordertime}%' and o.orderstate like '%${orderstate}%' LIMIT #{page},#{size}    "})
	public List<Map<String, Object>> querymorestate(@Param("user") String user,@Param("technicianname") String technicianname,@Param("serve") String serve,@Param("ordertime") String ordertime, @Param("orderstate") int orderstate, @Param("page") int page,@Param("size") int size);

	
	@Select(value={"select count(*) from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid where u.user like '%${user}%' and s.serve  like '%${serve}%'  and t.technicianname like '%${technicianname}%' and  o.ordertime like '%${ordertime}%' "})
	public int querymoretotel(@Param("user") String user,@Param("technicianname") String technicianname,@Param("serve") String serve,@Param("ordertime") String ordertime);

	@Select(value={"select  count(*) from `order` o left join `user` u on o.userid=u.id left join servedemo s on s.serveid=o.serveid left join technician t on t.technicianid=o.technicianid where u.user like '%${user}%' and s.serve  like '%${serve}%'  and t.technicianname like '%${technicianname}%' and  o.ordertime like '%${ordertime}%' and o.orderstate like '%${orderstate}%' "})
	public int querymorestatetotel(@Param("user") String user,@Param("technicianname") String technicianname,@Param("serve") String serve,@Param("ordertime") String ordertime, @Param("orderstate") int orderstate);


	
}
