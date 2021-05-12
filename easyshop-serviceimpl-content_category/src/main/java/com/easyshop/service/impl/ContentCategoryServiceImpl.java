package com.easyshop.service.impl;

import com.easyshop.bean.ContentCategory;
import com.easyshop.mapper.ContentCategoryMapper;
import com.easyshop.service.ContentCategoryService;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author pursuelin
 * @since 2018-12-06
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {

	@Autowired
	ContentCategoryService ccs;
}
