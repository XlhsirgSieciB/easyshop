package com.easyshop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.easyshop.bean.Brand;
import com.easyshop.service.BrandService;
import com.easyshop.utils.DateUtils;
import com.easyshop.utils.PageHelper;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pursuelin
 * @since 2018-12-05
 */
@Controller
@Scope("prototype")
@RequestMapping("/brand")
public class BrandController {

	@Reference
	BrandService bs;

	//显示所有数据
	@RequestMapping("/list/{pageIndex}")
	public String list(Brand brand, @PathVariable(value = "pageIndex") Integer pageIndex,
			@RequestParam(defaultValue = "3") Integer pageSize, Model model) {
		// 分页工具
		Page<Brand> page = new Page<Brand>(pageIndex, pageSize);
		EntityWrapper<Brand> entityWrapper = new EntityWrapper<Brand>();

		// 查询的数据结果集对象 查询条件
		if (brand != null && brand.getName() != null) {
			entityWrapper.like("name", brand.getName());
		}
		if (brand != null && brand.getChina() != null && brand.getChina() != -1) {
			entityWrapper.eq("china", brand.getChina());
		}
		entityWrapper.eq("del", 0);
		Page<Brand> result = bs.selectPage(page, entityWrapper);
		// 获得总数
		int totalCount = ((Long) result.getTotal()).intValue();
		// 查询出每页数据
		List<Brand> brands = result.getRecords();
		for (Brand brand2 : brands) {
			System.out.println(brand2);
		}
		PageHelper<Brand> pageBean = new PageHelper<>(totalCount, pageIndex, pageSize, brands, brand);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("hasNext", result.hasNext());
		model.addAttribute("hasPrevious", result.hasPrevious());
		return "Brand_Manage";// 跳转到模板上
	}

	//跳转新增页面
	@RequestMapping("/toadd")
	public String toadd() {
		return "Add_Brand";
	}
	
	//新增
	@RequestMapping("/add")
	public void add(Brand brand,HttpServletResponse response) throws Exception {
		String createtime = DateUtils.getNow();
		brand.setCreatetime(createtime);
		//设置是否删除状态   1已删除 0未删除
//		brand.setDel(0);
		boolean b = bs.insert(brand);
		if(b) {
			response.getWriter().write("<script>alert('新增成功');location.href='/brand/list/1';</script>");
		}else {
			response.getWriter().write("<script>alert('新增失败');location.href='/brand/toadd';</script>");
		}
	}

	//修改品牌状态
	@RequestMapping("/status/{id}")
	@ResponseBody
	public boolean status(@PathVariable(name="id") Integer id) {
		Brand brand = bs.selectById(id);
		Integer status = brand.getStatus();
		status=status==1?0:1;
		brand.setId(id);
		brand.setStatus(status);
		return bs.updateById(brand);
	}
	
	//根据id查找品牌信息
	@RequestMapping("/toupdate/{id}")
	public String toupdate(@PathVariable(name="id") Integer id,Model model) {
		Brand brand = bs.selectById(id);
		model.addAttribute("brand", brand);
		return "Update_Brand";
	}
	
	//修改品牌信息
	@RequestMapping("/update")
	public void update(Brand brand,HttpServletResponse response) throws Exception {
		boolean b = bs.updateById(brand);
		if(b) {
			response.getWriter().write("<script>alert('修改成功');location.href='/brand/list/1';</script>");
		}else {
			response.getWriter().write("<script>alert('修改失败');location.href='/brand/toupdate/"+brand.getId()+"';</script>");
		}
	}
	
	//删除品牌
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public boolean deleteById(@PathVariable(name="id") Integer id) {
		Brand brand = new Brand();
		brand.setId(id);
		//修改删除状态
		brand.setDel(1);
		return bs.updateById(brand);
	}
	
	//批量删除
	@RequestMapping("/deletebatch/{ids}")
	@ResponseBody
	public boolean deletebatch(@PathVariable(name="ids") String ids) {
		System.out.println(ids);
		String[] split = ids.split(",");
		ArrayList<Brand> list = new ArrayList<>();
		for (String string : split) {
			int id = Integer.parseInt(string);
			Brand brand = new Brand();
			brand.setId(id);
			brand.setDel(1);
			list.add(brand);
		}
		return bs.updateBatchById(list);
	}
	
	
	//文件上传到FastDFS服务器
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request,@RequestParam("file") MultipartFile mfile) throws Exception {

		// 开始上传图片
		// 1.加载大哥的路径.
		String realPath = request.getServletContext().getRealPath("/fastdfsconfig/Tracker.conf");
		ClientGlobal.init(realPath);
		// 2.创建大哥的Client.
		TrackerClient tc = new TrackerClient();
		// 3.大哥的Client获得大哥的server
		TrackerServer ts = tc.getConnection();
		// 4.声明一个马仔的server.默认值给null
		StorageServer ss = null;
		// 5.通过大哥的server和马仔的server获得到马仔的客户端 StorageClient
		StorageClient sc = new StorageClient(ts, ss);
		// 6.使用马仔的Client上传图片.
		String[] result = sc.upload_file(mfile.getBytes(), mfile.getOriginalFilename(), null);
		// 返回值result 封装有文件上传路径和文件名（随机生成的）

		// 当和添加工作一起做时，需要把文件的路径和文件名保存到数据库，因此需要该返回值（但不会包括图片服务器的ip地址和端口，别忘了添加进去）
		String imagePath = "http://192.168.202.129:8866/" + result[0] + "/" + result[1];
		System.out.println("文件上传的服务器路径:" + imagePath);
		System.out.println(result[0] + "-----" + result[1]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imagePath", imagePath);
		map.put("code", 1000);
		return map;
	}
}
