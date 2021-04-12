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

import com.rj.bd.Json;
import com.rj.bd.entity.User;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.mapper.IUserMapper;

public class Technician {
	@Controller
	@RequestMapping("/technician")
	public class user {
		@Autowired
		private ITechnicianMapper technicianMapper;

		
		@RequestMapping(value="/query",method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> queryAll(HttpServletResponse response) throws IOException {
			List<com.rj.bd.entity.Technician> list = technicianMapper.selectList(null);
			return Json.MyPrint(200, "请求成功", list);
		}
	}

}
