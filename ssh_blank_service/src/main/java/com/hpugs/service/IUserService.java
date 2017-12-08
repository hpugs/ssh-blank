package com.hpugs.service;

import com.hpugs.po.User;

/**
 * @Description 用户表
 * @author 高尚
 * @version 1.0
 * @date 创建时间：2017年12月7日 下午6:21:23
 */
public interface IUserService {

	User getObjectByParams(String whereParam);

}
