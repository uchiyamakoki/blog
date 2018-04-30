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

						<!--正文内容-->
						<div class="main-20">
							<!--表格上方的搜索操作-->
							<!-- 文章分类 -->
							<div style="text-align: right;margin-bottom: 10px;">
								<select id="type_id" class="no-shadow">
								<option value="">请选择</option>
									<c:forEach items="${typeList}" var="typeInfo"
										varStatus="status">
										<option value="${typeInfo.id}" <c:if test="${typeInfo.id==typeId}">selected</c:if>>${typeInfo.name}</option>
									</c:forEach>		
								</select>
								<!-- 日期范围 -->
								<input type="text" id="date2" class="date" style="width: 300px;"
									value="" readonly />
								<!-- 标题检索 -->
								<input type="text" class="text" id="title" value="${keyWord}" placeholder="检索文章标题" />
								<!-- 点击查询按钮 -->
								<button class="button blue" style="margin-top: -3px;" onclick="search()">
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
										<c:when test="${fn:length(pageInfo.list)==0}">
											<tr>
												<td colspan="7" style="text-align: center;">暂无记录</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${pageInfo.list}" var="entity"
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
							<!-- 分页的骚操作 -->
							<div class="page">
								<ul id="page" class="pagination"></ul>
							</div>
							<!--块元素-->
							<div class="block no-shadow">
								<!--banner用来修饰块元素的名称-->
								<div class="banner">
									<p class="tab fixed">
										批量操作<span class="hint">Batch Opt</span>
									</p>
								</div>
								<!--正文内容-->
								<div class="main" style="margin-bottom: 200px">
								<label zoom="1.2"><input type="radio" class="fill" name="radio" value="move"/>批量移动到版块</label>
									<select id="type_id2" class="no-shadow">
										<c:forEach items="${typeList}" var="typeInfo"
										varStatus="status">
										<option value="${typeInfo.id}" >${typeInfo.name}</option>
									</c:forEach>	
									</select> 
									<br  />
								<label zoom="1.2"><input type="radio" class="fill" name="radio" value="recycle"/>批量删除</label>
								<br  />
								<button id="submit" class="button green" style="margin-top: 20px;"><span class="icon-check"></span>提交</button>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var currentPage = "${pageInfo.pageNum}";
	var pageCount = "${pageInfo.pages}";
	
	javaex.select({
		id : "type_id",
		isSearch:false
	});
	
	javaex.select({
		id : "type_id2",
		isSearch:false
	});
	
	var startDate="";
	var endDate="";
	
	javaex.date({
		id : "date2",		// 承载日期组件的id
		monthNum : 2,		// 2代表选择范围日期
		alignment:"right",
		startDate : "${startDate}",	// 开始日期
		endDate : "${endDate}",		// 结束日期
		// 重新选择日期之后返回一个时间对象
		callback : function(rtn) {
			startDate=rtn.startDate;
			endDate=rtn.endDate;
			//alert(rtn.startDate + " - " + rtn.endDate);
		}
	});
	
	javaex.page({
		id : "page",
		pageCount : pageCount, // 总页数
		currentPage : currentPage,// 默认选中第几页
		// 返回当前选中的页数
		callback : function(rtn) {
			//alert("当前选中的页数：" + rtn.pageNum);
			search(rtn.pageNum);
		}
	});
	function search(pageNum){
		if(pageNum==undefined){
			pageNum=1;
		}
		//文章分类
		var typeId=$("#type_id").val();
		//关键字检索
		var keyWord=$("#title").val();
		//window.location.href = "list_normal.action?pageNum="+rtn.pageNum;
		window.location.href = "list_normal.action"
		+"?pageNum="+pageNum
		+"&typeId="+typeId
		+"&startDate="+startDate
		+"&endDate="+endDate
		+"&keyWord="+keyWord;
	}
	//批量提交点击事件
	$("#submit").click(function(){
		var idArr=new Array();
		$(':checkbox[name="id"]:checked').each(function(){
			idArr.push($(this).val());
		});
		//判断至少选择一条记录
		if(idArr.length==0){
			javaex.optTip({
				content : "至少选择一条记录",
				type : "error"
			});
		}
		//判断选择了哪一个单选框进行操作
		var opt=$(':radio[name="radio"]:checked').val();
		if(opt=="move"){
			//console.log("move");
			//获取目标的分类的id
			var typeId=$("#type_id2").val();
			$.ajax({
				url : "move.json",
				type : "POST",
				dataType : "json",
				traditional : "true",
				data : {
					"idArr" : idArr,
					"typeId" : typeId
				},
				success : function(rtn) {
					if(rtn.code=="000000"){
						javaex.optTip({
							content : rtn.message
						});
						// 建议延迟加载
						setTimeout(function() {
							// 载入数据
							//$("#content").append(resultHtml);
							// 每次数据载入后，必须重置（需要传入数据区域的id）
							//javaex.resetLoad("content");
							window.location.reload();
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
		}else if (opt=="recycle") {
			//console.log("delete");
			$.ajax({
				url : "recycle.json",
				type : "POST",
				dataType : "json",
				traditional : "true",
				data : {
					"idArr" : idArr,
					"status" : "0"
				},
				success : function(rtn) {
					if(rtn.code=="000000"){
						javaex.optTip({
							content : rtn.message
						});
						// 建议延迟加载
						setTimeout(function() {
							// 载入数据
							//$("#content").append(resultHtml);
							// 每次数据载入后，必须重置（需要传入数据区域的id）
							//javaex.resetLoad("content");
							window.location.reload();
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
		}
	});
</script>
</html>