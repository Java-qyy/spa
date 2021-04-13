package com.rj.bd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.Technicianpost;


public interface ITechnicianPostMapper extends BaseMapper<Technicianpost>{
	@Select(value={"select * from Technicianpost"})
	public List<Technicianpost> queryAll();
	@Select(value={"select u.user,t.technicianname,p.postcontent,p.posttime from Technicianpost p left join user u on p.userid=u.id left join technician t on p.technicianid=t.technicianid LIMIT #{page},#{size}"})
	public List<Map<String, Object>> queryMoreAll(@Param("page") int page,@Param("size") int size);
}
