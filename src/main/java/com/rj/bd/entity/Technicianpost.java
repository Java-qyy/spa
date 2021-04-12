package com.rj.bd.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("technicianpost")
public class Technicianpost {
	public int postid;
	public int userid;
	public int technicianid;
	public String postcontent;
	public int posttime;
}
