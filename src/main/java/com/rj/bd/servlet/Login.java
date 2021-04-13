package com.rj.bd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.entity.Root;
import com.rj.bd.mapper.IRootMapper;
import com.rj.bd.util.Json;

/**
 * @desc     登陆
 * @author 齐云尧
 *
 */
@Controller
@RequestMapping("/root")
public class Login {
	
	@Autowired
	private IRootMapper rootMapper;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(@Param("rootuser")   HttpServletRequest request) throws IOException {
		try {
			String rootuser = request.getParameter("rootuser");
			String rootpassword = request.getParameter("rootpassword");
			
			List<Root> list = rootMapper.selectquery(rootuser,rootpassword);
			System.out.println(list);
			if(list==null || list.size()==0){
				return Json.MyPrint("-1", "请输入正确的账号或密码", null);
			}else{
				return Json.MyPrint("200", "登陆成功", null);
			}
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
			
		
		
		
		
	}
}
