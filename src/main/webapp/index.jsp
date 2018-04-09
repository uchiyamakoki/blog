<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script>
	window.location.href = "${APP_PATH}/user_info/index.action";
</script>