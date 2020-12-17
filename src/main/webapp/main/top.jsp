<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 <%-- <script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>  --%>
<%-- <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script> --%>
<body>
<nav class="navbar navbar-default absolute">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/Grabpic">Grabpic<span class="sr-only">(current)</span></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
     
        <li class="dropdown" id="fetch_menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">抓取独立页面<span class="caret"></span></a>
          <ul class="dropdown-menu" >
            <li><a href="${APP_PATH }/">独立抓图</a></li>
          	<li role="separator" class="divider"></li>
            <li><a href="${APP_PATH }/picUrlPage">配置抓取页面</a></li>
            <li><a href="${APP_PATH }/picPage">查看抓取结果</a></li>
            

          </ul>
        </li>
 
        <li class="dropdown" id="dbinfo_menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">整站抓取模块<span class="caret"></span></a>
          <ul class="dropdown-menu" >
            <li><a href="${APP_PATH }/sitePage">网站信息</a></li>
            <li><a href="${APP_PATH }/categoryPage">站内分类</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${APP_PATH }/webpagePage">分页链接库</a></li>
            <li><a href="${APP_PATH }/linkPage">图页链接库</a></li>
            <li><a href="${APP_PATH }/webpicPage">图片库</a></li>
          </ul>
        </li>
        
      </ul>
      <form class="navbar-form navbar-left hidden" >
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="button" class="btn btn-default">Submit</button><!-- type="submit"  -->
      </form>
      <ul class="nav navbar-nav navbar-right hidden">
        <li><a href="#">登录用户：admin</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Logout</a></li>
            <li><a href="#">Change Pass</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</body>
<script type="text/javascript">
 	$("a").click(function(){
		var lis =  $(this).parents("ul").addClass("active");
		console.log(lis);
		return true;
	}); 
</script>
<jsp:include page="commom.jsp"></jsp:include>
</html>