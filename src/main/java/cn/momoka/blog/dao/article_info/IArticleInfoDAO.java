package cn.momoka.blog.dao.article_info;

import java.util.List;
import java.util.Map;

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
	 * 查询所有文章
	 */
	List<ArticleInfo> list(Map<String, Object> param);

	/*
	 * 根据主键查询文章信息
	 */
	ArticleInfo selectById(String id);

	/*
	 * 新增文章
	 */
	void insert(ArticleInfo articleInfo);

	/*
	 * 更新文章
	 */
	void update(ArticleInfo articleInfo);
	/*
	 * 批量移动文章到某个分类
	 * idArr 主键数组
	 * typeId 文章分类的id
	 */
	//void batchUpdate(@Param("idArr") String[] idArr,@Param("typeId") String typeId);
	
	void batchUpdate(Map<String, Object> param);

	

	

}
