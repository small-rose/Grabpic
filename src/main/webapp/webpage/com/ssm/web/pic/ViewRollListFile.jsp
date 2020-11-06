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
<title>相册预览</title>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;background: #494A5F;}
a,img{border:0;}
.blank30{height:10px;overflow:hidden;}
/* jQuery jcImgScroll */
/* .jcImgScroll{position:relative;height:380px;margin:40px auto 0 auto;} */
.jcImgScroll{position:relative;height:680px;margin:20px auto 0 auto;}
.jcImgScroll li{border:1px solid #ccc;}
.jcImgScroll li a{background:#fff;display:block;position:relative;z-index:99;}
.jcImgScroll li.loading a{background:#fff url(${APP_PATH}/static/js/images/loading.gif) no-repeat center center;} 
.jcImgScroll li img,.jcImgScroll li,.jcImgScroll em,.jcImgScroll dl{display:none;border:0 none;}
.jcImgScroll li img{width: 100%;height: 100%;}
.jcImgScroll em.sPrev{background:url(${APP_PATH}/static/js/images/arrow-left.png) no-repeat left center;}
.jcImgScroll em.sNext{background:url(${APP_PATH}/static/js/images/arrow-right.png) no-repeat right center;}
.jcImgScroll dl dd{background:url(${APP_PATH}/static/js/images/NumBtn.png) no-repeat 0 bottom;text-indent:-9em;}
.jcImgScroll dl dd:hover,.jcImgScroll dl dd.curr{background-position:0 0;}
</style>
</head>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/jQuery-easing.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/jQuery-jcImgScroll.js"></script>

<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<body>
	<input type="hidden" id="urlId" value="${urlId }"/>
	<div class="modal-body" >
		<form class="form-horizontal">
			<div class="form-group">
					<div class="col-sm-1 control-label"><span class="">&nbsp;</span> </div>
					<div class="col-sm-8">
						<button type="button" class="btn btn-warning" id="pic_down_all">批次下载</button>
					</div>
					<div class="col-sm-2 control-label"><span class="">&nbsp;</span>  </div> 
				</div>
		</form>
		<div id="demo" class="jcImgScroll">
			<ul>
				<c:forEach items="${fileNames }" var="item">
					<li><a href="#" path="tmpImg/${item.picOldname}"></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#demo").jcImgScroll({
		arrow : {
			//width:45,	
			width:200,	
			//height:400,
			height:550,
			x:60,
			y:0
		},
		//width : 330, //设置图片宽度
		//height:469, //设置图片高度
		width : 530, //设置图片宽度
		height:669, //设置图片高度
		imgtop:22,//每张图片的上下偏移量
		imgleft:-10,//每张图片的左边偏移量
		imgwidth:30,//每张图片的宽度偏移量
		imgheight:44,//每张图片的高度偏移量
		count : 9,
		offsetX : 60,
		NumBtn : false,
		title:false,
		setZoom:.8,
	});
});
</script>
</html>

