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

import com.rj.bd.entity.User;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.mapper.IUserMapper;
import com.rj.bd.util.Json;

/**
 * @desc     获取全部技师
 * @author 齐云尧
 *
 */

@Controller
@RequestMapping("/technician")
public class Technician {

	

		@Autowired
		private ITechnicianMapper technicianMapper;

		
		@RequestMapping(value="/query",method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> queryAll(HttpServletResponse response) throws IOException {
			try {
				List<com.rj.bd.entity.Technician> list = technicianMapper.selectList(null);
				return Json.MyPrint("200", "请求成功", list);
			} catch (Exception e) {
				return Json.MyPrint("-1", "非法调用", null);
			}
		
		}
		
		
		@RequestMapping(value="/queryOne",method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> queryOne(HttpServletRequest request) throws IOException {
			try {
				String name = request.getParameter("technicianname");
				List<com.rj.bd.entity.Technician> list = technicianMapper.queryOne(name);
				return Json.MyPrint("200", "请求成功", list);
			} catch (Exception e) {
				return Json.MyPrint("-1", "非法调用", null);
			}
			
		}
		
		
		
}


