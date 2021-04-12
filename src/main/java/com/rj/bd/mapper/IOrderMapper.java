package com.rj.bd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.entity.Order;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.User;

public interface IOrderMapper extends BaseMapper<Order>{
	@Select(value={"select * from `order`"})
	public List<Order> queryAll();
}
