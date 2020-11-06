<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网页预览</title>
</head>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/format/base.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/format/jsformat.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/format/htmlformat.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript">
var jsreg = "/<script[\s\S]*?<\/script>/i";//识别js\
var bodyReg = '/<b>(((?!<\/b>).)*)<\/b>/mi'; //保留body

function clear_js_handle(){
	var src = document.getElementById('content2').value;
	if(src=="" ||src ==null ||src==undefined){
		alert("请先格式化HTML");
	}
	var newSrc = src.replace(jsreg+"/g","");
	document.getElementById('content2').value = newSrc;
}

function clear_css_handle(){
	var src = document.getElementById('content2').value;
	if(src=="" ||src ==null ||src==undefined){
		alert("请先格式化HTML");
	}
	var newSrc = src.replace(cssReg+"/g","");
	document.getElementById('content2').value = newSrc;
}
function clear_html_handle(){
	var src = document.getElementById('content2').value;
	if(src=="" ||src ==null ||src==undefined){
		alert("请先格式化HTML");
	}
	var newSrc = src.replace(cssReg+"/g","");
	document.getElementById('content2').value = newSrc;
}

function do_js_beautify() {
    //document.getElementById('beautify').disabled = true;
    js_source = document.getElementById('content1').value.replace(/^\s+/, '');
    tabsize = 1 ;//document.getElementById('tabsize').value;
    tabchar = ' ';
    if (tabsize == 1) {
        tabchar = '\t';
    }
    if (js_source && js_source.charAt(0) === '<') {
        document.getElementById('content2').value = style_html(js_source, tabsize, tabchar, 80);
    } else {
        document.getElementById('content2').value = js_beautify(js_source, tabsize, tabchar);
    }
    //document.getElementById('beautify').disabled = false;
    return false;
}

</script>
<body>
	<div class="modal-body">
		<form role="form">
		  <div class="form-group" >
			  <div class="col-sm-6" >
					<button type="button" class="btn btn-warning btn-sm" id="beautify" onclick="return do_js_beautify()">格式化HTML</button>
			  </div>
			   <div class="col-sm-6" >
			   		<button type="button" class="btn btn-warning btn-sm" id="clear_js" onclick="return clear_js_handle()">去掉js</button>
					<button type="button" class="btn btn-warning btn-xs" id="clear_css" onclick="return clear_css_handle()">去掉css</button>
					<button type="button" class="btn btn-warning btn-xs" id="clear_html" onclick="return clear_html_handle()">简化Html</button>
			  </div>
		  </div>
		  <div class="form-group" >
		  	<div class="col-sm-6" id="orgContent">
		    <label for="name"><strong>原网页：</strong>
		    </label><button type="button" class="btn btn-warning btn-xs" id="style_handle_open">展开</button>
		    <button type="button" class="btn btn-warning btn-xs hanle_init" id="style_handle_init">还原</button>
		    <textarea class="form-control" rows="25" id="content1">${object.webContent}</textarea>
		 	</div>
		 	<div class="col-sm-6" id="formatContent">
		    <label for="name"><strong>格式化网页：</strong></label>
		    <button type="button" class="btn btn-warning btn-xs" id="formatContent_open">展开</button>
		    <button type="button" class="btn btn-warning btn-xs hanle_init" id="style_handle_init2">还原</button>
		    <textarea class="form-control" rows="25" id="content2"></textarea>
		 	</div>
		  </div>
		</form>
	</div>
</body>	
<script type="text/javascript">  
    $("#style_handle_open").click(function(){
    	$("#orgContent").removeClass("col-sm-6");
    	$("#formatContent").removeClass("col-sm-6");
    	$("#formatContent").attr("hidden","hidden");
    	$("#orgContent").removeAttr("hidden");
    	$("#orgContent").addClass("col-sm-12");
    });
    $("#formatContent_open").click(function(){
    	$("#orgContent").removeClass("col-sm-6");
    	$("#orgContent").attr("hidden","hidden");
    	$("#formatContent").removeAttr("hidden");
    	$("#formatContent").removeClass("col-sm-6");
    	$("#formatContent").addClass("col-sm-12");
    	
    });
	$(".hanle_init").click(function(){
		$("#orgContent").removeClass("col-sm-12");
    	$("#formatContent").removeClass("col-sm-12");
    	$("#orgContent").removeAttr("hidden");
    	$("#formatContent").removeAttr("hidden");
    	$("#orgContent").addClass("col-sm-6");
    	$("#formatContent").addClass("col-sm-6");
    });
</script>  
</html>
