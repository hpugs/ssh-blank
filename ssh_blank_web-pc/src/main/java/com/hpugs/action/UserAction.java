package com.hpugs.action;

import com.hpugs.commons.action.BaseAction;
import com.hpugs.po.User;
import com.hpugs.service.IUserService;

/**
 * @Description 用户Action
 * 
 * @author 高尚
 * @version 1.0
 * @date 创建时间：2017年12月6日 下午8:26:29
 */
public class UserAction extends BaseAction {
	
	private IUserService userService;
	
	private String userName;
	private String userPWD;
	
	/**
	 * @Description 登录页面
	 * @return
	 *
	 * @author 高尚
	 * @version 1.0
	 * @date 创建时间：2017年12月6日 下午8:35:40
	 */
	public String loginJsp(){
		return SUCCESS;
	}
	
	/**
	 * @Description 登录检查
	 * @return
	 *
	 * @author 高尚
	 * @version 1.0
	 * @date 创建时间：2017年12月6日 下午9:14:27
	 */
	public String login(){
		if(null != userName && 0 < userName.length()){
			User userPo = (User) userService.getObjectByParams("WHERE name = '" + userName + "' OR mobile = '" + userName + "'");
			if(null != userPo && userPo.getPwd().equals(userPWD)){
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPWD(String userPWD) {
		this.userPWD = userPWD;
	}
	
}
