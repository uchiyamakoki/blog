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
	/*
	 * 批量删除文章
	 * 批量最好注解，不然xml要写array
	 */
	void batchDelete(@Param("idArr") String[] idArr);
	/*
	 * 根据文章分类查询文章的数量
	 * typeIdArr
	 * status
	 */
	int countByTypeIdArr(@Param("typeIdArr") String[] typeIdArr,@Param("status") String status);
	/*
	 * 批量删除回收站根据分类id   删除分类时
	 */
	void batchDeleteByTypeIdArr(@Param("typeIdArr") String[] typeIdArr);
	
	/*
	 * 更新浏览次数
	 * id 主键
	 * viewCount 浏览量
	 */
	void updateViewCount(@Param("id") String id,@Param("ViewCount") int ViewCount);


}
