package cn.momoka.blog.action.type_info;

import java.util.List;

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

import cn.momoka.blog.exception.MomokaException;
import cn.momoka.blog.service.type_info.TypeInfoService;
import cn.momoka.blog.service.user_info.UserInfoService;
import cn.momoka.blog.view.Result;
import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

@Controller
@RequestMapping("type_info")
public class TypeInfoAction {
	
	
	@Autowired
	private TypeInfoService typeInfoService;
	
	@RequestMapping("list.action")
	public String list(ModelMap map){
		//UserInfo userInfo=userInfoService.selectUser("admin", "1234");
		List<TypeInfo> list=typeInfoService.list();
		map.put("list", list);
		return "admin/type_info/list";
	}
}
