package com.rj.bd.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


@Data
@TableName("technician")
public class Technician {

	
	public int technicianid;
	public String technicianname;
	public String technicianavatar;
	public int jobtime;
	public String techniciantemp;
	public int technicianstate;
	public String technicianheat;
	
}
