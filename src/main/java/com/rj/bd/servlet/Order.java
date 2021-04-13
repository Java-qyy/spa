package com.rj.bd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.mapper.IOrderMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.util.Json;
/**
 * 
 * @desc    获取指定订单
 * @author 齐云尧
 *
 */

@Controller
@RequestMapping("/order")
public class Order {
	
	
	@Autowired 
	private IOrderMapper orderMapper;
	
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) throws IOException {
		try {
			int state = Integer.parseInt(request.getParameter("orderstate"));
			
			List<Map<String, Object>> list = orderMapper.queryOne(state);
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
		
	}
}
