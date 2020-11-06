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
<title>抓取结果列表</title>
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
		  	<h4>图页链接库</h4><a href="/ssm/webpagePage">返回分页页</a></h5>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-12">
				<form class="form-inline" id="queryForm">
					  <div class="form-group">
					    <label for="exampleInputName2">所属分页名称</label>
					    <input type="text" name="picOldname" class="form-control" id="querypicOldname" placeholder="">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail2">链接名称</label>
					    <input type="text" name="linkName"  class="form-control" id="querylinkName" placeholder="">
					  </div>
					  <div class="form-group">
					    <label class="control-label">是否收藏</label>
						    <select class="form-control" name="isMark" id="isMark">
						    	<option value="">请选择</option>
						    	<option value="1">已收藏</option>
						    	<option value="0">未收藏</option>
							</select>
					  </div>
					   <button type="button" class="btn btn-primary btn-ms" id="query_handle"><span class="glyphicon glyphicon-search"></span>查询</button>
						 <button type="button" class="btn btn-primary btn-ms" id="query_delete_all"><span class="glyphicon glyphicon-remove"></span>清空条件</button>
					</form>
					
			</div>
			 <div class="col-md-12">
						<button type="button" class="btn btn-success" id="pic_refresh"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
						 <button type="button" class="btn btn-danger" id="pic_delete_all"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
						 <button type="button" class="btn btn-primary" id="add_model_btn"><span class="glyphicon glyphicon-plus"></span> 新增</button>
						<!--  <button type="button" class="btn btn-warning" id="fetch_batch_btn"><span class="glyphicon glyphicon-globe"></span> 批量抓取</button> -->
					 </div>
		    <!-- <div class="col-md-4 ">col-md-offset-10
		    	  <button type="button" class="btn btn-primary" id="add_model_btn">新增</button>
				  
				  <button type="button" class="btn btn-danger" id="pic_delete_all">全部删除</button>
				  <button type="button" class="btn btn-success" id="pic_refresh">刷新</button>
		    </div> -->
		</div>
		<!-- 表格数据 -->
		<div class="row">
		    <div class="col-md-12">
		    	<table id="data_table" class="table table-bordered table-hover" >
		    		<thead>
		    		<tr>
		    			<th><input type="checkbox" id="check_all"/></th>
		    			<th hidden="hidden">#</th>
		    			<th>分类名称</th>
		    			<th>页码</th>
		    			<th>链接地址</th>
		    			<th hidden="hidden">链接名称</th>
		    			<th>序号</th>
		    			<th>数量</th>
		    			<th>抓取时间</th>
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
			//2.发送ajax请求保存
	 		$.ajax({
				url:"${APP_PATH}/saveLink",
				type:"POST",
				data:$("#addModal form").serializeArray(),
				cache : false,
				success:function(result){
					if(result.code==200){
						alert(result.message);
						//员工保存成功1.要关闭模态框，2来到最后一页
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
			getWebsites("input",null);
			$("#addModalLabel").text("新增分页信息");
			//弹出模态框
			$("#addModal").modal({
				backdrop:"static"
			});
		});
	});
	
	function to_page(pn){
		var query = $("#queryForm").serializeArray();
		var pageNum = $(".page_size_change").children('option:selected').val();
		if(undefined==pageNum){
			pageNum = 5;
		}
		$.ajax({
			url:"${APP_PATH}/linksAjax",
			data:"pn="+pn+"&pageNum="+pageNum+"&pageId="+'${pageId}'+'&'+query,
			type:"POST",
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
			var checkBox = $("<td></td>").append($("<input type=\"checkbox\"/>").addClass("check_item"));
			var linkIdTD = $("<td hidden=\"hidden\"></td>").append(item.linkId);
			var categoryName = item.webCategory!=null ? item.webCategory.categoryName:"";
			var categoryNameTD = $("<td></td>").append(categoryName);
			var pageNameTD = $("<td></td>").append(item.webPageBase.pageNo).attr("data-toggle","tooltip").attr("title",item.webPageBase.pageName);
			var linkhref = item.linkHerf.substring(0,12)+"****"+item.linkHerf.substring(item.linkHerf.length-20,item.linkHerf.length);
			var linkhrefA = $("<a></a>").append(linkhref).attr("href",item.linkHerf);
			var linkHerfTD = $("<td></td>").append(item.linkHerf).attr("data-toggle","tooltip").attr("title",item.linkName);
			var linkName = item.linkName;
			linkName = linkName.replace(/([^\u0000-\u00FF])+/g, function ($) { return "****";/* escape($);  */});
			//alert(oldName);
			var linkNameTD = $("<td hidden=\"hidden\"></td>").append(linkName).attr("data-toggle","tooltip").attr("title",item.linkName);
			var linkNoTD = $("<td></td>").append(item.linkNo);
			var picTotalTD = $("<td></td>").append(item.picTotal);
			var addTimeTD = $("<td></td>").append(item.addTime);
			var linkMarkTD = $("<td></td>");
			if(item.linkMark=="1"){
				linkMarkTD.append($("<span></span>").addClass("glyphicon glyphicon-star")).append("");
			}else{
				linkMarkTD.append($("<span></span>").addClass("glyphicon glyphicon-star-empty")).append("");
			}
			linkMarkTD.click(function(){
				mark_handle(item.linkId,result);
			});
			
			var editBtn = $("<button></button>").addClass("btn btn-info btn-xs edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
			editBtn.attr("edit-id",item.linkId);

			var viewBtn = $("<button></button>").addClass("btn btn-info btn-xs view_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("查看");
			viewBtn.attr("view-id",item.linkId);
			
			var linkBtn = $("<button></button>").addClass("btn btn-info btn-xs link_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-share-alt")).append("打开");
			linkBtn.attr("link-id",item.pageId);
			
			var fetchBtn = $("<button></button>").addClass("btn btn-warning btn-xs fetch_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-arrow-down")).append("抓图片");
			fetchBtn.attr("fetch-id",item.linkId);
			
			var resultBtn = $("<button></button>").addClass("btn btn-success btn-xs result_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("预览");
			resultBtn.attr("result-id",item.linkId);
			
			var topicBtn = $("<button></button>").addClass("btn btn-success btn-xs to_pic_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("列表");
			topicBtn.attr("to_pic-id",item.linkId);
			
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs del_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			delBtn.attr("del-id",item.linkId);
			
			
			var opt = $("<td></td>").append(editBtn).append(" ").append(viewBtn).append(" ")
						.append(linkBtn).append(" ").append(fetchBtn).append(" ").append(resultBtn).append(" ").append(topicBtn).append(" ").append(delBtn);
			var trObj =  $("<tr></tr>").append(checkBox).append(linkIdTD).append(categoryNameTD).append(pageNameTD)
						.append(linkHerfTD).append(linkNameTD).append(linkNoTD).append(picTotalTD).append(addTimeTD).append(linkMarkTD)
						.append(opt);
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
	
	function getWebsites(key,initVal){
		$.ajax({
			url:"${APP_PATH}/websiteSelect",
			data:"",
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#"+key+"_web_id").empty();
				$("#"+key+"_web_id").append($("<option value=\"\">请选择</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.webName+" - "+this.webUrl).attr("value",this.webId);
					if(this.webId==initVal){
						optionEl.attr("selected","selected");
					}
					$("#"+key+"_web_id").append(optionEl);
				});
			}
		});
	}
	
	function clear_model(){
		$("#inputPicName").val("");
	}
	
	function init_add_model(result, type){
		var object = result.extend.object;
		//$("#input_web_id").val([object.webCategory.webId]);
		if(object.webCategory!=null){
			getWebsites("input",object.webCategory.webId);
		}
		if(object.webCategory!=null){
			getCategoryListByWebId("input",object.webCategory.webId,object.categoryId);
		}
		//$("#input_category_id").val([object.categoryId]);
		
		if(object.categoryId!=null){
			getPageListByCategoryId("input",object.categoryId,object.pageId);
		}
		
		//$("#input_pageId").val([object.pageId]);
		$("#input_linkId").val(object.linkId);
		$("#input_linkHerf").val(object.linkHerf);
		$("#input_linkName").val(object.linkName);
		$("#input_linkTag").val(object.linkTag);
		$("#input_linkTagAttr").val(object.linkTagAttr);
		$("#input_linkKeys").val(object.linkKeys);
		
		if(type=="edit"){
			$("#addModalLabel").text("编辑分页信息");
			$("#input_categoryId").val(object.categoryId);
		}
		$("#addModal input").removeAttr("readonly");
		$("#addModal select").removeAttr("disabled");
		$("#addModal input:checkbox").removeAttr("disabled");
		$("#save_btn").show();
		if(type=="view"){
			$("#addModalLabel").text("查看分页信息");
			$("#addModal input").attr("readonly","readonly");
			$("#addModal select").attr("disabled","disabled");
			$("#addModal input:checkbox").attr("disabled","disabled");
			$("#save_btn").hide();
		}
	}
	
	//编辑
	$(document).on("click",".edit_btn",function (){
		getWebsites();
		var id = $(this).attr("edit-id");
		$.ajax({
			url:"${APP_PATH}/editLink",
			data:"id="+id,
			type:"POST",
			success:function(result){
				console.log(result);
				clear_model();
				init_add_model(result,"edit");
				$(".modal-title").text("编辑分页信息");
				$("#addModal").modal({
					backdrop:"static"
				});
				init_add_model(result,"edit");
			}
		});
	});
	
	//查看
	$(document).on("click",".view_btn",function (){
		getWebsites();
		var id = $(this).attr("view-id");
		$.ajax({
			url:"${APP_PATH}/editLink",
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
		var link = $(this).parents("tr").find("td:eq(4)").text();
		window.open(link, "前往");
	});
	//联动绑定
	$(document).on("change","#input_web_id",function(){
		var webId = $("#input_web_id option:selected").val();
		getCategoryListByWebId(webId,null);
	}).trigger("change");
	
	//联动调用
	function getCategoryListByWebId(key,webId,initVal){
		$.ajax({
			url:"${APP_PATH}/categorylist",
			data:"webId="+webId,
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#"+key+"_category_id").empty();
				$("#"+key+"_category_id").append($("<option value=\"\">请选择</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.categoryName+" - "+this.categoryUrl).attr("value",this.categoryId);
					if(this.categoryId==initVal){
						optionEl.attr("selected","selected");
					}
					$("#"+key+"_category_id").append(optionEl);
				});
				
			}
		});
	}
	//联动绑定2
	$(document).on("change","#input_category_id",function(){
		var categoryId = $("#input_category_id option:selected").val();
		//alert(categoryId+"categoryId");
		getPageListByCategoryId(categoryId,null);
	}).trigger("change");
	
	//联动调用
	function getPageListByCategoryId(key,categoryId,initVal){
		$.ajax({
			url:"${APP_PATH}/pagelist",
			data:"categoryId="+categoryId,
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#"+key+"_page_id").empty();
				$("#"+key+"_page_id").append($("<option value=\"\">请选择</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.pageName+" - "+this.pageHerf).attr("value",this.pageId);
					if(this.pageId==initVal){
						optionEl.attr("selected","selected");
					}
					$("#"+key+"_page_id").append(optionEl);
				});
			}
		});
	}
	
	function mark_handle(id,result){
		var pageInfo = result.extend.pageInfo;
		$.ajax({
			url:"${APP_PATH}/markLink",
			data:"id="+id,
			type:"POST",
			success:function(result){
				if(result.code==200){
					AlertModal("success",result.message);
				}else{
					AlertModal("error",result.message);
				}
				to_page(pageInfo.pageNum);
			}
		});
	}
	
	$(document).on("click", ".fetch_btn",function(){
		$('#loadingModal').modal('show');
		var id = $(this).attr("fetch-id");
		$.ajax({
			url:"${APP_PATH}/fetchLink",
			data:"id="+id,
			type:"POST",
			success:function(result){
				if(result.code==200){
					AlertModal("success",result.message);
				}else{
					AlertModal("error",result.message);
				}
				to_page(currPageNum);
				$('#loadingModal').modal('hide');
			}
		});
	});
	
	$(document).on("click", ".result_btn",function(){
		var linkid = $(this).attr("result-id");
		window.open("${APP_PATH}/viewByLink?linkId="+linkid, "结果预览");
	});
	

	$(document).on("click", ".to_pic_btn",function(){
		var linkid = $(this).attr("to_pic-id");
		window.open("${APP_PATH}/webpicPage?linkId="+linkid, "结果预览");
	});
	
	$(document).on("click", ".del_btn",function(){
		//弹出确认框
		var picName = $(this).parents("tr").find("td:eq(3)").text();
		var  picId = $(this).attr("del-id");
		if(confirm("确认删除【"+picName+" 】吗?")){
			del_handle(picId);
		}
	});
	
	function del_handle(ids){
		$.ajax({
			url:"${APP_PATH}/delLink/"+ids,
			//data:"ids="+ids,
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
		$("#querypicOldname").val("");
		$("#querypicName").val("");
		$("#querypicSuffix").val("");
		$("#queryisMark").val("");
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
					alert(result.message);
				}else{
					alert(result.message);
				}
				to_page(currPageNum);
			}
		});
	});
	
	$("#fetch_batch_btn").click(function(){
		getWebsites("batch",null);
		$("#batchModal").modal({
			backdrop:"static"
		});
	});
	
	//联动绑定
	$(document).on("change","#batch_web_id",function(){
		var webId = $("#batch_web_id option:selected").val();
		getCategoryListByWebId("batch",webId,null);
	}).trigger("change");
	
	//联动绑定2
	$(document).on("change","#batch_category_id",function(){
		var categoryId = $("#batch_category_id option:selected").val();
		//alert(categoryId+"categoryId");
		getPageListByCategoryId("batch",categoryId,null);
	}).trigger("change");
	
	$(document).on("click","#batch_btn",function(){
		$('#loadingModal').modal('show');
		var webId = $("#batch_web_id option:selected").val();
		var categoryId = $("#batch_category_id option:selected").val();
		var pageId = $("#batch_page_id option:selected").val();
		$("#batchModal").modal('hide');
		$.ajax({
			url:"${APP_PATH}/fetchBatchByLink",//?webId="+webId+"&categoryId"+"&pageId"+pageId
			data:{ "webId":webId,"categoryId":categoryId,"pageId": pageId},
			type:"POST",
			success:function(result){
				if(result.code==200){
					AlertModal("success",result.message);
				}else{
					AlertModal("error",result.message);
				}
				to_page(currPageNum);
				$('#loadingModal').modal('hide');
			}
		});
	})
</script>
<!-- 新增模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">新增站内分页地址</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       	<div class="form-group">
       	  	<input type="hidden" name="linkId" class="form-control" id="input_linkId">
       	   <label for="input_web_id" class="col-sm-2 control-label">网站地址</label>
		      <div class="col-sm-5">
		    	 <select class="form-control" name="webId" id="input_web_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
       	  <div class="form-group">
       	   <label for="input_category_id" class="col-sm-2 control-label">网站分类名称</label>
		      <div class="col-sm-6">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="categoryId" id="input_category_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
		   <div class="form-group">
		    <label for="input_page_id" class="col-sm-2 control-label">分类子页名称</label>
		    <div class="col-sm-7">
		      <!-- 提交部门ID -->
			    <select class="form-control" name="pageId" id="input_page_id">
			    	<option value="">请选择</option>
				</select>
		      <span id="pageId_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="input_linkHerf" class="col-sm-2 control-label">图页地址</label>
		    <div class="col-sm-10">
		      <input type="text" name="linkHerf" class="form-control" id="input_linkHerf" placeholder="图页地址">
		      <span id="pageHref_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="input_linkName" class="col-sm-2 control-label">图页名称</label>
		    <div class="col-sm-10">
		      <input type="text"name="linkName" class="form-control" id="input_linkName" placeholder="图页名称" >
		      <span id="pageNo_block" class="help-block"></span>
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="input_ImgTag" class="col-sm-2 control-label">图片标签定位</label>
		    <div class="col-sm-10">
		      <input type="text" name="imgTag" class="form-control" id="input_ImgTag" data-toggle="tooltip" title="图片标签,一般是img标签" value="body img">
		      <span id="imgTag_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputimgTagAttr" class="col-sm-2 control-label">图片链接标签属性</label>
		    <div class="col-sm-10">
		      <input type="text"name="imgTagAttr" class="form-control" id="input_imgTagAttr" data-toggle="tooltip" title="抓取图片标签签属性值,默认是img标签的属性src" value="abs:src">
		      <span id="imgTagAttr_block" class="help-block"></span>
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

<!-- batchModal 批量抓取模态框 -->
<div class="modal fade" id="batchModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="batchModalLabel">批量抓取</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       	<div class="form-group">
       	  	<input type="hidden" name="linkId" class="form-control" id="input_linkId">
       	   <label for="input_web_id" class="col-sm-2 control-label">网站地址</label>
		      <div class="col-sm-5">
		    	 <select class="form-control" name="webId" id="batch_web_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
       	  <div class="form-group">
       	   <label for="input_category_id" class="col-sm-2 control-label">网站分类名称</label>
		      <div class="col-sm-6">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="categoryId" id="batch_category_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
		   <div class="form-group">
		    <label for="input_page_id" class="col-sm-2 control-label">分类子页名称</label>
		    <div class="col-sm-7">
		      <!-- 提交部门ID -->
			    <select class="form-control" name="pageId" id="batch_page_id">
			    	<option value="">请选择</option>
				</select>
		      <span id="pageId_block" class="help-block"></span>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="batch_btn">执行批量抓取</button>
      </div>
    </div>
  </div>
</div>  
  
</html>