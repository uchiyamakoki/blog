package cn.momoka.blog.dao.user_info;

import org.apache.ibatis.annotations.Param;

import cn.momoka.blog.view.UserInfo;

public interface IUserInfoDAO {

	/*
	 * 校验用户，参数俩个以上，要注解
	 */
	UserInfo selectUser(@Param("loginName") String loginName,@Param("passWord") String passWord);

}
