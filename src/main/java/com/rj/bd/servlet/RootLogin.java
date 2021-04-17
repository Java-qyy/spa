package com.rj.bd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.rj.bd.mapper.IOrderMapper;
import com.rj.bd.mapper.IRootLoginMapper;
import com.rj.bd.util.ExcleImpl;
import com.rj.bd.util.Json;

/**
 * @desc     获取登陆日志
 *
 */

@Controller
@RequestMapping("/rootlogin")
public class RootLogin {

	@Autowired
	private IRootLoginMapper rootLoginMapper;
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request,int page,int size) throws IOException {
		try {
			page = (page-1)*10;
			List<Map<String, Object>> list = rootLoginMapper.query(page,size);
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);		
		}
		
		
	}
	
	
	@RequestMapping(value="/querytotel",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> querytotel() throws IOException {
		try {

			int list = rootLoginMapper.querytotel();
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);		
		}
		
		
	}
	
	@RequestMapping(value="/excel",method=RequestMethod.GET)    
	@ResponseBody
	//获取url链接上的参数
	public  Map<String, Object> dowm(HttpServletResponse response){
	     response.setContentType("application/binary;charset=UTF-8");
	              try{
	                  ServletOutputStream out=response.getOutputStream();
	      
	               
	                  String[] titles = { "管理员id", "管理员姓名", "登陆的时间", "登陆的ip" }; 
	                  try{
		    				RootLogin rootLogin = new RootLogin();
		    				//设置文件头：最后一个参数是设置下载文件名
		                      response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("日志"+".xls", "UTF-8"));
		    				
		                     // 第一步，创建一个workbook，对应一个Excel文件
		                     HSSFWorkbook workbook = new HSSFWorkbook();
		                     
		                     // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		                     HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		                     
		                     // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		                     
		                     HSSFRow row = hssfSheet.createRow(0);
		                    // 第四步，创建单元格，并设置值表头 设置表头居中
		                     HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		                     
		                     //居中样式
		                     hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		         
		                     HSSFCell hssfCell = null;
		                     for (int i = 0; i < titles.length; i++) {
		                         hssfCell = row.createCell(i);//列索引从0开始
		                         hssfCell.setCellValue(titles[i]);//列名1
		                         hssfCell.setCellStyle(hssfCellStyle);//列居中显示                
		                     }
		                     
		                     // 第五步，写入实体数据 
		                      //这里我把list当做数据库啦
		                      List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		                      System.out.println(rootLoginMapper.querysy());
		                      list = rootLoginMapper.querysy();
		                     
		                         for (int i = 0; i < list.size(); i++) {
		                             row = hssfSheet.createRow(i+1);                
		                             Map<String, Object> rootLogin1 = list.get(i);
		                             
		                             // 第六步，创建单元格，并设置值
		                               int id =0;
		                            
		                             if(rootLogin1.get("rootid") != null){
		                            	   id =(int) rootLogin1.get("rootid");
		                             }
		                            row.createCell(0).setCellValue(id);
		                             String rootuser = "";
		                             if(rootLogin1.get("rootuser") != null){
		                            	 rootuser =(String) rootLogin1.get("rootuser");
		                             }
		                            row.createCell(1).setCellValue(rootuser);
		                             String logintime = "";
		                             if(rootLogin1.get("logintime") != null){
		                            	 logintime = (String) rootLogin1.get("logintime");
		                             }
		                             row.createCell(2).setCellValue(logintime);
		                             String rootip=null;
		                             if( rootLogin1.get("rootip") !=null){
		                            	 rootip =  (String) rootLogin1.get("rootip");
		                             }
		                             row.createCell(3).setCellValue(rootip);
		                         }
		    
		                     // 第七步，将文件输出到客户端浏览器
		                     try {
		                         workbook.write(out);
		                         out.flush();
		                        out.close();
		         
		                     } catch (Exception e) {
		                         e.printStackTrace();
		                     }
		                 }catch(Exception e){
		                     e.printStackTrace();
		                    throw new Exception("导出信息失败！");
		                    
		                    }  
	                  return Json.MyPrint("200", "导出成功", null);
	              } catch(Exception e){
	                  e.printStackTrace();
	                  return Json.MyPrint("-1", "导出失败", null);	
	              }
	          }
	
	
	
	
}
