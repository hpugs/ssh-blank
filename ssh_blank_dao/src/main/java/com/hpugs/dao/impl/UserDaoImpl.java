package com.hpugs.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hpugs.commons.email.tencent.SendEmailUtil;
import com.hpugs.dao.IUserDao;
import com.hpugs.po.User;

/**
 * @Description 用户表
 * @author 高尚
 * @version 1.0
 * @date 创建时间：2017年12月7日 下午6:19:59
 */
public class UserDaoImpl implements IUserDao {
	
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			SendEmailUtil.send("Dao生成Session异常", "1、用户表Dao生成Session异常；2、异常详情：" + e.toString());
			return null;
		}
	}
	
	public User getObjectByParams(String whereParam) {
		String hql = "select o from User o ";
		if (whereParam != null)
			hql += whereParam;
		Query query = getSession().createQuery(hql);
		query.setMaxResults(1);
		return (User) query.uniqueResult();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
