package com.rj.bd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rj.bd.entity.User;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.mapper.IUserMapper;
import com.rj.bd.util.Json;

/**
 * @desc     获取用户
 * @author 齐云尧
 *
 */


@Controller
@RequestMapping("/user")
public class Users {
	@Autowired
	
	private IUserMapper userMapper;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) throws IOException {
		try {
			List<com.rj.bd.entity.User> list = userMapper.selectList(null);
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
		
	}
	
	
	
	
	@RequestMapping(value="/queryOne",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryOne(HttpServletRequest request) throws IOException {
		try {
			String name = request.getParameter("user");
			System.out.println(name);
			if(name.equals("") || name==null){
				try {
					List<com.rj.bd.entity.User> list = userMapper.selectList(null);
					return Json.MyPrint("200", "请求成功", list);
				} catch (Exception e) {
					return Json.MyPrint("-1", "非法调用", null);
				}
			}else{
				List<com.rj.bd.entity.User> list = userMapper.queryOne(name);
				return Json.MyPrint("200", "请求成功", list);
			}
		
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
		
	}
	
	
	
	

	@RequestMapping(value="/update",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateUser(HttpServletRequest request) throws IOException {
		try {
			User userentity = new User();
			int id = Integer.parseInt(request.getParameter("id"));
			userentity.setUser(request.getParameter("name"));
			userentity.setIdentitycard(request.getParameter("identitycard"));
			userentity.setTel(request.getParameter("tel"));
			userentity.setSex(request.getParameter("sex"));
			
			UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
			updateWrapper.eq("id",id);
			
			int no =userMapper.update(userentity, updateWrapper);
			if(no!=0){
				System.out.println("修改成功");
				return Json.MyPrint("200", "修改成功", null);
			}else{
				return Json.MyPrint("-1", "修改失败", null);
			}
		
		} catch (Exception e) {
			return Json.MyPrint("-2", "非法调用", null);
		}
		
	}
	
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteUser(HttpServletRequest request,int id) throws IOException {

				
			System.out.println(id);
			
			
//			UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
//			updateWrapper.eq("id",id);
//			
//			int no =userMapper.delete(updateWrapper);
//			if(no!=0){
//				System.out.println("删除成功");
//				return Json.MyPrint("200", "删除成功", null);
//			}else{
//				return Json.MyPrint("-1", "删除失败", null);
//			}
	
			return null;
	}
	
	
	
}
