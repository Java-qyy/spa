package com.rj.bd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rj.bd.entity.Order;
import com.rj.bd.entity.Servedemo;
import com.rj.bd.entity.Technician;
import com.rj.bd.entity.User;
import com.rj.bd.mapper.IOrderMapper;
import com.rj.bd.mapper.IServedemoMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.util.Json;

/**
 * @desc    服务项目
 *
 */


@Controller
@RequestMapping("/servedemo")
public class Servedemos {
	@Autowired
	private IServedemoMapper servedemoMapper;
	@Autowired 
	private IOrderMapper orderMapper;
	
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
	
	
	@RequestMapping(value="/queryOne",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryOne(HttpServletResponse response, String serve) throws IOException {
		
			List<com.rj.bd.entity.Servedemo> list = servedemoMapper.queryOne(serve);
			return Json.MyPrint("200", "请求成功", list);
		
		
	}
	
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request,String serve,String servemoney,String servetime,MultipartFile serveavatar) throws IOException {
		
		
			com.rj.bd.entity.Servedemo servedemo = new com.rj.bd.entity.Servedemo();
			//获取文件的文件名字
			String fileName = serveavatar.getOriginalFilename();
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
	            	serveavatar.transferTo(uploadFile);
	               
	            } catch (IOException e) {
	            	return Json.MyPrint("-2", "上传图片失败", null);
	            }
	        }
			servedemo.setServe(serve);
			servedemo.setServeavatar(fileName);
			servedemo.setServemoney(servemoney);
			servedemo.setServetime(servetime);
			servedemo.setServenumber(0);
			int addno = servedemoMapper.insert(servedemo);
			if(addno!=0){
				return Json.MyPrint("200", "添加成功", null);
			}else{
				return Json.MyPrint("-1", "添加失败", null);
			}
	
		
		
	}
	
	
	
	
	
	
	@RequestMapping("/download")
	@ResponseBody
    public Map<String, Object> downloads(HttpServletResponse response , HttpServletRequest request ,int serveid) throws Exception{
        //要下载的图片地址
		
		System.out.println(serveid);
		String path = this.getClass().getClassLoader().getResource("/").getPath()+"img";
		String fileName = "4.jpg";
		if( servedemoMapper.queryByIdAvatar(serveid) == null){
			fileName ="4.jpg";
		} else{
			fileName = servedemoMapper.queryByIdAvatar(serveid);
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
	
	
	
	
	
	@RequestMapping(value="/querybyidone",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryByIdOne(HttpServletRequest request,int serveid) throws IOException {
		
				try {
					List<Servedemo> list = servedemoMapper.queryById(serveid);
					return Json.MyPrint("200", "请求成功", list);
				} catch (Exception e) {
					return Json.MyPrint("-1", "非法调用", null);
				}
			}
	
	
	
	
	
	

	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUser(HttpServletRequest request,int serveid,String serve,String servemoney,String servetime,MultipartFile serveavatar) throws IOException {
	
			
			com.rj.bd.entity.Servedemo servedemo = new com.rj.bd.entity.Servedemo();
			
			UpdateWrapper<Servedemo> updateWrapper = new UpdateWrapper<Servedemo>();
			updateWrapper.eq("serveid",serveid);
			//获取文件的文件名字
			String fileName = serveavatar.getOriginalFilename();
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
	            	serveavatar.transferTo(uploadFile);
	               
	            } catch (IOException e) {
	            	return Json.MyPrint("-2", "上传图片失败", null);
	            }
	        }
			servedemo.setServe(serve);
			servedemo.setServeavatar(fileName);
			servedemo.setServemoney(servemoney);
			servedemo.setServetime(servetime);
	
			int addno = servedemoMapper.insert(servedemo);
			int no =servedemoMapper.update(servedemo, updateWrapper);
			if(no!=0){
				System.out.println("修改成功");
				return Json.MyPrint("200", "修改成功", null);
			}else{
				return Json.MyPrint("-1", "修改失败", null);
			}
		
	
		
	}
	
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteUser(HttpServletRequest request,int serveid) throws IOException {
		try {
			
	
			
			UpdateWrapper<Servedemo> updateWrapper = new UpdateWrapper<Servedemo>();
			updateWrapper.eq("serveid",serveid);
			
			int no =servedemoMapper.delete(updateWrapper);
			if(no!=0){
				System.out.println("删除成功");
				
				UpdateWrapper<Order> updateWrapper2 = new UpdateWrapper<Order>();
				updateWrapper2.eq("serveid",serveid);
				orderMapper.delete(updateWrapper2);
				
				return Json.MyPrint("200", "删除成功", null);
			}else{
				return Json.MyPrint("-1", "删除失败", null);
			}
		
		} catch (Exception e) {
			return Json.MyPrint("-2", "非法调用", null);
		}
		
	}
	
	
}
