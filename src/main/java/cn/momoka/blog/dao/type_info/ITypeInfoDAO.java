package cn.momoka.blog.dao.type_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

public interface ITypeInfoDAO {

	/*
	 * 查询所有文章分类
	 */
	List<TypeInfo> list();

	/*
	 * 插入一条新的数据
	 * 排序用
	 * 分类的名称
	 */
	void insert(@Param("sort") String sort,@Param("name") String name);
	/*
	 * 更新一条新的数据
	 * 主键
	 * 排序用
	 * 分类的名称
	 */
	void update(@Param("id") String id, @Param("sort") String sort,@Param("name") String name);

}
