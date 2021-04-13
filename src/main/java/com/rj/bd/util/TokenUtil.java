package com.rj.bd.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.rj.bd.mapper.IRootLoginMapper;
import com.rj.bd.mapper.IRootMapper;

/**
 * @desc     token工具类
 * @author 齐云尧
 *
 */
public class TokenUtil {

	/**
	 * @desc    生成新的token
	 * @param rootuser
	 * @param rootpassword
	 * @return
	 */
	public static String getToken(String rootuser , String rootpassword){
		String data = UUID.randomUUID().toString()+rootuser+rootpassword;
		
		return data;
		
	}
	
}
