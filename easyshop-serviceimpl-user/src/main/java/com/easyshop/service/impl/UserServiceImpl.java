package com.easyshop.service.impl;

import com.easyshop.bean.User;
import com.easyshop.mapper.UserMapper;
import com.easyshop.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author bruce
 * @since 2018-12-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	UserService us;
}
