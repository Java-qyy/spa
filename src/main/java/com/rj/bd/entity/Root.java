package com.rj.bd.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("root")
public class Root {
	public int rootid;
	public String rootuser;
	public String rootpassword;
	public String token;
	
}
