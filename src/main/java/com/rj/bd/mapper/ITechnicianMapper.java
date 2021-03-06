package com.rj.bd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Technician;
import com.rj.bd.entity.User;

public interface ITechnicianMapper  extends BaseMapper<Technician>{

	@Select(value={"select * from technician"})
	public List<Technician> queryAll();
	
	@Select(value={"select * from technician where technicianid=#{technicianid}"})
	public List<Technician> queryById(int technicianid);

	@Select(value={"select * from technician where technicianname like '%${name}%'"})
	public List<Technician> queryOne(@Param("name") String name);
	
	

	@Select(value={"select technicianavatar from technician where technicianid=#{id}"})
	public String queryByIdAvatar(int id);
}
