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

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.Technician;
import com.rj.bd.entity.User;
import com.rj.bd.mapper.IServedemoMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.util.Json;

/**
 * @desc    服务项目
 * 
 * @author 齐云尧
 *
 */


@Controller
@RequestMapping("/servedemo")
public class Servedemos {
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
	
	
	
	

	@RequestMapping(value="/update",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateUser(HttpServletRequest request) throws IOException {
		try {
			Servedemo servedemo = new Servedemo();
			int serveid = Integer.parseInt(request.getParameter("serveid"));
			servedemo.setServe(request.getParameter("serve"));
			servedemo.setServemoney(request.getParameter("servemoney"));
			servedemo.setServetime(request.getParameter("servetime"));
	
			
			UpdateWrapper<Servedemo> updateWrapper = new UpdateWrapper<Servedemo>();
			updateWrapper.eq("serveid",serveid);
			
			int no =servedemoMapper.update(servedemo, updateWrapper);
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
	
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteUser(HttpServletRequest request) throws IOException {
		try {
			
			int serveid = Integer.parseInt(request.getParameter("serveid"));
			
			UpdateWrapper<Servedemo> updateWrapper = new UpdateWrapper<Servedemo>();
			updateWrapper.eq("serveid",serveid);
			
			int no =servedemoMapper.delete(updateWrapper);
			if(no!=0){
				System.out.println("删除成功");
				return Json.MyPrint("200", "删除成功", null);
			}else{
				return Json.MyPrint("-1", "删除失败", null);
			}
		
		} catch (Exception e) {
			return Json.MyPrint("-2", "非法调用", null);
		}
		
	}
	
	
}
