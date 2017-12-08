<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="http://manyiaby.oss-cn-beijing.aliyuncs.com/public/logo.ico" rel="shortcut icon">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <form action="user_login.action" method="post">
   	账号：<input type="text" name="userName" id="userName" placeholder="请输入用户名" /><br/>
   	密码：<input type="password" name="userPWD" id="userPWD" placeholder="请输入密码" /><br/>
   	<input type="submit" value="登录">&nbsp;&nbsp;<input type="reset" value="重置">
   </form> 
  </body>
</html>