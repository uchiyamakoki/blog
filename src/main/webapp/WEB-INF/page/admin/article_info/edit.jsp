<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>文章编辑</title>
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
							class="active">文章编辑</span>
					</div>
				</div>
				<!--主体内容-->
				<div class="list-content">
					<!--块元素-->
					<div class="block">
						<!--修饰块元素名称-->
						<div class="banner">
							<p class="tab fixed">
								文章编辑<span class="hint">Article Editors</span>
							</p>
						</div>
						<!--正文-->
						<div class="main">
							<!--表单-->
							<form id="form">
							<input type="hidden" name="id" value="${id}" />
								<!--输入框-->
								<div class="unit">
									<div class="left">
										<p class="subtitle">标题</p>
									</div>
									<div class="right">
										<input type="text" class="text" name="title"
											data-type="必填" placeholder="请输入文章标题" value="${articleInfo.title}"/>
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
								<!--分割线-->
								<p class="divider"></p>

								<!--下拉选择框-->
								<div class="unit">
									<div class="left">
										<p class="subtitle">所属分类</p>
									</div>
									<div class="right">
										<select id="type_id" name="typeId">
										<c:forEach items="${typeList}" var="typeInfo" varStatus="status" >
												<option value="${typeInfo.id}" <c:if test="${articleInfo.typeId==typeInfo.id}">selected</c:if>>${typeInfo.name}</option>
											</c:forEach>
										</select>
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
								<!--分割线-->
								<p class="divider"></p>
								
								<!--文章封面-->
								<div class="unit">
									<div class="left">
										<p class="subtitle">文章封面</p>
									</div>
									<div class="right">
										<!-- 图片承载容器 -->
										<label id="container" for="upload" style="display: inline-block; width: 132px; height: 74px;">
											<c:choose>
												<c:when test="${empty articleInfo.cover}">
													<img
														src="${pageContext.request.contextPath}/static/javaex/pc/images/default.png"
														width="100%" height="100%" />
												</c:when>
												<c:otherwise>
													<img
														src="${articleInfo.cover}" width="100%" height="100%" />
												</c:otherwise>
											</c:choose>
											 
										</label>
										<!-- 上传按钮 -->
										<input type="file" class="hide" id="upload"
											accept="image/gif, image/jpeg, image/jpg, image/png" />
										<input type="hidden" id="cover" name="cover" value="" />
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
								<!--分割线-->
								<p class="divider"></p>

								<!--文本域-->
								<div class="unit">
									<div class="left">
										<p class="subtitle">内容</p>
									</div>
									<div class="right">
										<div id="edit" class="edit-container"></div>
										<input type="hidden" id="content" name="content" value="" />
										<input type="hidden" id="content_text" name="content_text" value="" />
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
								<!--分割线-->
								<p class="divider"></p>

								<!--提交按钮-->
								<div class="unit">
									<div style="margin-left: 200px;">
										<!--表单提交时，必须是input元素，并指定type类型为button，否则ajax提交时，会返回error回调函数-->
										<input type="button" id="return" class="button no" value="返回" />
										<input type="button" id="submit" class="button yes" value="保存" />
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>

	javaex.upload({
		type : "image",
		url : "upload.json", // 请求路径
		id : "upload", // <input type="file" />的id
		param : "file", // 参数名称，SSM中与MultipartFile的参数名保持一致
		dataType : "url", // 返回的数据类型：base64 或 url
		callback : function(rtn) {
			// 后台返回的数据
			console.log(rtn);
					if (rtn.code=="000000") {
						var imgUrl=rtn.data.imgUrl;
						$("#container img").attr("src", "/upload/"+imgUrl);
						$("#cover").val("http://localhost:8080/upload/"+imgUrl);
					} else {
						javaex.optTip({
							content : rtn.message,
							type : "error"
						});
					}
		}
	});

	javaex.select({
		id : "type_id",
		isSearch : false,
	// 选择后的回调函数

	});
	
	var content='${articleInfo.content}';
	
	javaex.edit({
		id : "edit",
		image : {
			url : "upload.json",	// 请求路径
			param : "file",		// 参数名称，SSM中与MultipartFile的参数名保持一致
			dataType : "url",	// 返回的数据类型：base64 或 url
			rtn : "rtnData",	// 后台返回的数据对象，在前面页面用该名字存储
			imgUrl : "data.imgUrl",	// 根据返回的数据对象，获取图片地址。  例如后台返回的数据为：{code: "000000", message: "操作成功！", data: {imgUrl: "1.jpg"}}
			prefix : "/upload/"	// 图片地址的前缀，根据实际情况决定是否需要填写
		},
		content : content,	// 这里必须是单引号，因为html代码中都是双引号，会产生冲突
		callback : function(rtn) {
			$("#content").val(rtn.html);
			$("#content_text").val(rtn.text.substring(0,100));
		}
	});
	$("#return").click(function(){
		history.back();
	});
	$("#submit").click(function(){
		$.ajax({
			url : "save.json",
			type : "POST",
			dataType : "json",
			data : $("#form").serialize(),
			success : function(rtn) {
				if(rtn.code=="000000"){
					javaex.optTip({
						content : rtn.message
					});
					// 建议延迟加载
					setTimeout(function() {
						//跳转页面
						//window.location.reload();
						window.location.href = "${pageContext.request.contextPath}/article_info/list_normal.action";
					}, 2000);
					//window.location.reload();
				}else{
					javaex.optTip({
						content : rtn.message,
						type: "error"
					});
				}
			}
		});
	});
</script>
</html>