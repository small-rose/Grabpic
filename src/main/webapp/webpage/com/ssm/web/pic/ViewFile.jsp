<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片预览</title>
</head>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<body>
	<div class="modal-body" >
		<form class="form-horizontal">
			<div class="form-group">
				<div class="col-sm-1 control-label"><span class="glyphicon glyphicon-chevron-left">PREV</span> </div>
				<div class="col-sm-8">
					<img alt="${fileName}" class=".center-block"
						src="tmpImg/${fileName}">
				</div>
				<div class="col-sm-2 control-label"><span class="glyphicon glyphicon-chevron-right">NEXT</span>  </div> 
			</div>
		</form>
	</div>
	
</html>

