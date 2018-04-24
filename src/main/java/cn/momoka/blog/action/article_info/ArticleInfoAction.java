package cn.momoka.blog.action.article_info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.momoka.blog.exception.MomokaException;
import cn.momoka.blog.service.article_info.ArticleInfoService;
import cn.momoka.blog.service.type_info.TypeInfoService;
import cn.momoka.blog.service.user_info.UserInfoService;
import cn.momoka.blog.view.ArticleInfo;
import cn.momoka.blog.view.Result;
import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

@Controller
@RequestMapping("article_info")
public class ArticleInfoAction {
	
	
	@Autowired
	private ArticleInfoService articleInfoService;
	@Autowired
	private TypeInfoService typeInfoService;
	/*
	 * 查询所有文章分类
	 */
	@RequestMapping("list_normal.action")
	public String listNormal(ModelMap map){
		//UserInfo userInfo=userInfoService.selectUser("admin", "1234");
		List<ArticleInfo> list=articleInfoService.listNormal();
		map.put("list", list);
		return "admin/article_info/list_normal";
	}
	
	/*
	 * 文章编辑
	 */
	@RequestMapping("edit.action")
	public String edit(ModelMap map,
			@RequestParam(required = false, value = "id") String id){
		//单个文章的信息
		//1.0判断新增还是更新文章
		//查询单个文章的信息
		if(!StringUtils.isEmpty(id)){
			ArticleInfo articleInfo=articleInfoService.selectById(id);
			map.put("articleInfo", articleInfo);
		}else {
			
		}
		//查询所有文章分类
		map.put("typeList", typeInfoService.list());
		return "admin/article_info/edit";
	}
	
}
