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
 * @author 齐云尧
 *
 */


@Controller
@RequestMapping("/technicianpost")
public class TechnicianPost {
	
	@Autowired
	private ITechnicianPostMapper technicianPostMapper;

	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletResponse response) throws IOException {
	try {
		List<Map<String, Object>> list = technicianPostMapper.queryMoreAll();
		return Json.MyPrint("200", "请求成功", list);
	} catch (Exception e) {
		return Json.MyPrint("-1", "非法调用", null);
	}
		
	}
}
