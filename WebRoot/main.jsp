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
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
	<!-- 顶部区域 -->
	<div class="layui-header header header-demo">
		<div class="layui-main">
		    <!-- logo区域 -->
			<div class="admin-logo-box">
				<a class="logo" href="http://www.kuxuebao.net" title="logo"><img src="common/images/yyf.png" alt=""></a>
				<div class="yyf-side-menu">
					<i class="fa fa-bars" aria-hidden="true"></i>
				</div>
			</div>
            <!-- 顶级菜单区域 -->
            <div class="layui-yyf-menu">
                 <ul class="layui-nav clearfix">
                       <li class="layui-nav-item layui-this">
                 	   	   <a href="javascirpt:;"><i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>内容管理</a>
                 	   </li>
                 	   <li class="layui-nav-item">
                 	   	   <a href="javascirpt:;"><i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>扩展模块</a>
                 	   </li>
                 </ul>
            </div>
            <!-- 右侧导航 -->
            <ul class="layui-nav yyf-header-item">
					<li class="layui-nav-item">
						<a href="login.html">
                        <i class="iconfont icon-exit"></i>
						退出</a>
					</li>
            </ul>
		</div>
	</div>
	<!-- 左侧侧边导航开始 -->
	<div class="layui-side layui-side-bg layui-yyf-side" id="yyf-side">
        <div class="layui-side-scroll" id="yyf-nav-side" lay-filter="side">
		<!-- 左侧菜单 -->
		<ul class="layui-nav layui-nav-tree">
			<li class="layui-nav-item layui-this">
				<a href="javascript:;" data-url="main.html">
				    <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					<span>后台首页</span>
				</a>
			</li>
			<!-- 个人信息 -->
			<li class="layui-nav-item">
				<a href="javascript:;">
					<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					<span>我的面板</span>
					<em class="layui-nav-more"></em>
				</a>
				<dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-url="admin/personInfo.html">
                          <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
                            <span>个人信息</span>
                        </a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-url="changepwd.html">
                           <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
                            <span>修改密码</span>
                        </a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-url="myloginfo.html">
                            <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
                            <span>日志信息</span>
                        </a>
                    </dd>
                </dl>
			</li>
			<!-- 用户管理 -->
			<li class="layui-nav-item">
					<a href="javascript:;">
					   <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					   <span>用户管理</span>
					   <em class="layui-nav-more"></em>
					</a>
					    <dl class="layui-nav-child">
					    	<dd>
					    		<a href="javascript:;" data-url="user/viewuserlist.do">
					    		  <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					    		   <span>用户列表</span>
					    		</a>
					    	</dd>
					    	<dd>
					    		<a href="javascript:;">
					    		  <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					    		   <span>角色列表</span>
					    		</a>
					    	</dd>
					    	<dd>
					    		<a href="javascript:;" data-url="module/get.do">
					    		   <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					    		   <span>菜单管理</span>
					    		</a>
					    	</dd>
					    </dl>
			    </li>
				
			<!-- 系统设置 -->
			<li class="layui-nav-item">
					<a href="javascript:;">
					  <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					   <span>系统设置</span>
					   <em class="layui-nav-more"></em>
					</a>
					    <dl class="layui-nav-child">
					    	<dd>
					    		<a href="javascript:;">
					    		   <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					    		   <span>基本参数设置</span>
					    		</a>
					    	</dd>
					    	<dd>
					    		<a href="javascript:;">
					    		   <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					    		   <span>多站点管理</span>
					    		</a>
					    	</dd>
					    	<dd>
					    		<a href="javascript:;">
					    		  <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					    		   <span>安全设置</span>
					    		</a>
					    	</dd>
					    	<dd>
					    		<a href="javascript:;">
					    		   <i class="iconfont icon-iconfuzhi01" data-icon='icon-iconfuzhi01'></i>
					    		   <span>系统日志管理</span>
					    		</a>
					    	</dd>
					    	<dd>
					    		<a href="javascript:;">
					    			<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					    			<span>SQL命令行工具</span>
					    		</a>
					    	</dd>
					    </dl>
				</li>
				<!-- 友链设置 -->
				<li class="layui-nav-item">
					<a href="javascript:;">
					   <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe609;</i>
					   <span>友情链接</span>
					</a>
				</li>
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
					<iframe class="yyf-iframe" data-id='0' src="main.html"></iframe>
				</div>
			</div>
		</div>

        
	</div>
	<!-- 底部区域 -->
	<div class="layui-footer layui-yyf-foot" id="yyf-footer">
		<div class="layui-mian">
		    <div class="yyf-footer-left">
		    	 查看:<a href="http://www.qinshouwei.com" title="">作者信息</a>
		    </div>
		    <p class="p-admin">
		    	<span>2017 &copy;</span>
		    	Write by yyf
		    </p>
		</div>
	</div>
</div>


<!-- 菜单控件 -->
<!-- <div class="yyf-tab-menu">
	<span class="layui-btn yyf-test">刷新</span>
</div> -->
<!-- iframe框架刷新操作 -->
<!-- <div id="refresh_iframe" class="layui-btn refresh_iframe">刷新</div> -->
</body>
</html>
