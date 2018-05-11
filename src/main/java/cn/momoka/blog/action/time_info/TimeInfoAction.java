package cn.momoka.blog.action.time_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.momoka.blog.service.time_info.TimeInfoService;
import cn.momoka.blog.view.TimeInfo;

@Controller
@RequestMapping("time_info")
public class TimeInfoAction {
	@Autowired
	private TimeInfoService timeInfoService;
	
	@RequestMapping("/times.action")
	@ResponseBody
	public List<TimeInfo> times(){
		return timeInfoService.checkTime();
	}
	
	

}
