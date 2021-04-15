package com.rj.bd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rj.bd.entity.Order;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.Technician;

import com.rj.bd.entity.User;
import com.rj.bd.mapper.IOrderMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.mapper.IUserMapper;
import com.rj.bd.util.Json;

/**
 * @desc     获取技师
 *
 */

@Controller
@RequestMapping("/technician")
public class Technicians {

	



		@Autowired
		private ITechnicianMapper technicianMapper;
		@Autowired 
		private IOrderMapper orderMapper;

		
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
		
		
		@RequestMapping(value="/queryById",method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> queryById(HttpServletRequest request,int id) throws IOException {
			try {
			
				List<com.rj.bd.entity.Technician> list = technicianMapper.queryById(id);
				return Json.MyPrint("200", "请求成功", list);
			} catch (Exception e) {
				return Json.MyPrint("-1", "非法调用", null);
			}
			
		}
		
		
		
		
		

		@RequestMapping(value="/querybyidone",method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> queryByIdOne(HttpServletRequest request,int technicianid) throws IOException {
			
					try {
						List<Technician> list = technicianMapper.queryById(technicianid);
						
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
		
		

		@RequestMapping(value="/update",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> update(HttpServletRequest request,int technicianid, String technicianname,MultipartFile technicianavatar, int jobtime , String techniciantemp,int technicianstate) throws IOException {
			
				//获取文件的文件名字
				String fileName = technicianavatar.getOriginalFilename();
				System.out.println(fileName);
				
				String path = this.getClass().getClassLoader().getResource("/").getPath()+"img";
				File uploadDir = new File(path);
				if(!uploadDir.exists()){
					uploadDir.mkdir();
		        }
				System.out.println(path);
				//构建一个完整的文件上传对象
		        File uploadFile = new File(path + "/" + fileName);
		        System.out.println(path + "/" + fileName);
		        //判断文件是否存在
		        if(!uploadFile.exists()) {
		            try {
		                //通过transferTo方法进行上传
		            	technicianavatar.transferTo(uploadFile);
		               
		            } catch (IOException e) {
		            	return Json.MyPrint("-2", "存入图片失败", null);
		            }
		        }
		        int id = technicianid;
		        Technician technician = new Technician();
		        technician.setJobtime(jobtime);
		        technician.setTechnicianavatar(fileName);
		        
		        technician.setTechnicianname(technicianname);
		        technician.setTechnicianstate(technicianstate);
		        technician.setTechniciantemp(techniciantemp);
		        
		        UpdateWrapper<Technician>updateWrapper = new UpdateWrapper<Technician>();
		        updateWrapper.eq("technicianid",id);
		        technicianMapper.update(technician, updateWrapper);
		        
		  
			return Json.MyPrint("200", "修改成功", null);
			
		}
		
	
		
		
		
		@RequestMapping(value="/delete",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
			try {
			
				int technicianid = Integer.parseInt(request.getParameter("technicianid"));
				
				UpdateWrapper<Technician> updateWrapper = new UpdateWrapper<Technician>();
				updateWrapper.eq("technicianid",technicianid);
				
				int no =technicianMapper.delete(updateWrapper);
				if(no!=0){
					
					UpdateWrapper<Order> updateWrapper2 = new UpdateWrapper<Order>();
					updateWrapper2.eq("technicianid",technicianid);
					orderMapper.delete(updateWrapper2);
					
					
					System.out.println("删除成功");
					
					return Json.MyPrint("200", "删除成功", null);
				}else{
					return Json.MyPrint("-1", "删除失败", null);
				}
			
			} catch (Exception e) {
				return Json.MyPrint("-2", "非法调用", null);
			}
			
		}
		
		
		
		
		
		@RequestMapping(value="/add",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> add(HttpServletResponse response , String technicianname,MultipartFile technicianavatar, int jobtime , String techniciantemp,HttpServletRequest request ) throws IOException {
			
			
			System.out.println(technicianname);
			System.out.println(technicianavatar);

			System.out.println(jobtime);
			System.out.println(techniciantemp);
			
			//获取文件的文件名字
			String fileName = technicianavatar.getOriginalFilename();
			System.out.println(fileName);
			
			String path = this.getClass().getClassLoader().getResource("/").getPath()+"img";
			File uploadDir = new File(path);
			if(!uploadDir.exists()){
				uploadDir.mkdir();
	        }
			System.out.println(path);
			//构建一个完整的文件上传对象
	        File uploadFile = new File(path + "/" + fileName);
	        System.out.println(path + "/" + fileName);
	        //判断文件是否存在
	        if(!uploadFile.exists()) {
	            try {
	                //通过transferTo方法进行上传
	            	technicianavatar.transferTo(uploadFile);
	               
	            } catch (IOException e) {
	            	return Json.MyPrint("-1", "添加失败", null);
	            }
	        }
	        
	        Technician technician = new Technician();
	        technician.setJobtime(jobtime);
	        technician.setTechnicianavatar(fileName);
	        technician.setTechnicianheat(0);
	        technician.setTechnicianname(technicianname);
	        technician.setTechnicianstate(0);
	        technician.setTechniciantemp(techniciantemp);
	        technicianMapper.insert(technician);
	        return Json.MyPrint("200", "添加成功", null);
			
		}
		
		
		
		
		@RequestMapping("/download")
		@ResponseBody
	    public Map<String, Object> downloads(HttpServletResponse response , HttpServletRequest request ,int id) throws Exception{
	        //要下载的图片地址
			
			System.out.println(id);
			String path = this.getClass().getClassLoader().getResource("/").getPath()+"img";
			String fileName = "4.jpg";
			if( technicianMapper.queryByIdAvatar(id) == null){
				fileName ="4.jpg";
			} else{
				fileName = technicianMapper.queryByIdAvatar(id);
			}
	       
	       System.out.println(fileName);
	        //1、设置response 响应头
	        response.reset(); //设置页面不缓存,清空buffer
	        response.setCharacterEncoding("UTF-8"); //字符编码
	        response.setContentType("multipart/form-data"); //二进制传输数据
	      

	        File file = new File(path,fileName);
	        //2、 读取文件--输入流
	        InputStream input=new FileInputStream(file);
	        //3、 写出文件--输出流
	        OutputStream out = response.getOutputStream();

	        byte[] buff =new byte[1024];
	        int index=0;
	        //4、执行 写出操作
	        while((index= input.read(buff))!= -1){
	            out.write(buff, 0, index);
	            out.flush();
	        }
	        out.close();
	        input.close();
	        return Json.MyPrint("200", "图片加载成功", null);
	    }
		
}


