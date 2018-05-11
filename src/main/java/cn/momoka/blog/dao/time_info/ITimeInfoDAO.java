package cn.momoka.blog.dao.time_info;

import java.util.List;

import cn.momoka.blog.view.TimeInfo;

public interface ITimeInfoDAO {

	/*
	 * 查询时间胶囊
	 */
	List<TimeInfo> selectTime();

}
