package cn.momoka.blog.service.type_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	/*
	 * 批量更新/插入文章分类
	 */
	public void save(String[] idArr, String[] sortArr, String[] nameArr) {
		//遍历第一个数组
		for(int i=0;i<idArr.length;i++){
			//判断这条数据需要更新还是插入
			if(StringUtils.isEmpty(idArr[i])){
				//插入
				iTypeInfoDAO.insert(sortArr[i],nameArr[i]);
			}else {
				//更新
				iTypeInfoDAO.update(idArr[i],sortArr[i],nameArr[i]);
			}
			
		}
		
	}
	/*
	 * 删除文章分类
	 * iddar: 主键数组
	 */
	public void delete(String[] idArr) {
		// TODO Auto-generated method stub
		iTypeInfoDAO.delete(idArr);
	}
	
	
	

}
