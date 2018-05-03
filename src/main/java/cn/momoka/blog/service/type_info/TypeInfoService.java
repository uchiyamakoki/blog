package cn.momoka.blog.service.type_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qiniu.common.QiniuException;

import cn.momoka.blog.dao.article_info.IArticleInfoDAO;
import cn.momoka.blog.dao.type_info.ITypeInfoDAO;
import cn.momoka.blog.dao.user_info.IUserInfoDAO;
import cn.momoka.blog.exception.MomokaException;
import cn.momoka.blog.view.TypeInfo;
import cn.momoka.blog.view.UserInfo;

@Service("TypeInfoService")
public class TypeInfoService {
	
	@Autowired
	private ITypeInfoDAO iTypeInfoDAO;
	
	@Autowired
	private IArticleInfoDAO iArticleInfoDAO;

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
	public void delete(String[] idArr) throws MomokaException {
		//判断该分类id有没有被使用
		int nCount=iArticleInfoDAO.countByTypeIdArr(idArr,"1");
		
		if(nCount>0){
			//被占用了，禁止删除
			throw new MomokaException("存在已被使用的分类，无法删除");
		}
		//先删除所有回收站的文章
		iArticleInfoDAO.batchDeleteByTypeIdArr(idArr);
		
		//然后删除该分类
		iTypeInfoDAO.delete(idArr);
	}

	/*
	 * 根据主键查询文章分类
	 */
	public TypeInfo selectById(String typeId) {
		// TODO Auto-generated method stub
		return iTypeInfoDAO.selectById(typeId);
	}
	
	
	

}
