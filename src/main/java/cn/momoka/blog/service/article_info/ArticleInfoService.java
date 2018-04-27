package cn.momoka.blog.service.article_info;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.momoka.blog.dao.article_info.IArticleInfoDAO;
import cn.momoka.blog.dao.type_info.ITypeInfoDAO;
import cn.momoka.blog.dao.user_info.IUserInfoDAO;
import cn.momoka.blog.view.ArticleInfo;
import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

@Service("ArticleInfoService")
public class ArticleInfoService {
	
	@Autowired
	private IArticleInfoDAO iArticleInfoDAO;

	/*
	 * 查询所有文章分类
	 */
	public List<ArticleInfo> listNormal() {
		// TODO Auto-generated method stub
		return iArticleInfoDAO.listNormal();
	}
	/*
	 * 查询单个文章的信息
	 */
	public ArticleInfo selectById(String id) {
		// TODO Auto-generated method stub
		return iArticleInfoDAO.selectById(id);
	}
	/*
	 * 保存文章
	 */
	public void save(ArticleInfo articleInfo) {
		Date currentTime=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=formatter.format(currentTime);
		// 修改还是新增
		if(StringUtils.isEmpty(articleInfo.getId())){
			articleInfo.setStatus(1);
			articleInfo.setUpdate_time(now);
			articleInfo.setView_count(1);
			iArticleInfoDAO.insert(articleInfo);
		}else {
			//更新
			articleInfo.setUpdate_time(now);
			iArticleInfoDAO.update(articleInfo);
		}
	}

}
