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
<title>站内分类列表</title>
<!--  web 路径问题
不以/开始的相对路径，找资源，以当前资源为基础
以/开始的路径，找资源，以服务器路径为标准（http://localhost:8080）
	http://localhost:8080/ssm
-->
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../../../../../main/top.jsp"></jsp:include>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
		  <div class="col-md-12">
		  	<h3>Grabpic-站内分类</h3>
		  	<hr>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-12">
				<form class="form-inline" id="queryForm">
					  <div class="form-group">
					    <label for="querywebName">网站名称</label>
					    <input type="text" name="webName" class="form-control" id="querywebName" placeholder="">
					  </div>
					  <div class="form-group">
					    <label for="querycategoryName">分类名称</label>
					    <input type="text" name="categoryName"  class="form-control" id="querycategoryName" placeholder="">
					  </div>
					  <!-- <div class="form-group">
					    <label for="exampleInputEmail2">文件格式</label>
					    <input type="text" name="picSuffix"  class="form-control" id="querypicSuffix" placeholder=".gif|.jpg|.jpeg|.png|.GIF|.JPG|.PNG)">
					  </div> -->
					  <div class="form-group">
					    <label class="control-label">是否收藏</label>
						    <select class="form-control" name="categoryMark" id="isMark">
						    	<option value="">请选择</option>
						    	<option value="1">已收藏</option>
						    	<option value="0">未收藏</option>
							</select>
					  </div>
						 <button type="button" class="btn btn-primary btn-ms" id="query_handle"><span class="glyphicon glyphicon-search"></span>查询</button>
						 <button type="button" class="btn btn-primary btn-ms" id="query_delete_all"><span class="glyphicon glyphicon-remove"></span>清空条件</button>
						 <!-- <button type="button" class="btn btn-danger" id="pic_delete_all"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
					  	 <button type="button" class="btn btn-success" id="pic_refresh"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
					  	  -->
					</form>
					 
			</div>
			<div class="col-md-12">
		    	<!--  <div class="col-md-4 "> col-md-offset-8 -->
				  <button type="button" class="btn btn-success" id="pic_refresh"><span class="glyphicon glyphicon-search"></span> 刷新</button>
				  <button type="button" class="btn btn-danger" id="pic_delete_all"><span class="glyphicon glyphicon-trash"></span> 全部删除</button>
				  <button type="button" class="btn btn-primary" id="add_model_btn"><span class="glyphicon glyphicon-plus"></span> 新增</button>
		    	  <button type="button" class="btn btn-warning" id="update_model_btn"><span class="glyphicon glyphicon-pencil"></span> 批量更新规则</button>
		    	
		    	<!--</div> -->
		    </div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
		    <div class="col-md-12">
		    	<table id="data_table" class="table table-bordered table-hover" >
		    		<thead>
		    		<tr>
		    			<th><input type="checkbox" id="check_all"/></th>
		    			<th hidden="hidden">#</th>
		    			<th>网址</th>
		    			<th>站称</th>
		    			<th>分类地址</th>
		    			<th>分类名称</th>
		    			<th hidden="hidden">父级分类名称</th> 
		    			<th>存档时间</th>
		    			<th>收藏</th>
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
	var currPageNum ;//总页数，也可以使用总记录数
	//1页面加载完成后发ajax
	$(function(){
		to_page(1);
		//绑定单击保存
		$("#save_btn").click(function(){
			//1.将模态框的内容提交后台保存到数据库
			//alert($("#addModal form").serialize());
			if(!validate_add_form()){
				return false;
			}
			//alert($("#addModal form").serializeArray());
			//2.发送ajax请求保存
	 		$.ajax({
				url:"${APP_PATH}/saveCategory",
				type:"POST",
				data:$("#addModal form").serializeArray(),
				cache : false,
				success:function(result){
					if(result.code==200){
						alert(result.message);
						//$('#successMsgContent').text("");
						//$('#successMsgContent').text(result.message);
						//$('#successModal').modal({backdrop:"static"});
						//保存成功1.要关闭模态框，2来到最后一页
						$('#addModal').modal('hide');
						to_page(currPageNum);
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
			//alert("add_model_btn click ");
			//获取部门下拉列表
			getWebsites("input");
			$("#addModalLabel").text("新增站内分类");
			//弹出模态框
			$("#parentId").hide();
			$("#addModal").modal({
				backdrop:"static"
			});
		});
	});
	
	function getWebsites(key){
		$.ajax({
			url:"${APP_PATH}/websiteSelect",
			data:"",
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#"+key+"_web_id").empty();
				$("#"+key+"_web_id").append($("<option value=\"\">请选择</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.webName+"-"+this.webUrl).attr("value",this.webId);
					$("#"+key+"_web_id").append(optionEl);
				});
				
			}
		});
	}
	function to_page(pn){
		var query = $("#queryForm").serializeArray();
		console.log(query);
		var pageNum = $(".page_size_change").children('option:selected').val();
		if(undefined==pageNum){
			pageNum = 5;
		}
		console.log(pageNum);
		$.ajax({
			url:"${APP_PATH}/categoryAjax",
			data:"pn="+pn+"&pageNum="+pageNum+"&"+query,
			type:"POST",
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
			var checkBox = $("<td></td>").append($("<input type=\"checkbox\"/>").addClass("check_item"));
			var categoryIdTD = $("<td hidden=\"hidden\"></td>").append(item.categoryId);
			var webUrlTD = $("<td ></td>").append(item.webSite.webUrl);
			var webNameTD = $("<td></td>").append(item.webSite.webName);
			var  categoryUrl = (item.categoryUrl!=null && item.categoryUrl.length>30 )?item.categoryUrl.substring(0,30):"";
			var categoryUrlTD = $("<td></td>").append(categoryUrl).attr("data-toggle","tooltip").attr("title",item.categoryUrl);
			var categoryNameTD = $("<td></td>").append(item.categoryName);
			var parentName = item.parent ==null ? "" :item.parent.categoryName ;
			var parentIdTD = $("<td hidden=\"hidden\"></td>").append(parentName);
			var addTimeTD = $("<td></td>").append(item.addTime);
			var categoryMarkTD = $("<td></td>");
			if(item.categoryMark=="1"){
				categoryMarkTD.append($("<span></span>").addClass("glyphicon glyphicon-star")).append("");
			}else{
				categoryMarkTD.append($("<span></span>").addClass("glyphicon glyphicon-star-empty")).append("");
			}
			//var categoryMarkTD = $("<td></td>").append(item.isMark=="1"?"已收藏":"未收藏");
			categoryMarkTD.click(function(){
				mark_handle(item.categoryId,result);
			});
			
			var editBtn = $("<button></button>").addClass("btn btn-info btn-xs edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
			editBtn.attr("edit-id",item.categoryId);

			var viewBtn = $("<button></button>").addClass("btn btn-info btn-xs view_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("查看");
			viewBtn.attr("view-id",item.categoryId);
			
			var linkBtn = $("<button></button>").addClass("btn btn-info btn-xs link_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-share-alt")).append("前往");
			linkBtn.attr("link-id",item.categoryId);
			
			var fetchBtn = $("<button></button>").addClass("btn btn-warning btn-xs fetch_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-arrow-down")).append("抓分页");
			fetchBtn.attr("fetch-id",item.categoryId);
			
			var resultBtn = $("<button></button>").addClass("btn btn-success btn-xs result_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon")).append(" 看结果");
			resultBtn.attr("result-id",item.categoryId);
			
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs del_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			delBtn.attr("del-id",item.categoryId);
			
			var opt = $("<td></td>").append(editBtn).append(" ").append(viewBtn).append(" ")
						.append(linkBtn).append(" ").append(fetchBtn).append(" ").append(resultBtn).append(" ").append(delBtn);
			var trObj =  $("<tr></tr>").append(checkBox).append(categoryIdTD)
						.append(webUrlTD).append(webNameTD).append(categoryUrlTD)
						.append(categoryNameTD).append(parentIdTD)
						.append(addTimeTD).append(categoryMarkTD).append(opt);
			trObj.appendTo("#data_table tbody");
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
		var pageInfo = result.extend.pageInfo
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
		$("#addModal form")[0].reset();
	}
	
	function init_add_model(result,type){
		var object = result.extend.object;
		$("#input_web_id").val([object.webSite.webId]);
		$("#input_categoryUrl").val(object.categoryUrl);
		$("#input_categoryName").val(object.categoryName);
		$("#input_pageTag").val(object.pageTag);
		$("#input_pageTagAttr").val(object.pageTagAttr);
		$("#input_pageKeys").val(object.pageKeys);
		var parentId = object.parentId;
		if(parentId!=null && parentId.length){
			$("#input_is_child").prop("checked",true);
			$("#parentId").show();
			$("input_parentId").val([parentId])
		}else{
			$("#parentId").hide();
		}
		
		if(type=="edit"){
			$("addModalLabel").text("编辑站内分类");
			$("#input_categoryId").val(object.categoryId);
		}
		$("#addModal input").removeAttr("readonly");
		$("#addModal select").removeAttr("disabled");
		$("#addModal input:checkbox").removeAttr("disabled");
		$("#save_btn").show();
		if(type=="view"){
			$("addModalLabel").text("查看站内分类");
			$("#addModal input").attr("readonly","readonly");
			$("#addModal select").attr("disabled","disabled");
			$("#addModal input:checkbox").attr("disabled","disabled");
			$("#save_btn").hide();
		}
	}
	
	//编辑
	$(document).on("click",".edit_btn",function (){
		getWebsites("input");
		var id = $(this).attr("edit-id");
		$.ajax({
			url:"${APP_PATH}/editCategory",
			data:"id="+id,
			type:"POST",
			success:function(result){
				clear_model();
				init_add_model(result,"edit");
				$(".modal-title").text("编辑站内分类");
				$("#addModal").modal({
					backdrop:"static"
				});
			}
		});
	});
	
	//查看
	$(document).on("click",".view_btn",function (){
		getWebsites("input");
		var id = $(this).attr("view-id");
		$.ajax({
			url:"${APP_PATH}/editCategory",
			data:"id="+id,
			type:"POST",
			success:function(result){
				console.log(result);
				//清空数据
				clear_model();
				init_add_model(result,"view");
				
				$("#addModal").modal({
					backdrop:"static"
				});
			}
		});
	});
	
	$(document).on("click",".link_btn",function(){
		var link = $(this).parents("tr").find("td:eq(4)").attr("title");
		window.open(link, "前往");
	});
	
	//抓取
	$(document).on("click",".fetch_btn",function (){
		$("#loadingModal").modal('show');
		var id = $(this).attr("fetch-id");
		$.ajax({
			url:"${APP_PATH}/fetchList",
			data:"id="+id,
			type:"POST",
			success:function(result){
				console.log(result);
				if(result.code==200){
					AlertModal("success",result.message);
				}else{
					AlertModal("error",result.message);
				}
				$('#loadingModal').modal('hide');
			}
		});
	});
	
	$(document).on("click",".result_btn",function (){
		var categoryid = $(this).attr("result-id");
		window.open("${APP_PATH}/webpagePage?categoryid="+categoryid, "抓取分页结果");
	});
	
	function mark_handle(id,result){
		var pageInfo = result.extend.pageInfo;
		$.ajax({
			url:"${APP_PATH}/markCategory",
			data:"id="+id,
			type:"POST",
			success:function(result){
				if(result.code==200){
					//alert(result.message);
					AlertModal("success",result.message);
				}else{
					//alert(result.message);
					AlertModal("warning",result.message);
				}
				//alert("333");
				to_page(currPageNum);
			}
		});
	}

	$(document).on("click", ".del_btn",function(){
		//弹出确认框
		var categoryName = $(this).parents("tr").find("td:eq(5)").text();
		var  ids = $(this).attr("del-id");
		if(confirm("确认删除【"+categoryName+" 】吗?")){
			del_handle(ids);
		}
	});
	
	function del_handle(ids){
		$.ajax({
			url:"${APP_PATH}/delCategory/"+ids,
			//data:"ids="+ids,
			type:"DELETE",
			success:function(result){
				if(result.code==200){
					//alert(result.message);
					AlertModal("success",result.message);
				}else{
					//alert(result.message);
					AlertModal("warning",result.message);
				}
				to_page(currPageNum);
			}
		});
	}
	$("#check_all").click(function(){
		//atrr获取checked是undefined
		//attr读取自定义属性的值
		//prop修改读取DOM原生属性的值
		//$(this).prop("checked");
		$(".check_item").prop("checked",$(this).prop("checked"));
	});
	
	$(document).on("click",".check_item",function(){
		//alert($(".check_item:checked").length +" ---"+$(".check_item").length);
		var flag =$(".check_item:checked").length == $(".check_item").length ;
		$("#check_all").prop("checked",flag);
	});
	
	$("#pic_delete_all").click(function(){
		var picNames = "";
		var ids = "";
		$.each($(".check_item:checked"),function(){
			//找第三个
			picNames += $(this).parents("tr").find("td:eq(3)").text()+",";
			ids += $(this).parents("tr").find("td:eq(1)").text()+",";
		});
		picNames = picNames.length>1 ? picNames.substring(0,picNames.length-1) :picNames ;
		if(ids==""){
			AlertModal("warning","请选择要删除的记录");
			return false;
		}
		//alert(ids);
		//return ;
		if(confirm("确认删除【"+picNames+" 】吗?")){
			del_handle(ids);
		}
		$("#check_all").prop("checked",false);
	});
	
	$("#pic_refresh").click(function(){
		window.location.reload(true);
	});
	
	$("#query_handle").click(function(){
		to_page(1);
	});
	
	$("#query_delete_all").click(function(){
		$("#queryForm")[0].reset();
		$("#querywebName").val("");
		$("#querycategoryName").val("");
		$("#isMark").val("");
	});
	
	
	var tmpSize = 5 ;
	$(document).on("change",".page_size_change",function(){
		var p1 =$(this).children('option:selected').val();//这就是selected的值 
		if(p1!=tmpSize){
			tmpSize = p1;
			to_page(1);
		}
	});
	$(document).on("click",".redown_btn",function(){
		var  picId = $(this).attr("redown-id");
		$.ajax({
			url:"${APP_PATH}/redownPic/"+picId,
			//data:"ids="+ids,
			type:"POST",
			success:function(result){
				if(result.code==200){
					AlertModal("success",result.message);
				}else{
					AlertModal("error",result.message);
				}
				to_page(currPageNum);
			}
		});
	});
	
	$(document).on("click","#input_is_child",function(){
		if($("#input_is_child").prop("checked")){
			$("#parentId").show();
		}else{
			$("#parentId").hide();
			$("#input_parentId").val("");
		}
	});
	
	$(document).on("change","#input_web_id",function(){
		var webId = $("#input_web_id option:selected").val();
		//alert(webId);
		$.ajax({
			url:"${APP_PATH}/categorylist",
			data:"webId="+webId,
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#input_parentId").empty();
				$("#input_parentId").append($("<option value=\"\">请选择</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.categoryName+"-"+this.categoryUrl).attr("value",this.categoryId);
					$("#input_parentId").append(optionEl);
				});
				
			}
		});
	});
	

	function validate_add_form(){
		var webId = $("#input_web_id option:selected").val();
		if(webId==null || webId=="" || webId==undefined){
			return show_validate_msg("#input_web_id","error","请选择网站");
		}else{
			show_validate_msg("#input_web_id","success","");
		}

		var categoryUrl = $("#input_categoryUrl").val();
		if(categoryUrl==null || categoryUrl=="" || categoryUrl==undefined){
			return show_validate_msg("#input_categoryUrl","error","请填写分类地址");;
		}else{
			show_validate_msg("#input_categoryUrl","success","");
		}
		var categoryName = $("#input_categoryName").val();
		if(categoryName==null || categoryName=="" || categoryName==undefined){
			return show_validate_msg("#input_categoryName","error","请填写分类地址名称");
		}else{
			show_validate_msg("#input_categoryName","success","");
		}
		
		if($("#input_is_child").prop("checked")){
			var parentId = $("#input_parentId option:selected").val();
			if(parentId==null || parentId=="" || parentId==undefined){
				return show_validate_msg("#input_parentId","error","子类别请选择父级类型");
			}else{
				show_validate_msg("#input_parentId","success","");
			}
		}
		return true;
	}
	
	function show_validate_msg(ele,status,msg){
		//清除当前元素校验状态
		$(ele).parent().removeClass("has-error has-success");
		$(ele).next("span").html("");
		if("error"==status){
			$(ele).parent().addClass("has-error");
			var picMsg = $("<span></span>").addClass("glyphicon glyphicon-remove").append(msg);
			$(ele).next("span").html(picMsg);
			return false;
		}else if("success"==status){
			$(ele).parent().addClass("has-success");
			//var picMsg = $("<span></span>").addClass("glyphicon glyphicon-ok").append("校验通过");
			$(ele).next("span").html("");
		}
	}
	
	$(document).on("change","#update_web_id",function(){
		var webId = $("#update_web_id option:selected").val();
		//alert(webId);
		$.ajax({
			url:"${APP_PATH}/categorylist",
			data:"webId="+webId,
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#update_categoryId").empty();
				$("#update_categoryId").append($("<option value=\"\">全部分类</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.categoryName+"-"+this.categoryUrl).attr("value",this.categoryId);
					$("#update_categoryId").append(optionEl);
				});
			}
		});
	}).trigger("change");
	
	$("#update_model_btn").click(function(){
		//获取部门下拉列表
		getWebsites("update");
		//弹出模态框
		 $("#updateModal").modal({
			show: true,
			backdrop:"static"
		}); 

	});
	
	$(document).on("click","#update_btn",function(){
		$.ajax({
			url:"${APP_PATH}/updatePageRules",
			type:"POST",
			data:$("#updateModal form").serializeArray(),
			cache : false,
			success:function(result){
				if(result.code==200){
					alert(result.message);
					$('#updateModal').modal('hide');
					to_page(currPageNum);
				}else{
					alert(result.message);
				}
			},
			error:function(){
				alert("save error ");
			}
		});  
	});
</script>
<!-- 新增模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">新增站内分类</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       	  <div class="form-group">
       	  	<input type="hidden" name="categoryId" class="form-control" id="input_categoryId">
       	   <label for="input_web_id" class="col-sm-2 control-label">网站名称</label>
		      <div class="col-sm-5">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="webId" id="input_web_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
		  <div class="form-group">
		    <label for="inputEmpName" class="col-sm-2 control-label">分类地址</label>
		    <div class="col-sm-10">
		      <input type="text" name="categoryUrl" class="form-control" id="input_categoryUrl" placeholder="分类地址">
		      <span id="webUrl_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmpEmail" class="col-sm-2 control-label">分类地址名称</label>
		    <div class="col-sm-10">
		      <input type="text"name="categoryName" class="form-control" id="input_categoryName" placeholder="分类地址名称" >
		      <span id="webName_block" class="help-block"></span>
		    </div>
		  </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">是否子分类</label>
		    <div class="col-sm-10">
		    	<input type="checkbox"  id="input_is_child" > 是
		    </div>
		  </div>
		  <div class="form-group" id="parentId" >
       	   <label for="input_web_id" class="col-sm-2 control-label">父级分类名称</label>
		      <div class="col-sm-4">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="parentId" id="input_parentId">
			    	<option value="">请选择</option>
				</select>
		     	<span id="parentId_block" class="help-block"></span>
		    </div>
		   </div>
		   <div class="form-group">
		    <label for="inputpageTag" class="col-sm-2 control-label">分页链接标签</label>
		    <div class="col-sm-10">
		      <input type="text" name="pageTag" class="form-control" id="input_pageTag" data-toggle="tooltip" title="分页链接标签,一般是a标签" value="a">
		      <span id="webUrl_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputpageTagAttr" class="col-sm-2 control-label">分页链接标签属性</label>
		    <div class="col-sm-10">
		      <input type="text"name="pageTagAttr" class="form-control" id="input_pageTagAttr" data-toggle="tooltip" title="抓取分页链接标签属性值,默认是a标签的属性href" value="abs:href">
		      <span id="webName_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputpageKeys" class="col-sm-2 control-label">分页链接关键字</label>
		    <div class="col-sm-10">
		      <input type="text"name="pageKeys" class="form-control" id="input_pageKeys" placeholder="分页链接中必有的关键字" >
		      <span id="webName_block" class="help-block"></span>
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
</div>

<!-- 批量更新 -->
<div class="modal fade" id="updateModal" tabindex="-10" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="updateModalLabel">批量更新抓取分页规则</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       	<div class="form-group">
       	   <label for="update_web_id" class="col-sm-2 control-label">网站地址</label>
		      <div class="col-sm-5">
		    	 <select class="form-control" name="webId" id="update_web_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
       	  <div class="form-group">
       	   <label for="update_categoryId" class="col-sm-2 control-label">网站分类名称</label>
		      <div class="col-sm-6">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="categoryId" id="update_categoryId">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
		   <div class="form-group">
       	   	  <label for="update_categoryId" class="col-sm-2 control-label">设置规则</label>
		      <div class="col-sm-6">
		    	<!-- 提交部门ID -->
		     	<span id="web_id_block" class="help-block">分页抓取规则设置</span></span>
		    </div>
		   </div>
		   <div class="form-group">
		    <label for="update_pageTag" class="col-sm-2 control-label">分页链接标签</label>
		    <div class="col-sm-10">
		      <input type="text" name="pageTag" class="form-control" id="update_pageTag" data-toggle="tooltip" title="图页链接标签,一般是a标签" value="a">
		      <span id="linkTag_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="update_pageTagAttr" class="col-sm-2 control-label">分页链接标签属性</label>
		    <div class="col-sm-10">
		      <input type="text"name="pageTagAttr" class="form-control" id="update_pageTagAttr" data-toggle="tooltip" title="抓取图页链接标签属性值,默认是a标签的属性href" value="abs:href">
		      <span id="linkTagAttr_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="update_pageKeys" class="col-sm-2 control-label">分页链接关键字</label>
		    <div class="col-sm-10">
		      <input type="text"name="pageKeys" class="form-control" id="update_pageKeys" placeholder="分页链接中必有的关键字" >
		      <span id="linkKeys_block" class="help-block"></span>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="update_btn">更新</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>