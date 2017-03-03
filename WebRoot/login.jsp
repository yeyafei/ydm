<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>后台登录</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<!-- load css -->
		<link rel="stylesheet" type="text/css" href="common/layui/css/layui.css" media="all">
		<link rel="stylesheet" type="text/css" href="admin/css/login.css" media="all">
		<script src="admin/js/jquery-1.8.0.min.js"></script>
		<script src="admin/js/jparticle.jquery.js"></script>
	    <script type="text/javascript" src="/admin/layer/layer.js"></script> 
		<script type="text/javascript">
	$(function() {
		$(".layui-canvs").width($(window).width());
		$(".layui-canvs").height($(window).height());
		$(".layui-canvs").jParticle( {
			background : "#666",
			color : "#E6E6E6"
		});
		if ("${errormsg}"!=""){
			layer.alert('${errormsg}', {
				title: false,
				icon : 0
			});
			return;
			}
	});
</script>
	</head>
	<body>
		<div class="layui-canvs"></div>
		<div class="layui-layout layui-layout-login">
			<form action="loginok.do" method="POST">
			<h1>
				<strong>管理系统</strong>
				<em>Management System</em>
			</h1>
			
			<div class="layui-user-icon l-login">
			<i class="layui-icon" style="font-size: 31px; color: #c2c2c2;">&#xe612;</i>
				<input type="text" name="username" placeholder="账号" class="login_txtbx" />
			</div>
			<div class="layui-pwd-icon l-login">
				<i class="layui-icon" style="font-size: 31px; color: #c2c2c2;">&#xe607;</i>
				<input type="password" name="password" placeholder="密码" class="login_txtbx" />
			</div>
			<div class="layui-submit l-login">
				<i class="layui-icon" style="font-size: 31px; color: #c2c2c2;">&#xe609;</i>
				<input type="submit" value="立即登陆" class="submit_btn" />
			</div>
			</form>
		</div>
	</body>
</html>
