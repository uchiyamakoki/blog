package cn.momoka.blog.action.portal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import cn.momoka.blog.exception.MomokaException;
import cn.momoka.blog.service.article_info.ArticleInfoService;
import cn.momoka.blog.service.type_info.TypeInfoService;
import cn.momoka.blog.service.user_info.UserInfoService;
import cn.momoka.blog.view.ArticleInfo;
import cn.momoka.blog.view.Result;
import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

@Controller
@RequestMapping("portal")
public class PortalAction {
	
	
	@Autowired
	private ArticleInfoService articleInfoService;
	@Autowired
	private TypeInfoService typeInfoService;
	/*
	 * 查询所有文章(正常)
	 */
	@RequestMapping("index.action")
	public String listNormal(ModelMap map,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="3") int pageSize){
		
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("status", "1");
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		//List<ArticleInfo> list=articleInfoService.listNormal();
		List<ArticleInfo> list=articleInfoService.list(param);
		//map.put("list", list);
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(list);
		map.put("pageInfo", pageInfo);
		
		//查询所有文章分类
		//map.put("typeList", typeInfoService.list());

		return "portal/index";
	}
	
	/*
	 * 页面一加载就像后台请求文章分类的数据
	 */
	@RequestMapping("get_type.json")
	@ResponseBody
	public Result getType(ArticleInfo articleInfo) {
		List<TypeInfo> typeList=typeInfoService.list();
		return Result.success().add("typeList", typeList);
	}
	
	/*
	 * 查询所有文章分类()
	 */
	@RequestMapping("type.action")
	public String type(ModelMap map,
			@RequestParam(value="typeId") String typeId,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="3") int pageSize){
		
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("typeId", typeId);
		param.put("status", "1");
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> list=articleInfoService.list(param);

		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(list);
		map.put("pageInfo", pageInfo);
		map.put("typeInfo", typeInfoService.selectById(typeId));

		return "portal/type";
	}
	
	/*
	 * 根据主键查询文章
	 */
	@RequestMapping("article.action")
	public String article(ModelMap map,
			@RequestParam(value="id") String id){
		
		ArticleInfo articleInfo=articleInfoService.selectById(id);
		map.put("articleInfo", articleInfo);

		return "portal/article";
	}
	
	/*
	 * 查询所有文章分类()
	 */
	@RequestMapping("about.action")
	public String about(ModelMap map){

		return "portal/about";
	}
	
}
