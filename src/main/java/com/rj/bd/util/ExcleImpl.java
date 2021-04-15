package com.rj.bd.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rj.bd.entity.RootLogin;
import com.rj.bd.mapper.IRootLoginMapper;

@Controller
public class ExcleImpl {
	
	
	@Autowired
	private IRootLoginMapper rootLoginMapper;
	
	public  void export(String[] titles, ServletOutputStream out) throws Exception{
	    try{
	    				RootLogin rootLogin = new RootLogin();
	    				
	    				
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
	                 }        
}
