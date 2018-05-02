<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="grid-1-3 container">
				<div id="logo">
					<a href="javascript:;">陈霓清的博客</a>
				</div>
				<div id="nav">
					<ul class="nav">
						<li class="active"><a href="#">首页</a></li>
						<li style="position: relative;">
							<a href="javascript:;">文章目录</a>
							<ul class="classified-nav">
								<li><a href="#">前端</a></li>
								<li><a href="#">java基础</a></li>
								<li><a href="#">数据库</a></li>
								<li><a href="#">SSM</a></li>
							</ul>
						</li>
						<li><a href="#">关于我</a></li>
						<li><a href="javascript:;" onClick="openSearch()"><span class="icon-search" style="font-size: 14px;"></span></a></li>
					</ul>
					<div id="search" class="hide">
						<span class="search-field-wrapper form-group">
							<input type="text" class="search" placeholder="搜索…" value="" name="">
						</span>
						<span id="close-search" class="icon-close" style="font-size: 16px;"></span>
					</div>
				</div>
			</div>