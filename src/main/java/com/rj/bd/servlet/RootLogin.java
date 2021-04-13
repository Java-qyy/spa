package com.rj.bd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.mapper.IOrderMapper;
import com.rj.bd.mapper.IRootLoginMapper;
import com.rj.bd.util.Json;

@Controller
@RequestMapping("/rootlogin")
public class RootLogin {

	@Autowired
	private IRootLoginMapper rootLoginMapper;
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) throws IOException {
		try {
			List<Map<String, Object>> list = rootLoginMapper.query();
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);		
		}
		
		
	}
}
