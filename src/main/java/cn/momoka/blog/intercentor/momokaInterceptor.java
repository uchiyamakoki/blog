package cn.momoka.blog.intercentor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.momoka.blog.view.UserInfo;

public class momokaInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		//1.0获取请求的地址
		String url=request.getRequestURI();
		//2.0对特殊地址直接放行
		if(url.indexOf("login")>=0 || url.indexOf("portal")>=0){
			return true;
		}
		//3.0判断session session存在的话，登陆后台
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
		if(userInfo!=null){
			return true;
		}
		//4.0跳转到登录界面
		request.getRequestDispatcher("/WEB-INF/page/admin/login.jsp").forward(request, response);
		return false;
	}

}
