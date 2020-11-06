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
<title>抓取地址列表</title>
<!--  web 路径问题
不以/开始的相对路径，找资源，以当前资源为基础
以/开始的路径，找资源，以服务器路径为标准（http://localhost:8080）
	http://localhost:8080/ssm
-->
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
td {
overflow: hidden;
white-space: nowrap;
text-overflow: ellipsis;
}
</style>
</head>
<jsp:include page="../../../../../main/top.jsp"></jsp:include>
<body>
	<div class="container" >
		
		<!-- 标题 -->
		<div class="row">
		  <div class="col-md-12">
		  	<h2>SSM-- Fetch Page </h2><h5><a href="/ssm">返回首页</a></h5>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
		    <div class="col-md-4 col-md-offset-8">
		    	<button type="button" class="btn btn-success" id="pic_url_refresh"><span class="glyphicon glyphicon-search"></span>刷新</button>
		    	<button type="button" class="btn btn-primary" id="add_model_btn"><span class="glyphicon glyphicon-plus"></span>新增</button>
				<button type="button" class="btn btn-danger">删除</button>
		    </div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
		    <div class="col-md-12">
		    	<table id="data_table" class="table table-bordered table-hover"  style="table-layout：fixed;word-break;nomal">
		    		<thead>
		    		<tr>
		    			<th hidden="hidden" class="">编号</th>
		    			<th style="">地址</th>
		    			<th class=".col-md-2">描述</th>
		    			<!-- <th>isDel[1-ok,0-del]</th> -->
		    			<th>是否收藏</th>
		    			<th>是否抓取</th>
		    			<th>操作</th>
		    		</tr>
		    		</thead>
		    		<tbody>
		    		
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
	var totalPage ;//总页数，也可以使用总记录数
	var currPageNum ;
	//1页面加载完成后发ajax
	$(function(){
		to_page(1);
		//绑定单击保存
		$("#save_btn").click(function(){
			//1.将模态框的内容提交后台保存到数据库
			//alert($("#addModal form").serialize());
			//2.发送ajax请求保存
	 		$.ajax({
				url:"${APP_PATH}/urlSave",
				type:"POST",
				data:$("#addModal form").serializeArray(),
				cache : false,
				success:function(result){
					if(result.code==200){
						alert(result.message);
						//员工保存成功1.要关闭模态框，2来到最后一页
						$('#addModal').modal('hide');
						to_page(1);
					}else{
						alert(result.message);
					}
				},
				error:function(){
					alert("save error ");
				}
			});  
			
		});
		
		//点击新增，弹出模态框
		$("#add_model_btn").click(function(){
			//弹出模态框
			clear_model();
			$("#addModal").modal({
				backdrop:"static"
			});
		});
	});
	
	
	function to_page(pn){
		var pageNum = $(".page_size_change").children('option:selected').val();
		if(undefined==pageNum){
			pageNum = 5;
		}
		$.ajax({
			url:"${APP_PATH}/picUrlAjax",
			data:"pn="+pn+"&pageNum="+pageNum,
			type:"get",
			success:function(result){
				//console.log(result);
				//清空数据
				clear_table();
				//1解析并显示数据
				build_data_table(result);
				//2解析并显示分页
				build_page_info(result);
				//2解析并显示分页导航
				build_page_navi(result);
			}
		});
	}
	//解析显示数据
	function build_data_table(result){
		
		var data = result.extend.pageInfo.list;
		$.each(data,function(index,item){
			//alert(" item"+item.empName);
			var id = $("<td hidden=\"hidden\"></td>").append(item.id);
			var tmpname = item.addrName.length>80 ?item.addrName.substring(0,60):item.addrName;
			var addrName = $("<td  style=\"word-wrap: break-word\"></td>").append(tmpname).attr("data-toggle","tooltip").attr("title",item.addrName);
			var addrDesc = $("<td ></td>").append(item.addrDesc).attr("data-toggle","tooltip").attr("title",item.addrDesc);
			var isDel = $("<td></td>").append(item.isDel);
			var isMark = $("<td></td>").append(item.isMark=="1"?"已收藏":"未收藏");
			var isFetch = $("<td></td>").append(item.isFetch=="1"?"已抓取":"未抓取");
			

			var editBtn = $("<button></button>)").addClass("btn btn-info btn-xs")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
		
			editBtn.click(function(){
				edit_page_handle(item.id);
			});
			var delBtn = $("<button></button>)").addClass("btn btn-danger btn-xs")
						.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
			delBtn.click(function(){
				delete_handle(item.id);
			});
			
			var fetchBtn = $("<button></button>)").addClass("btn btn-warning btn-xs")
						.append($("<span></span>").addClass("glyphicon glyphicon-cloud")).append("抓取");
			fetchBtn.click(function(){
				fetchBtn_handle(item.id);
			});
			
			var resultBtn = $("<button></button>)").addClass("btn btn-success btn-xs")
						.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("抓取结果");
			resultBtn.click(function(){
				view_result_handle(item.id);
			});
			
			var clearReesultBtn = $("<button></button>)").addClass("btn btn-danger btn-xs")
					.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("清空抓取结果");
			clearReesultBtn.click(function(){
				clear_result_handle(item.id);
			});
			var opt = $("<td></td>").append(fetchBtn).append(" ").append(resultBtn).append(" ").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(id)
						.append(addrName)
						.append(addrDesc)
						//.append(isDel)
						.append(isMark)
						.append(isFetch)
						.append(opt)
						.appendTo("#data_table tbody");
		});
	}
	
	function clear_table(){
		$("#data_table tbody").empty();
		$("#page_info").empty();
		$("#page_navi").empty();
	}
	
	//解析显示分页信息
	function build_page_info(result){
		var pageInfo = result.extend.pageInfo;
		totalPage = pageInfo.pages;
		currPageNum = pageInfo.pageNum;
		var pageSize = pageInfo.navigatePages;
		var select = $("<select></select>");//.addClass("form-control");
		for(var i = 1 ;i<20 ;i ++){
			if( (i*5) == pageSize){
				select.append($("<option selected=\"selected\">"+(i*5)+"</option>"));
			}else{
				select.append($("<option>"+(i*5)+"</option>"));
			}
		}
		select.addClass("page_size_change");
		$("#page_info").append("当前第 "+pageInfo.pageNum +" 页，总 "+pageInfo.pages+"页，总 "+pageInfo.total+"条记录");
		$("#page_info").append(",每页显示").append(select).append("条");
	}
	
	//解析显示分页条
	function build_page_navi(result){
		var pageInfo = result.extend.pageInfo ;
		var ulObj = $("<ul></ul>").addClass("pagination");
		//首页
		var firstli = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		//前一页
		var preli = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
		if(!pageInfo.hasPreviousPage){
			firstli.addClass("disabled");
			preli.addClass("disabled");
		}else{
			firstli.click(function(){
				to_page(1);
			});
			preli.click(function(){
				to_page(pageInfo.pageNum-1);
			});
		}
		
		//后一页
		var nextli = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
		//末页
		var lastli = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		if(!pageInfo.hasNextPage){
			nextli.addClass("disabled");
			lastli.addClass("disabled");
		}else{
			nextli.click(function(){
				to_page(pageInfo.pageNum+1);
			});
			lastli.click(function(){
				to_page(pageInfo.pages);
			});
		}
		
		//组装
		ulObj.append(firstli).append(preli);
		var pages = result.extend.pageInfo.navigatepageNums;
		$.each(pages,function(index,item){
			var numli = $("<li></li>").append($("<a></a>").append(item).attr("href","#"));
			if(item==pageInfo.pageNum){
				numli.addClass("active");
			}
			numli.click(function(){
				to_page(item);
			});
			ulObj.append(numli);
		});
		ulObj.append(nextli).append(lastli);
		//把ul放入nav元素
		var navEle = $("<nav></nav>").append(ulObj);
		//把nav元素放入$("#page_navi")
		navEle.appendTo("#page_navi");
	}
	
	function edit_page_handle(id){
		$.ajax({
			url:"${APP_PATH}/editUrl",
			data:"id="+id,
			type:"POST",
			success:function(result){
				//console.log(result);
				//清空数据
				clear_model();
				init_model(result);
				$("#addModalLabel").text("修改抓取页面");
				$("#addModal").modal({
					backdrop:"static"
				});
			}
		});
	}
	
	function delete_handle(id){
		$.ajax({
			url:"${APP_PATH}/delUrl",
			data:"id="+id,
			type:"POST",
			success:function(result){
				if(result.code==200){
					alert(result.message);
				}else{
					alert(result.message);
				}
				to_page(currPageNum);
			}
		});
	}
	function clear_model(){
		$("#inputid").val("");
		$("#inputAddrName").val("");
		$("#inputAddrDesc").val("");
	}
	
	function init_model(result){
		var picUrl = result.extend.picUrl;
		$("#inputid").val(picUrl.id);
		$("#inputAddrName").val(picUrl.addrName);
		$("#inputAddrDesc").val(picUrl.addrDesc);
	}
	
	function fetchBtn_handle(id){
		$.ajax({
			url:"${APP_PATH}/fetchUrl",
			data:"id="+id,
			type:"POST",
			success:function(result){
				if(result.code==200){
					alert(result.message);
				}else{
					alert(result.message);
				}
			}
		});
	}
	
	function view_result_handle(id){
		window.open("${APP_PATH}/picPage?urlId="+id, "抓取结果");
	}
	
	$("#pic_url_refresh").click(function(){
		window.location.reload(true);
	});
	
	var tmpSize = 5 ;
	$(document).on("change",".page_size_change",function(){
		var p1 =$(this).children('option:selected').val();//这就是selected的值 
		if(p1!=tmpSize){
			tmpSize = p1;
			to_page(1);
		}
	});
</script>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">新增抓取页面</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       		<input type="hidden" name="id"  class="form-control" id="inputid" />
		  <div class="form-group">
		    <label for="inputEmpName" class="col-sm-2 control-label">抓取地址</label>
		    <div class="col-sm-10">
		      <input type="text" name="addrName"  class="form-control" id="inputAddrName" placeholder="图片网页地址">
		    </div>
		  </div>
		 
		  <div class="form-group">
		    <label for="inputEmpEmail" class="col-sm-2 control-label">网页描述</label>
		    <div class="col-sm-10">
		      <input type="text" name="addrDesc" class="form-control" id="inputAddrDesc" placeholder="图片网页地址描述">
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="save_btn">保存</button>
      </div>
    </div>
  </div>
</div>
</html>