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
import com.rj.bd.Json;
import com.rj.bd.entity.User;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.mapper.IUserMapper;

@Controller
@RequestMapping("/user")
public class user {
	@Autowired
	
	private IUserMapper userMapper;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll2(HttpServletResponse response) throws IOException {
		List<User> list = userMapper.selectList(null);
		return Json.MyPrint(200, "请求成功", list);
	}
}
