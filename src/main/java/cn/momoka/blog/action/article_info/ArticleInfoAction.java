package cn.momoka.blog.action.article_info;

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
@RequestMapping("article_info")
public class ArticleInfoAction {
	
	
	@Autowired
	private ArticleInfoService articleInfoService;
	@Autowired
	private TypeInfoService typeInfoService;
	/*
	 * 查询所有文章(正常)
	 */
	@RequestMapping("list_normal.action")
	public String listNormal(ModelMap map,
			@RequestParam(required = false, value = "typeId") String typeId,
			@RequestParam(required = false, value = "startDate") String startDate,
			@RequestParam(required = false, value = "endDate") String endDate,
			@RequestParam(required = false, value = "keyWord") String keyWord,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="10") int pageSize){
		
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("typeId", typeId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if(!StringUtils.isEmpty(keyWord)){
			param.put("keyWord", "%"+keyWord.trim()+"%");
		}
		param.put("status", "1");
		//UserInfo userInfo=userInfoService.selectUser("admin", "1234");
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		//List<ArticleInfo> list=articleInfoService.listNormal();
		List<ArticleInfo> list=articleInfoService.list(param);
		//map.put("list", list);
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(list);
		map.put("pageInfo", pageInfo);
		
		//查询所有文章分类
		map.put("typeList", typeInfoService.list());
		
		map.put("typeId", typeId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("keyWord", keyWord);
		return "admin/article_info/list_normal";
	}
	
	/*
	 * 查询所有文章(回收站)
	 */
	@RequestMapping("list_recycle.action")
	public String listRecycle(ModelMap map,
			@RequestParam(required = false, value = "typeId") String typeId,
			@RequestParam(required = false, value = "startDate") String startDate,
			@RequestParam(required = false, value = "endDate") String endDate,
			@RequestParam(required = false, value = "keyWord") String keyWord,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="2") int pageSize){
		
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("typeId", typeId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if(!StringUtils.isEmpty(keyWord)){
			param.put("keyWord", "%"+keyWord.trim()+"%");
		}
		param.put("status", "0");
		//UserInfo userInfo=userInfoService.selectUser("admin", "1234");
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		//List<ArticleInfo> list=articleInfoService.listNormal();
		List<ArticleInfo> list=articleInfoService.list(param);
		//map.put("list", list);
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(list);
		map.put("pageInfo", pageInfo);
		
		//查询所有文章分类
		map.put("typeList", typeInfoService.list());
		
		map.put("typeId", typeId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("keyWord", keyWord);
		return "admin/article_info/list_recycle";
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
		map.put("id", id);
		
		return "admin/article_info/edit";
	}
	/*
	 * 文章保存
	 */
	@RequestMapping("save.json")
	@ResponseBody
	public Result save(ArticleInfo articleInfo) {
		articleInfoService.save(articleInfo);
		return Result.success();
	}
	
	/*
	 * 批量移动文章
	 */
	@RequestMapping("move.json")
	@ResponseBody
	public Result move(
			@RequestParam( value = "idArr") String[] idArr,
			@RequestParam( value = "typeId") String typeId) {
		//articleInfoService.batchUpdate(idArr,typeId);
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("idArr", idArr);
		param.put("typeId", typeId);
		
		articleInfoService.batchUpdate(param);
		return Result.success();
	}
	
	/*
	 * 批量删除文章到回收站,其实就是批量更新文章的状态
	 */
	@RequestMapping("update_status.json")
	@ResponseBody
	public Result delete(
			@RequestParam( value = "idArr") String[] idArr,
			@RequestParam( value = "status") String status) {
		
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("idArr", idArr);
		param.put("status", status);
		
		articleInfoService.batchUpdate(param);
		return Result.success();
	}

	/*
	 * 批量删除文章
	 */
	@RequestMapping("delete.json")
	@ResponseBody
	public Result delete2(
			@RequestParam( value = "idArr") String[] idArr) {
		
		articleInfoService.batchDelete(idArr);
		return Result.success();
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
