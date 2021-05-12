package com.easyshop.service.impl;

import com.easyshop.bean.Content;
import com.easyshop.mapper.ContentMapper;
import com.easyshop.service.ContentService;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pursuelin
 * @since 2018-12-06
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

	@Autowired
	ContentService cs;
}
