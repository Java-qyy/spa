package com.rj.bd.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("user")
public class User {


	public int id;
	public String user;
	public String sex;
	public String tel;
	public String identitycard;
}
