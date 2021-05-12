package com.easyshop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.easyshop.MyApplication;
import com.easyshop.bean.User;
import com.easyshop.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MyApplication.class)
public class TestEasyShop {

	@Autowired
	UserMapper um;
	
	@Test
	public void test1() {
		User user = um.selectById(9);
		System.err.println(user);
	}
	
}
