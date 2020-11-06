<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片显示</title>
</head>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/jQuery.zoom.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<body>
	<h4>显示图片的例子</h4>
	<img alt=""  width="600"  height="400"  src="${APP_PATH}/getImgStream">
	<div></div>
</body>
<script type="text/javascript">
	$(function(){
		
		$.ajax({
			url:"${APP_PATH}/getImgAjax",
			data:{},
			type:"POST",
			success:function(result){
				console.log(result);
			}
		});
	})
	
	
	axios.get('${APP_PATH}/getImgAjax')
	  .then(function (response) {
	    console.log(response);
	  });
</script>
</html>