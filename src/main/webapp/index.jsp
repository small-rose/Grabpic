<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
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
<jsp:include page="main/top.jsp"></jsp:include>
<body>
	<!-- 按钮 -->
	<div class="row">
		<div class="col-md-12">
			
			
			<%-- <button type="button" class="btn btn-danger btn-lg">
			<a href="${APP_PATH }/picture">Fetch Pic</a>
			</button> --%>
		</div>
	</div>
		<div class="container">
		
		<!-- 标题 -->
		<div class="row">
		  <div class="col-md-12">
		  	<h1>Grabpic</h1>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
		    <div class="col-md-4 col-md-offset-8">
		    	  <button type="button" class="btn btn-primary" id="add_model_btn">新增</button>
				  
				  <button type="button" class="btn btn-danger">删除</button>
		    </div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
		    <div class="col-md-12">
		    	<table id="data_table" class="table table-bordered table-hover">
		    		<thead>
		    		<tr>
		    			<th>#</th>
		    			<th>功能名称</th>
		    			<th>描述</th>
		    			<th>opt</th>
		    		</tr>
		    		</thead>
		    		<tbody>
					<tr>
						<th>1</th>
		    			<th>pic fatch</th>
		    			<th>获取网上图片</th>
		    			<th>
							<a href="${APP_PATH }/picUrlPage "><button type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil">Fetch Page</span></button></a>
							<a href="${APP_PATH }/picPage"><button type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil">Fetch Result</span></button></a>
						</th>
					</tr>
					<tr>
						<th>2</th>
		    			<th>图片显示</th>
		    			<th>页面展示图片</th>
		    			<th>
							<a href="${APP_PATH }/viewpicPage"><button type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil">view pic Page</span></button></a>
							<a href="${APP_PATH }/picPage"><button type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil">Fetch Result</span></button></a>
						</th>
					</tr>
		    		</tbody>
		    	</table>
		    </div>
		</div>
		<!-- 分页 -->
		<div class="row">
		    <div class="col-md-6" id="page_info">
		    </div>
		    <div class="col-md-6" id="page_navi">
		    </div>
		</div>
	</div>
</body>
<script type="text/javascript">

	
</script>
</body>
</html> 