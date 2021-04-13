package com.rj.bd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import com.rj.bd.entity.User;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.mapper.IUserMapper;
import com.rj.bd.util.Json;

/**
 * @desc     获取全部用户
 * @author 齐云尧
 *
 */


@Controller
@RequestMapping("/user")
public class user {
	@Autowired
	
	private IUserMapper userMapper;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletResponse response) throws IOException {
		List<User> list = userMapper.selectList(null);
		return Json.MyPrint("200", "请求成功", list);
	}
	
	
	
	
	@RequestMapping(value="/queryOne",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryOne(HttpServletRequest request) throws IOException {
		String name = request.getParameter("user");
		List<User> list = userMapper.queryOne(name);
		return Json.MyPrint("200", "请求成功", list);
	}
	
}
