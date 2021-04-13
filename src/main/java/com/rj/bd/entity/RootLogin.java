package com.rj.bd.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("rootlogin")
public class RootLogin {

	public int rootloginid;
	public int rootid;
	public String logintime;
	public String rootip;
	public String temp;
}
