package com.rj.bd.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rj.bd.entity.Root;
import com.rj.bd.entity.RootLogin;
import com.rj.bd.mapper.IRootLoginMapper;
import com.rj.bd.mapper.IRootMapper;
import com.rj.bd.util.IPUtils;
import com.rj.bd.util.Json;
import com.rj.bd.util.TokenUtil;

/**
 * @desc     登陆
 *
 */
@Controller
@RequestMapping("/root")
public class Login {
	
	@Autowired
	private IRootMapper rootMapper;
	@Autowired
	private IRootLoginMapper rootLoginMapper;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(@Param("rootuser")   HttpServletRequest request) throws IOException {
		
			String rootuser = request.getParameter("rootuser");
			String rootpassword = request.getParameter("rootpassword");
			
			List<Root> list = rootMapper.selectquery(rootuser,rootpassword);
			
			System.out.println(list);
			if(list==null || list.size()==0){
				return Json.MyPrint("-1", "请输入正确的账号或密码", null);
			}else{
				RootLogin rootLogin =new RootLogin();
				Root login = new Root();
				UpdateWrapper<Root> updateWrapper = new UpdateWrapper<Root>();
				for (Root root : list) {
					 rootLogin.setRootid(root.getRootid()); 
					 Date d = new Date();
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 rootLogin.setLogintime(sdf.format(d)); 
					 String rootip = IPUtils.getClientIpAddr(request);
					 rootLogin.setRootip(rootip);
					int on = rootLoginMapper.insert(rootLogin);
					if(on!=0){
						System.out.println("日志添加成功");
					}
					
					String token = root.getToken();
					int rootid = root.getRootid();
					updateWrapper.eq("rootid", rootid);
					System.out.println("token:"+token);
					String newtoken =TokenUtil.getToken(rootuser,rootpassword);
					login.setToken(newtoken);
					login.setRootid(rootid);
					System.out.println("newtoken:"+newtoken);
					int no =rootMapper.update(login, updateWrapper);
					if(no!=0){
						System.out.println("更新成功");
					}
				}
				List<Root> list2 = rootMapper.selectquery(rootuser,rootpassword);
				
				
				
				return Json.MyPrint("200", "登陆成功", list2);
			}

		
	}
	
	@RequestMapping(value="/token",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> token(HttpServletRequest request , String token,String id) throws IOException {
		
					int list = rootMapper.queryToken(token,id);
					if(list!=0){
						return Json.MyPrint("200", "请求成功", null);
					}else{
						return Json.MyPrint("-1", "登陆失效，请重新登陆", null);
					}
					
		
	}
	
	
	
	
	
}
