package com.easyshop.service.impl;

import com.easyshop.bean.Brand;
import com.easyshop.mapper.BrandMapper;
import com.easyshop.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pursuelin
 * @since 2018-12-05
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

	@Autowired
	BrandService bs;
}
