<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>文章列表</title>
</head>

<body>
	<div class="wrap">
		<!-- 头部 -->
		<div id="header">
			<c:import url="../head.jsp"></c:import>
		</div>
		<!-- 左侧菜单和主体内容 -->
		<div class="grid-1-7" style="flex: 1;margin:0;">
			<!-- 左侧菜单 -->
				<c:import url="../menu.jsp"></c:import>
			<!-- 面包屑导航和主体内容 -->
			<div class="content">
				<!--面包屑导航-->
				<div class="content-header">
					<div class="breadcrumb">
						<span>文章管理</span> <span class="divider">/</span> <span
							class="active">文章列表</span>
					</div>
				</div>
				<!--主体内容-->
				<div class="list-content">
					<!--块元素-->
					<div class="block">
						<!--页面有多个表格时，可以用于标识表格-->
						<h2>w文章列表</h2>
						<!--右上角的返回按钮-->
						<a href="javascript:history.back();">
							<button class="button wathet"
								style="position: absolute; right: 20px; top: 16px;">
								<span class="icon-arrow_back"></span> 返回
							</button>
						</a>

						<!--正文内容-->
						<div class="main-20">
							<!--表格上方的搜索操作-->
							<div style="text-align: right; margin-bottom: 10px;">
								<!-- 标题检索 -->
								<input type="text" class="text" id="title" value=""
									placeholder="检索标题" />
								<!-- 点击查询按钮 -->
								<button class="button blue" style="margin-top: -3px;"
									onclick="search()">
									<span class="icon-search"></span>
								</button>
							</div>

							<!--表格上方的操作元素，添加、删除等-->
							<div class="operation-wrap">
								<div class="buttons-wrap">
									<a href="edit.action">
									<button id="add" class="button blue"><span class="icon-plus"></span>添加</button>
									</a>
									
									
								</div>
							</div>
							<table id="table" class="table">
								<thead>
									<tr>
										<th style="width: 20px;"><input type="checkbox" class="fill listen-1"/> </th>
										<th style="width: 60px;">序号</th>
										<th>分类</th>
										<th>文章标题</th>
										<th>撰写日期</th>
										<th>阅读量</th>
										<th>编辑</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${fn:length(list)==0}">
											<tr>
												<td colspan="7" style="text-align: center;">暂无记录</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${list}" var="entity"
												varStatus="status">
												<tr>
												<td ><input type="checkbox" class="fill listen-1-2" value="${entity.id}" name="id"/> </td>
													<td>${status.index+1}</td>
													<td>${entity.name}</td>
													<td>${entity.title}</td>
													<td>${entity.update_time}</td>
													<td>${entity.view_count}</td>
													<td><a href="edit.action?id=${entity.id}">
															<button class="button wathet">
																<span class="icon-edit"></span>编辑
															</button>
													</a></td>
													
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>