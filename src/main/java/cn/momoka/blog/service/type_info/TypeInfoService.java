package cn.momoka.blog.service.type_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.momoka.blog.dao.type_info.ITypeInfoDAO;
import cn.momoka.blog.dao.user_info.IUserInfoDAO;
import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

@Service("TypeInfoService")
public class TypeInfoService {
	
	@Autowired
	private ITypeInfoDAO iTypeInfoDAO;

	/*
	 * 查询所有文章分类
	 */
	public List<TypeInfo> list() {
		// TODO Auto-generated method stub
		return iTypeInfoDAO.list();
	}
	
	
	

}
