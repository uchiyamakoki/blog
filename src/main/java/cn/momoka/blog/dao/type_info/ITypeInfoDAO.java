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

}
