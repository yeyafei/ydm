<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>后台管理</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<!-- load css -->
	<link rel="stylesheet" type="text/css" href="common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="common/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="admin/css/adminstyle.css" media="all">
	<!-- 加载js文件-->
	<script type="text/javascript" src="common/layui/layui.js"></script> 
	<script type="text/javascript" src="admin/js/yyf.js"></script>
	<script type="text/javascript" src="admin/js/index.js"></script>	
	<script src="admin/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="admin/js/clock.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#clock").drawClock(
					{
						 hCol: '#1AA094',// 时针颜色
						 mCol: '#1AA094', // 时针颜色
						 sCol: '#1AA094', // 时针颜色
						 isNumCol: '', // 数字所在的点颜色
						 noNumCol: '', // 非数字所在的点颜色
						 dCol: '#c2c2c2', // 中心圈颜色
					}
			);
		})
		
	</script>
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
	<!-- 顶部区域 -->
	<div class="layui-header header header-demo">
		<div class="layui-main">
		    <!-- logo区域 -->
			<div class="admin-logo-box">
				<a class="logo" href="" title="logo"><img src="common/images/yyf.png" alt=""></a>
				<div class="yyf-side-menu">
				<i class="layui-icon" style="font-size: 20px; color: #FFFFFF;">&#xe603;</i>
				</div>
			</div>
			
			
            <!-- 顶级菜单区域 -->
            <div class="layui-yyf-menu">
                 <ul class="layui-nav clearfix">
                       <li class="layui-nav-item layui-this">
                 	   	   <a href="https://github.com/yeyafei/ydm"><i class="layui-icon" style="font-size: 25px; color: #1E9FFF;">&#xe650;</i>GITHUB</a>
                 	   </li>
                 	   <li class="layui-nav-item">
                 	   	   <a href="javascirpt:;"><i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe62d;</i>SYSLOG</a>
                 	   </li>
                 </ul>
            </div>
            <!-- 右侧导航 -->
            <ul class="layui-nav yyf-header-item">
					<li class="layui-nav-item">
						<a href="loginout.do">
                        <i class="iconfont icon-exit"></i>
						退出</a>
					</li>
            </ul>
		</div>
	</div>
	<!-- 左侧侧边导航开始 -->
	<div class="layui-side layui-side-bg layui-yyf-side" id="yyf-side">
        <div class="layui-side-scroll" id="yyf-nav-side" lay-filter="side">
        <div class="user-photo">
			<a class="img">
			<canvas id="clock" width="120" height="120"></canvas>
			</a>
			<!-- <p>${USER.name }</p> -->
		</div>
		<!-- 左侧菜单 -->
		<ul class="layui-nav layui-nav-tree">
			<li class="layui-nav-item layui-this">
				<a href="javascript:;" data-url="notice/viewnoticelist.do">
				    <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					<span>后台首页</span>
				</a>
			</li>
			<c:forEach items="${FRISTMENU}" var="menu" >
				<li class="layui-nav-item">
					<a href="javascript:;">
						<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#${menu.icon}</i>
						<span>${menu.description }</span>
						<em class="layui-nav-more"></em>
					</a>
					<dl class="layui-nav-child">
					<c:forEach items="${menu.list}" var="menul" >
					  	<dd>
                        <a href="javascript:;" data-url="${menul.url}?num=${menul.num}">
                          <i class="layui-icon" style="font-size: 25px; color: #1E9FFF;">&#${menul.icon}</i>
                        <!--   <i class="layui-icon" style="font-size: 25px; color: #1E9FFF;">&#xe609;</i> --> 
                            <span>${menul.description}</span>
                        </a>
                    	</dd>
					</c:forEach>
					</dl>
				</li>
	  		</c:forEach>
		</ul>
	    </div>
	</div>

	<!-- 左侧侧边导航结束 -->
	<!-- 右侧主体内容 -->
	<div class="layui-body" id="yyf-body" style="bottom: 0;border-left: solid 2px #1AA094;">
		<div class="layui-tab layui-tab-card yyf-tab-box" id="yyf-tab" lay-filter="demo" lay-allowclose="true">
			<ul class="layui-tab-title">
				<li class="layui-this" id="admin-home"><i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i><em>后台首页</em></li>
			</ul>
			<div class="layui-tab-content" style="min-height: 150px; ">
				<div class="layui-tab-item layui-show">
					<iframe class="yyf-iframe" id="iframe" name ="iframeName" "data-id='0' src="notice/viewnoticelist.do"></iframe>
				</div>
			</div>
		</div>

        
	</div>
	<!-- 底部区域 -->
	<div class="layui-footer layui-yyf-foot" id="yyf-footer">
 	<div class="layui-mian">
		    <div class="yyf-footer-left" style="text-align: center;">
		    	 查看:<a href="http://yeyafei.github.io" title="">作者信息</a>
		    </div>
		    <p  style="text-align: center;">
		    	<span>2017 &copy;</span>
		    	Write by yyf
		    </p>
		</div>
	</div>
</div>

</body>
</html>
