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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.entity.Technician;
import com.rj.bd.entity.Technicianpost;
import com.rj.bd.entity.User;
import com.rj.bd.mapper.IServedemoMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.mapper.ITechnicianPostMapper;
import com.rj.bd.mapper.IUserMapper;
import com.rj.bd.util.Json;

import lombok.experimental.var;


/**
 * @desc    获取全部评论
 *
 */


@Controller
@RequestMapping("/technicianpost")
public class TechnicianPost {
	
	@Autowired
	private ITechnicianPostMapper technicianPostMapper;
	@Autowired
	private ITechnicianMapper technicianMapper;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request,int page , int size) throws IOException {
	
		page= (page-1) * 10;
		List<Map<String, Object>> list = technicianPostMapper.queryMoreAll(page,size);
		return Json.MyPrint("200", "请求成功", list);
	
		
	}
	
	
	@RequestMapping(value="/queryOne",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryOne(HttpServletRequest request,int page , int size, String technicianname) throws IOException {
	
		page= (page-1) * 10;
		List<Map<String, Object>> list = technicianPostMapper.queryOne( technicianname,page, size);
		return Json.MyPrint("200", "请求成功", list);

		
	}
	
	
	
	
	
	
	@RequestMapping(value="/totel",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryOne() throws IOException {

		
		Object list = technicianPostMapper.queryTotal();
		return Json.MyPrint("200", "请求成功", list);
	
		
	}
	
	
	
	@RequestMapping(value="/toteltwo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryTwo(String technicianname) throws IOException {

		
		List<Technician> list = technicianMapper.queryOne(technicianname);
	
		int totel=0;
		for (Technician technician : list) {
			totel= totel+technicianPostMapper.queryTotalTwo(technician.getTechnicianid());
		}
		return Json.MyPrint("200", "请求成功", totel);
	
		
	}
	
	
	
	
}
