package com.rj.bd.test;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.entity.User;
import com.rj.bd.mapper.IUserMapper;

 

@Controller
@RequestMapping("/user")
@ContextConfiguration(locations = "classpath:spring-common.xml")
 

public class Test {
	@Autowired
	private IUserMapper userMapper;
	
	@RequestMapping("/query")
	public String queryAll(){
		 List<User> list = userMapper.selectList(null);
		 
		 for (User user : list) {
				System.out.println(user);
				System.out.println("1");
			}
		return null;
		
		
	}
}
