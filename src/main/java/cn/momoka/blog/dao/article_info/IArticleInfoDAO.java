package cn.momoka.blog.dao.article_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.momoka.blog.view.ArticleInfo;
import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

public interface IArticleInfoDAO {

	/*
	 * 查询所有文章分类(正常)
	 */
	List<ArticleInfo> listNormal();

	/*
	 * 根据主键查询文章信息
	 */
	ArticleInfo selectById(String id);

	

}
