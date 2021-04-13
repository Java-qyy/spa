package com.rj.bd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.rj.bd.mapper.IServedemoMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.util.Json;

/**
 * @desc    获取全部服务项目
 * 
 * @author 全部
 *
 */


@Controller
@RequestMapping("/servedemo")
public class Servedemo {
	@Autowired
	private IServedemoMapper servedemoMapper;

	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletResponse response) throws IOException {
		try {
			List<com.rj.bd.entity.Servedemo> list = servedemoMapper.selectList(null);
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
		
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) throws IOException {
		
		try {
			com.rj.bd.entity.Servedemo servedemo = new com.rj.bd.entity.Servedemo();
			
			servedemo.setServe(request.getParameter("serve"));
			servedemo.setServemoney(request.getParameter("servemoney"));
			servedemo.setServetime(request.getParameter("servetime"));
			servedemo.setServenumber(0);
			int addno = servedemoMapper.insert(servedemo);
			if(addno!=0){
				return Json.MyPrint("200", "请求成功", null);
			}else{
				return Json.MyPrint("-1", "请求失败", null);
			}
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
		
		
	}
	
	
	
	
}
