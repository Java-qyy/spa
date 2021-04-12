package com.rj.bd.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("`order`")
public class Order {
	public int orderid;
	public int userid;
	public int serveid;
	public int technicianid;
	public String ordertime;
	public int orderstate;
}
