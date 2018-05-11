package cn.momoka.blog.service.time_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.momoka.blog.dao.time_info.ITimeInfoDAO;
import cn.momoka.blog.view.TimeInfo;

@Service("TimeInfoService")
public class TimeInfoService {
	
	@Autowired
	private ITimeInfoDAO iTimeInfoDAO;
	
	/*
	 * 查询时间胶囊
	 */
	public List<TimeInfo> checkTime(){
		return iTimeInfoDAO.selectTime();
	}
}
