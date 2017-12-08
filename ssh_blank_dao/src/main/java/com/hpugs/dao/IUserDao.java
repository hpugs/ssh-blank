package com.hpugs.dao;

import com.hpugs.po.User;

/**
 * @Description 用户表
 * @author 高尚
 * @version 1.0
 * @date 创建时间：2017年12月7日 下午6:18:23
 */
public interface IUserDao {

	User getObjectByParams(String whereParam);

}
