package com.easyshop.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.easyshop.bean.Brand;
import com.easyshop.bean.Content;
import com.easyshop.bean.ContentCategory;
import com.easyshop.service.ContentCategoryService;
import com.easyshop.service.ContentService;
import com.easyshop.utils.PageHelper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pursuelin
 * @since 2018-12-06
 */
@Controller
@Scope("prototype")
@RequestMapping("/content")
public class ContentController {

	@Reference
	ContentService cs;
	
	@Reference
	ContentCategoryService ccs;
	
	@RequestMapping("/list/{pageIndex}")
	public String list(Model model, @PathVariable(name = "pageIndex") Integer pageIndex,
			@RequestParam(defaultValue = "3") Integer pageSize) {

		// 分页工具
		Page<Content> page = new Page<Content>(pageIndex, pageSize);
		EntityWrapper<Content> entityWrapper = new EntityWrapper<Content>();

		// 查询的数据结果集对象 查询条件
		entityWrapper.eq("del", 0);
		Page<Content> result = cs.selectPage(page, entityWrapper);
		// 获得总数
		int totalCount = ((Long) result.getTotal()).intValue();
		// 查询出每页数据
		List<Content> contents = result.getRecords();
		for (Content content : contents) {
			Long categoryId = content.getCategoryId();
			ContentCategory category = ccs.selectById(categoryId);
			content.setContentCategory(category);
		}
		PageHelper<Content> pageBean = new PageHelper<>(totalCount, pageIndex, pageSize, contents, null);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("hasNext", result.hasNext());
		model.addAttribute("hasPrevious", result.hasPrevious());
		return "/advertising";
	}
}
