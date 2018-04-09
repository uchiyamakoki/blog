package cn.momoka.blog.service.user_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.momoka.blog.dao.user_info.IUserInfoDAO;
import cn.momoka.blog.view.UserInfo;

@Service("UserInfoService")
public class UserInfoService {
	
	@Autowired
	private IUserInfoDAO iUserInfoDAO;
	
	/*
	 * 用户校验
	 */
	public UserInfo selectUser(String loginName, String passWord) {
		// TODO Auto-generated method stub
		return iUserInfoDAO.selectUser(loginName,passWord);
	}
	

}
