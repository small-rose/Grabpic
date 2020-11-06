<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- msg 警告模态框 -->
<div class="modal fade " id="warningModal" tabindex="-100" role="dialog" aria-labelledby="msgModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="warningModalLabel">警告信息</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		 <div class="form-group">
		    <label class="col-sm-4 control-label" id="msgType">提示：</label>
	    	<p class="form-control-static" id="warningMsgContent"></p>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>

<!-- msg 信息提示模态框 -->
<div class="modal fade " id="successModal" tabindex="-100" role="dialog" aria-labelledby="msgModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="successModalLabel">提示信息</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		 <div class="form-group">
		    <label class="col-sm-4 control-label" id="msgType">提示：</label>
	    	<p class="form-control-static" id="successMsgContent"></p>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>

<!-- msg 信息提示模态框 -->
<div class="modal fade " id="errorModal" tabindex="-100" role="dialog" aria-labelledby="msgModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="errorModalLabel">错误信息</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		 <div class="form-group">
		    <label class="col-sm-4 control-label" id="msgType">提示：</label>
	    	<p class="form-control-static" id="errorMsgContent"></p>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="loadingModal" tabindex="-100" >
	<div
		style="width: 200px; height: 20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%; margin-left: -100px; margin-top: -10px">
		<div class="progress progress-striped active"
			style="margin-bottom: 0;">
			<div class="progress-bar" style="width: 100%;"></div>
		</div>
		<h5 style="color: black">
			<strong style="color: red;">正在抓取...请稍等！</strong>
		</h5>
	</div>
</div>
<script type="text/javascript">
	function AlertModal(type,msg){
		if(type==null || type==undefined || type==""){
			type = "success";
		}
		$("#"+type+"MsgContent").text(msg);
		$("#"+type+"Modal").modal({
			backdrop:"static"
		});
		if(type=="success"){
			setTimeout(function(){
	            $("#"+type+"Modal").modal("hide")
	        },1200);
		}
	}
	
	
</script>