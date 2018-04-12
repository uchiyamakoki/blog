package cn.momoka.blog.action.user_info;

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
import cn.momoka.blog.service.user_info.UserInfoService;
import cn.momoka.blog.view.Result;
import cn.momoka.blog.view.UserInfo;

@Controller
@RequestMapping("admin")
public class UserInfoAction {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("index.action")
	public String index(){
		//UserInfo userInfo=userInfoService.selectUser("admin", "1234");
		return "admin/index";
	}
	
	@RequestMapping("login.action")
	public String login(){
		return "admin/login";
	}
	
	@RequestMapping("login.json")
	@ResponseBody
	public Result login2(ModelMap map,HttpServletRequest request) throws MomokaException{
		
		log.debug("DEBUG登陆开始");
		log.info("INFO登陆开始");
		log.error("ERROR登陆开始");//一般也可写在try catch里面
		
		//1.0获取参数
		String loginName=request.getParameter("login_name"); //与form的name对应
		String passWord=request.getParameter("pass_word"); 
		//2.0校验参数
		//2.1判断参数是否为空
		if(StringUtils.isEmpty(loginName)||StringUtils.isEmpty(passWord)){
			//return Result.error("用户名或者密码不能为空");
			throw new MomokaException("用户名或者密码不能为空");
		}
		//2.2判断用户名密码是否正确
		UserInfo userInfo=userInfoService.selectUser(loginName, passWord);
		if(userInfo==null){
			//return Result.error("用户名或者密码不正确");
			throw new MomokaException("用户名或者密码不存在");
		}
		//3.0设置session
		request.getSession().setAttribute("userInfo", userInfo);
		
		//
		return Result.success();
	}
	
	@RequestMapping("login_out.action")
	public String loginOut(HttpSession session){
		//销毁session
		session.invalidate();
		return "admin/login";
	}
	
	
	
	/*
	 * 用户登录
	 * @RequestMapping("login.action")
	public String login(ModelMap map,
			@RequestParam(required=false,value="login_name") String loginName,
			@RequestParam(required=false,value="pass_word") String passWord){
		//如果登录名或者密码未填写，直接返回登录界面
		if(StringUtils.isEmpty(loginName)||StringUtils.isEmpty(passWord)){
			return "login";
			
		}
		//校验用户名，密码是否正确
		UserInfo userInfo=userInfoService.selectUser(loginName,passWord);
		if(userInfo==null){
			return "login";
		}
		//登录成功，进入主页
		return "home";
		
	}
	 */
	

}
