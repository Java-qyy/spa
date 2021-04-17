package com.rj.bd.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.rj.bd.entity.Order;
import com.rj.bd.entity.OrderState;

import com.rj.bd.mapper.IOrderMapper;
import com.rj.bd.mapper.ITechnicianMapper;
import com.rj.bd.util.Json;
/**
 * 
 * @desc    获取指定订单
 *
 */

@Controller
@RequestMapping("/order")
public class Orders {
	
	
	@Autowired 
	private IOrderMapper orderMapper;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request, int page,int size) throws IOException {
		try {
			page=(page-1)*10;
			
			List<Map<String, Object>> list = orderMapper.query(page,size);
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
	}
	
	
	
	@RequestMapping(value="/queryOne",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryOne(HttpServletRequest request ,int orderstate,int page, int size) throws IOException {
			page=(page-1)*5;
			List<Map<String, Object>> list = orderMapper.queryOne(orderstate , page,size);
			return Json.MyPrint("200", "请求成功", list);
		
		
	}
	
	
	@RequestMapping(value="/state",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> state( HttpServletRequest request){
		OrderState orderState = new OrderState();
		int orderstate = 0;
		if(request.getParameter("orderstate")==null){
			int state=3;
			orderstate= state;
		}else{
			orderstate= Integer.parseInt( request.getParameter("orderstate"));
		}
		
		System.out.println(orderstate);
		if(orderstate == 0){
			orderState.setYuyue(orderMapper.queryStateY());
			return Json.MyPrint("200", "请求成功", orderState);
		}else if(orderstate == 1){
			orderState.setProceed(orderMapper.queryStateP());
			return Json.MyPrint("200", "请求成功", orderState);
		}else if(orderstate == 2){
			orderState.setAccomplish(orderMapper.queryStateC());
			return Json.MyPrint("200", "请求成功", orderState);
		}
		
		
		orderState.setAll(orderMapper.queryStateA());
		orderState.setProceed(orderMapper.queryStateP());
		orderState.setAccomplish(orderMapper.queryStateC());
		orderState.setYuyue(orderMapper.queryStateY());
		return Json.MyPrint("200", "请求成功", orderState);
		
		
	}
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value="/echart",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryEchart(HttpServletRequest request) throws IOException {
		try {
			
			Date firstDay = getBeforeOrAfterDate(new Date(),-6);
	        Date lastDay = getBeforeOrAfterDate(new Date(),-0);

	        String first = sdf.format(firstDay);
	        String end = sdf.format(lastDay);
	        List<Map<String, Object>> list = orderMapper.queryNumber(first, end);
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
		
	}

	

	@RequestMapping(value="/echarttwo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryEcharttwo(HttpServletRequest request) throws IOException {
		try {
			
			Date firstDay = getBeforeOrAfterDate(new Date(),-5);
	        Date lastDay = getBeforeOrAfterDate(new Date(),-0);

	        String first = sdf.format(firstDay);
	        String end = sdf.format(lastDay);
	        List<Map<String, Object>> list = orderMapper.queryshouru(first, end);
			return Json.MyPrint("200", "请求成功", list);
		} catch (Exception e) {
			return Json.MyPrint("-1", "非法调用", null);
		}
		
	}
	
	
	
	  public static Date getBeforeOrAfterDate(Date date, int num) {
	        Calendar calendar = Calendar.getInstance();//获取日历
	        calendar.setTime(date);//当date的值是当前时间，则可以不用写这段代码。
	        calendar.add(Calendar.DATE, num);
	        Date d = calendar.getTime();//把日历转换为Date
	        return d;
	    }
	
	  
	  
	  
	  
	  @RequestMapping(value="/excel",method=RequestMethod.GET)    
		@ResponseBody
		//获取url链接上的参数
		public  Map<String, Object> dowm(HttpServletResponse response){
		     response.setContentType("application/binary;charset=UTF-8");
		              try{
		                  ServletOutputStream out=response.getOutputStream();
		      
		               
		                  String[] titles = { "用户名称", "技师名称", "服务名称", "订单金额","订单时间","订单状态" }; 
		                  try{
			    				Order order = new Order();
			    				
			    				//设置文件头：最后一个参数是设置下载文件名
			                      response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("订单"+".xls", "UTF-8"));
			    				
			    				
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
			                      System.out.println(orderMapper.querymany());
			                      list = orderMapper.querymany();
			                     
			                         for (int i = 0; i < list.size(); i++) {
			                             row = hssfSheet.createRow(i+1);                
			                             Map<String, Object> order1 = list.get(i);
			                             
			                             // 第六步，创建单元格，并设置值
			                               String user =null;
			                            
			                             if(order1.get("user") != null){
			                            	 user =(String) order1.get("user");
			                             }
			                            row.createCell(0).setCellValue(user);
			                             String technicianname = "";
			                             if(order1.get("technicianname") != null){
			                            	 technicianname =(String) order1.get("technicianname");
			                             }
			                            row.createCell(1).setCellValue(technicianname);
			                        
			                             String serve = "";
			                             if(order1.get("serve") != null){
			                            	 serve = (String) order1.get("serve");
			                             }
			                             row.createCell(2).setCellValue(serve);
			                             String money=null;
			                             if( order1.get("money") !=null){
			                            	 money =  (String) order1.get("money");
			                             }
			                             row.createCell(3).setCellValue(money);
			                             String ordertime=null;
			                             if( order1.get("ordertime") !=null){
			                            	 ordertime =  (String) order1.get("ordertime");
			                             }
			                             row.createCell(4).setCellValue(ordertime);
			                             int orderstate=0;
			                             if( order1.get("orderstate") !=null){
			                            	 orderstate =  (int) order1.get("orderstate");
			                             }
			                             row.createCell(5).setCellValue(orderstate);
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
		
	  
	  
	  
	  
	  
		@RequestMapping(value="/querymore",method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> querymore(HttpServletRequest request ,String user,String technicianname,String serve,String ordertime,int orderstate,int page,int size) throws IOException {
				if(user==null){
					user="";
				}
				if(serve==null){
					serve="";
				}
				if(ordertime==null ){
					ordertime="";
				}
				
				if(technicianname==null){
					technicianname="";
				}
			
				page=(page-1)*10;
				if(orderstate==3){
					List<Map<String, Object>> list = orderMapper.querymore(user,technicianname,serve,ordertime,page,size);
					return Json.MyPrint("200", "请求成功", list);
				}else{
					List<Map<String, Object>> list = orderMapper.querymorestate(user,technicianname,serve,ordertime,orderstate,page,size);
					return Json.MyPrint("200", "请求成功", list);
				}
	
		}
	  
	  
	  

		@RequestMapping(value="/querytotel",method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> querytotel(HttpServletRequest request ,String user,String technicianname,String serve,String ordertime,int orderstate) throws IOException {
				if(user==null){
					user="";
				}
				if(serve==null){
					serve="";
				}
				if(ordertime==null){
					ordertime="";
				}
				if(technicianname==null){
					technicianname="";
				}
			
				
				if(orderstate==3){
					int list = orderMapper.querymoretotel(user,technicianname,serve,ordertime);
					return Json.MyPrint("200", "请求成功", list);
				}else{
					int list = orderMapper.querymorestatetotel(user,technicianname,serve,ordertime,orderstate);
					return Json.MyPrint("200", "请求成功", list);
				}
				
				
			
			
		}
		
		
	  
	  
	
}
	
	

