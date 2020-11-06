<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:forward page="/emps"></jsp:forward>
<%-- <%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
</head>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<body>
	<!-- 按钮 -->
	<div class="row">
		<div class="col-md-12">
			<button type="button" class="btn btn-primary">
			<a href="${APP_PATH }/empsListDemo">Forward Page Demo</a>
			</button>

			<button type="button" class="btn btn-danger">
			<a href="${APP_PATH }/empsAjaxDemo">Ajax Demo</a>
			</button>
		</div>
	</div>
</body>
</html>  --%>