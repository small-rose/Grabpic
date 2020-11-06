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
<title>图片预览</title>
</head>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/jQuery.zoom.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<body>
	<input type="hidden" id="urlId" value="${urlId }"/>
	<div class="modal-body" >
		<form class="form-horizontal">
			<div class="form-group">
					<div class="col-sm-1 control-label"><span class="">&nbsp;</span> </div>
					<div class="col-sm-8">
						<button type="button" class="btn btn-warning" id="pic_down_all"><span class="glyphicon glyphicon-circle-arrow-down"></span>批次下载</button>
					</div>
					<div class="col-sm-2 control-label"><span class="">&nbsp;</span></div> 
				</div>
			<c:forEach items="${fileNames }" var="item" varStatus="index">
				<div class="form-group">
					<div class="col-sm-1 control-label"><span class="">&nbsp;</span> </div>
					<div class="col-sm-8">
							<img alt="${item.picOldname}" class="center-block" width="850"
								src="tmpImg/${item.picOldname}">
					</div>
					<div class="col-sm-2 control-label"><span class="">&nbsp;</span></div> 
				</div>
			</c:forEach>
			
		</form>
	</div>
	</body>
<script type="text/javascript">
	$("#pic_down_all").click(function(){
		var urlId = $("#urlId").val();
		alert(urlId)
		window.location.href='${APP_PATH}/downPicList?urlId='+urlId;
	});


	

</script>	
</html>

