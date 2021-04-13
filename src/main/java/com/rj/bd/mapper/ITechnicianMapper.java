package com.rj.bd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Technician;
import com.rj.bd.entity.User;

public interface ITechnicianMapper  extends BaseMapper<Technician>{

	@Select(value={"select * from technician"})
	public List<Technician> queryAll();
	
	@Select(value={"select * from technician where technicianid=#{id}"})
	public List<Technician> queryById(int id);

	@Select(value={"select * from technician where technicianname=#{name}"})
	public List<Technician> queryOne(String name);
	
}
