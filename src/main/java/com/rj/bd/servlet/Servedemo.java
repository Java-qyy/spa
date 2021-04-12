package com.rj.bd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.rj.bd.mapper.IServedemoMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.util.Json;

@Controller
@RequestMapping("/servedemo")
public class Servedemo {
	@Autowired
	private IServedemoMapper servedemoMapper;

	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletResponse response) throws IOException {
		List<com.rj.bd.entity.Servedemo> list = servedemoMapper.selectList(null);
		return Json.MyPrint(200, "请求成功", list);
	}
}
