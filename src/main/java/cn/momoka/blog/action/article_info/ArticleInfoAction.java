package cn.momoka.blog.action.article_info;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
	
	/**
	 * 上传文件到磁盘（物理路径）
	 * @throws IOException 
	 */
	@RequestMapping("upload.json")
	@ResponseBody
	public Result upload(MultipartFile file, HttpServletRequest request) throws IOException {
		
		// 文件原名称
		String szFileName = file.getOriginalFilename();
		// 重命名后的文件名称
		String szNewFileName = "";
		// 根据日期自动创建3级目录
		String szDateFolder = "";
		
		// 上传文件
		if (file!=null && szFileName!=null && szFileName.length()>0) {
			Date date = new Date();
			szDateFolder = new SimpleDateFormat("yyyy/MM/dd").format(date);
			// 存储文件的物理路径
			String szFilePath = "D:\\upload\\" + szDateFolder;
			// 自动创建文件夹
			File f = new File(szFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			// 新的文件名称
			szNewFileName = UUID.randomUUID() + szFileName.substring(szFileName.lastIndexOf("."));
			// 新文件
			File newFile = new File(szFilePath+"\\"+szNewFileName);
			
			// 将内存中的数据写入磁盘
			file.transferTo(newFile);
		}
		
		return Result.success().add("imgUrl", szDateFolder+"/"+szNewFileName);
	}
	
	
}
