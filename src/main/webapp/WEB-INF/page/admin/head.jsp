<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!--字体图标样式 绝对地址WEB-INF为根目录目测-->
<link href="${pageContext.request.contextPath}/static/javaex/pc/css/icomoon.css" rel="stylesheet" />
<!--动画样式-->
<link href="${pageContext.request.contextPath}/static/javaex/pc/css/animate.css" rel="stylesheet" />
<!--核心样式-->
<link href="${pageContext.request.contextPath}/static/javaex/pc/css/style.css" rel="stylesheet" />
<!--jquery，当前版本不可更改jquery版本-->
<script src="${pageContext.request.contextPath}/static/javaex/pc/lib/jquery-1.7.2.min.js"></script>
<!--全局动态修改-->
<script src="${pageContext.request.contextPath}/static/javaex/pc/js/common.js"></script>
<!--核心组件-->
<script src="${pageContext.request.contextPath}/static/javaex/pc/js/javaex.min.js"></script>
<!--表单验证-->
<script src="${pageContext.request.contextPath}/static/javaex/pc/js/javaex-formVerify.js"></script>
<!--左侧logo-->
			<div>
				<a href="#"> <img class="logo-img"
					src="${pageContext.request.contextPath}/static/javaex/pc/images/logo.png">
				</a>
			</div>
			<!--右侧功能-->
			<ul class="pull-right header-right">
				<li>
					<p class="pull-left margin-left-10">
						欢迎您，<span>管理员</span>
					</p> <label class="margin-left-10 margin-right-10">|</label> <a
					href="${pageContext.request.contextPath}/admin/login_out.action">退出</a></li>
				<li>
				</li>
				<li><a href="${pageContext.request.contextPath}/portal/index.action">首页</a></li>
			</ul>