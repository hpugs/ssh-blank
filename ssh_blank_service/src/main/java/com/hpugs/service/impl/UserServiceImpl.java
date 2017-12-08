package com.hpugs.service.impl;

import com.hpugs.dao.IUserDao;
import com.hpugs.po.User;
import com.hpugs.service.IUserService;

/**
 * @Description 用户表
 * @author 高尚
 * @version 1.0
 * @date 创建时间：2017年12月7日 下午6:20:56
 */
public class UserServiceImpl implements IUserService{
	
	private IUserDao userDao;

	public User getObjectByParams(String whereParam) {
		// TODO Auto-generated method stub
		return userDao.getObjectByParams(whereParam);
	}
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


}
