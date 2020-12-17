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
<title>网站列表</title>
<!--  web 路径问题
不以/开始的相对路径，找资源，以当前资源为基础
以/开始的路径，找资源，以服务器路径为标准（http://localhost:8080）
	http://localhost:8080/Grabpic
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
		  	<h3>Grabpic-抓取站点 </h3>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
		    <div class="col-md-4 col-md-offset-8">
		    	<button type="button" class="btn btn-success" id="pic_url_refresh"><span class="glyphicon glyphicon-refresh"></span> 刷新</button>
		    	<button type="button" class="btn btn-primary" id="add_model_btn"><span class="glyphicon glyphicon-plus"></span> 新增</button>
				<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> 删除</button>
		    </div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
		    <div class="col-md-12">
		    	<table id="data_table" class="table table-bordered table-hover"  style="table-layout：fixed;word-break;nomal">
		    		<thead>
		    		<tr>
		    			<th hidden="hidden" class="">ID</th>
		    			<th style="">地址</th>
		    			<th class=".col-md-2">名称</th>
		    			<th hidden="hidden" >主页</th>
		    			<th>描述</th>
		    			<th>存档时间</th>
		    			<th>是否开放</th>
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
				url:"${APP_PATH}/saveWebsite",
				type:"POST",
				data:$("#addModal form").serializeArray(),
				cache : false,
				success:function(result){
					if(result.code==200){
						alert(result.message);
						//员工保存成功1.要关闭模态框，2来到最后一页
						$('#addModal').modal('hide');
						to_page(totalPage+1);
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
			url:"${APP_PATH}/websiteAjax",
			data:"pn="+pn+"&pageNum="+pageNum,
			type:"GET",
			success:function(result){
				console.log(result);
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
			var webIdTD = $("<td hidden=\"hidden\"></td>").append(item.webId);
			var webUrlTD = $("<td></td>").append(item.webUrl);
			//var webName = item.webName.length>80 ?item.webName.substring(0,60):item.webName;
			var webNameTD = $("<td  style=\"word-wrap: break-word\"></td>").append(item.webName).attr("data-toggle","tooltip").attr("title",item.webName);
			var webDescTD = $("<td ></td>").append(item.webDesc).attr("data-toggle","tooltip").attr("title",item.webDesc);
			var webOpenTD = $("<td></td>").append(item.webOpen=="1"?"是":"否");
			var addTimeTD = $("<td></td>").append(item.addTime);

			var editBtn = $("<button></button>)").addClass("btn btn-info btn-xs edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
			editBtn.attr("edit-id",item.webId);
			
			var delBtn = $("<button></button>)").addClass("btn btn-danger btn-xs delete_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
			delBtn.attr("delete-id",item.webId);
			
			var fetchBtn = $("<button></button>)").addClass("btn btn-warning btn-xs fetch_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-cloud")).append("抓取页面");
			fetchBtn.attr("fetch-id",item.webId);
			
			var viewBtn = $("<button></button>)").addClass("btn btn-success btn-xs view_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("预览页面");
			viewBtn.attr("view-id",item.webId);

			var opt = $("<td></td>").append(fetchBtn).append(" ").append(viewBtn).append(" ").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(webIdTD).append(webUrlTD).append(webNameTD).append(webDescTD)
						.append(addTimeTD).append(webOpenTD).append(opt)
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
	
	
	function clear_model(){
		$("#inputiWebId").val("");
		$("#inputWebUrl").val("");
		$("#inputWebName").val("");
		$("#inputWebDesc").val("");
		$("#addModal select").val("");
	}
	
	function init_model(result){
		var object = result.extend.object;
		$("#inputiWebId").val(object.webId);
		$("#inputWebUrl").val(object.webUrl);
		$("#inputWebName").val(object.webName);
		$("#inputWebDesc").val(object.webDesc);
		$("#addModal select").val([object.webOpen]);
	}
	//绑定编辑
	$(document).on("click",".edit_btn", function(){
		var webId = $(this).attr("edit-id");
		$.ajax({
			url:"${APP_PATH}/editWebsite",
			data:"id="+webId,
			type:"POST",
			success:function(result){
				console.log(result);
				//清空数据
				clear_model();
				init_model(result);
				$("#addModalLabel").text().replace("新增","修改");
				$("#addModal").modal({
					backdrop:"static"
				});
			}
		});
	});
	
	//绑定删除
	$(document).on("click",".delete_btn", function(){
		var webId = $(this).attr("delete-id");
		$.ajax({
			url:"${APP_PATH}/delWebsite/"+webId,
			//data:"id="+webId,
			type:"DELETE",
			success:function(result){
				if(result.code==200){
					alert(result.message);
				}else{
					alert(result.message);
				}
				to_page(currPageNum);
			}
		});
	});
	
	
	//绑定抓取
	$(document).on("click",".fetch_btn", function(){
		var webId = $(this).attr("fetch-id");
		$.ajax({
			url:"${APP_PATH}/fetchWebSite",
			data:"id="+webId,
			type:"POST",
			success:function(result){
				if(result.code==200){
					alert(result.message);
				}else{
					alert(result.message);
				}
			}
		});
	});
	
	//绑定预览抓取页面
	$(document).on("click",".view_btn", function(){
		var webId = $(this).attr("view-id");
		window.open("${APP_PATH}/viewWebContent?id="+webId, "抓取结果");
	});
	
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
        <h4 class="modal-title" id="addModalLabel">新增WebSite页面</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       		<input type="hidden" name="WebId"  class="form-control" id="inputiWebId" />
		  <div class="form-group">
		    <label for="inputEmpName" class="col-sm-2 control-label">网站地址</label>
		    <div class="col-sm-10">
		      <input type="text" name="webUrl"  class="form-control" id="inputWebUrl" placeholder="请输入网站地址">
		    </div>
		  </div>
		 <div class="form-group">
		    <label for="inputEmpName" class="col-sm-2 control-label">网站名称</label>
		    <div class="col-sm-10">
		      <input type="text" name="webName"  class="form-control" id="inputWebName" placeholder="请输入网站名称">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmpEmail" class="col-sm-2 control-label">网站描述</label>
		    <div class="col-sm-10">
		      <input type="text" name="webDesc" class="form-control" id="inputWebDesc" placeholder="请输入网站描述">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmpEmail" class="col-sm-2 control-label">是否开放</label>
		      <div class="col-sm-4">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="webOpen" id="web_open">
			    	<option value="">请选择</option>
			    	<option value="1">是</option>
			    	<option value="0">否</option>
				</select>
		     
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="save_btn"><span class="glyphicon glyphicon-floppy-save"></span>保存</button>
      </div>
    </div>
  </div>
</div>
</html>