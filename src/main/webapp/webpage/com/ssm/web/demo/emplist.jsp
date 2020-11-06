<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<!--  web 路径问题
不以/开始的相对路径，找资源，以当前资源为基础
以/开始的路径，找资源，以服务器路径为标准（http://localhost:8080）
	http://localhost:8080/ssm
-->
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<jsp:include page="../../../../../main/top.jsp"></jsp:include>
<body>
	<div class="container">
		
		<!-- 标题 -->
		<div class="row">
		  <div class="col-md-12">
		  	<h2>SSM-DEMO</h2><h5><a href="/ssm">返回首页</a></h5>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
		    <div class="col-md-4 col-md-offset-8">
		    	  <button type="button" class="btn btn-primary">新增</button>
				  
				  <button type="button" class="btn btn-danger">删除</button>
		    </div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
		    <div class="col-md-12">
		    	<table class="table table-bordered table-hover">
		    		<tr>
		    			<th>#</th>
		    			<th>name</th>
		    			<th>sex</th>
		    			<th>email</th>
		    			<th>deptName</th>
		    			<th>opt</th>
		    		</tr>
		    		<c:forEach items="${pageInfo.list}" var="emp">
		    		<tr>
		    			<th>${emp.empId}</th>
		    			<th>${emp.empName}</th>
		    			<th>${emp.sex=="M"?"男":"女"}</th>
		    			<th>${emp.email}</th>
		    			<th>${emp.department.deptName}</th>
		    			<th>
		    				<button type="button" class="btn btn-info btn-sm">
		    				<span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span></button>
		    				<button type="button" class="btn btn-danger btn-sm">
		    				<span class="glyphicon glyphicon-remove" aria-hidden="true">删除</span></button>
		    			</th>
		    		</tr>
		    		</c:forEach>
		    		<tr>
		    			<th>1</th>
		    			<th>adsa</th>
		    			<th>m</th>
		    			<th>xx@qq.com</th>
		    			<th>AA</th>
		    			<th>
		    				<button type="button" class="btn btn-info btn-sm">
		    				<span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span></button>
		    				<button type="button" class="btn btn-danger btn-sm">
		    				<span class="glyphicon glyphicon-remove" aria-hidden="true">删除</span></button>
		    			</th>
		    		</tr>
		    	</table>
		    </div>
		</div>
		<!-- 分页 -->
		<div class="row">
		    <div class="col-md-6">
				当前第 ${pageInfo.pageNum } 页，总 ${pageInfo.pages}页，总 ${pageInfo.total}条记录
		    </div>
		    <div class="col-md-6">
		    	<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	
				  		<li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
				  	
				  	<c:if test="${pageInfo.hasPreviousPage }">
				  		<li>
					      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				  	</c:if>

				    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
				    	<c:if test="${page_num== pageInfo.pageNum}">
				    		<li class="active"><a href="#">${page_num}</a></li>
				    	</c:if>
				    	<c:if test="${page_num!= pageInfo.pageNum}">
				    		<li><a href="${APP_PATH }/emps?pn=${page_num}">${page_num}</a></li>
				    	</c:if>
				    	
				    </c:forEach>
				    <c:if test="${pageInfo.hasNextPage }">
				    <li>
				      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				    </c:if>
				    <c:if test="${!pageInfo.isLastPage }">
				    </c:if>
				    <li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">末页</a></li>
				  </ul>
				</nav>
		    </div>
		</div>
	</div>
</body>
</html>