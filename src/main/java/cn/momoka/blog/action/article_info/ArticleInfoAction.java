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
	
	@RequestMapping("list_normal.action")
	public String listNormal(ModelMap map){
		//UserInfo userInfo=userInfoService.selectUser("admin", "1234");
		List<ArticleInfo> list=articleInfoService.listNormal();
		map.put("list", list);
		return "admin/article_info/list_normal";
	}
	
}
