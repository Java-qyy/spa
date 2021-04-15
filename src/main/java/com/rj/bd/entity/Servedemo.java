package com.rj.bd.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("servedemo")
public class Servedemo {

	public int serveid;
	public String serve;
	public String serveavatar;
	public String servemoney;
	public String servetime;
	public int servenumber;
	 
}
