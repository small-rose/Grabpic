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
		  	<h4>分页链接库</h4><a href="/ssm/categoryPage">返回分类页</a></h5>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-12">
				<form class="form-inline" id="queryForm">
					  <div class="form-group">
					    <label for="exampleInputName2">所属分类名称</label>
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
						 <button type="button" class="btn btn-primary" id="update_model_btn"><span class="glyphicon glyphicon-pencil"></span> 批量更新规则</button>
						 <button type="button" class="btn btn-warning" id="fetch_batch_btn"><span class="glyphicon glyphicon-globe"></span> 批量抓取</button>
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
		    			<th>所属分类名称</th>
		    			<th>页面地址</th>
		    			<th>页码</th>
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
				url:"${APP_PATH}/savePage",
				type:"POST",
				data:$("#addModal form").serializeArray(),
				cache : false,
				success:function(result){
					if(result.code==200){
						AlertModal("success",result.message);
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
			getWebsites("input");
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
			url:"${APP_PATH}/pagesAjax",
			data:"pn="+pn+"&pageNum="+pageNum+"&categoryid="+'${categoryid}'+'&'+query,
			type:"POST",
			success:function(result){
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
		console.log(data);
		$.each(data,function(index,item){
			//alert(" item"+item.empName);
			var checkBox = $("<td></td>").append($("<input type=\"checkbox\"/>").addClass("check_item"));
			var pageIdTD = $("<td hidden=\"hidden\"></td>").append(item.pageId);
			var categoryNameTD = $("<td></td>").append(item.webCategory.categoryName);
			var pageHerfTD = $("<td></td>").append(item.pageHerf);
			var pageName = item.pageName;
			var pageNameTD = $("<td></td>").append(pageName);
			var linkTotalTD = $("<td></td>").append(item.linkTotal);
			var addTimeTD = $("<td></td>").append(item.addTime);
			var pageMarkTD = $("<td></td>");
			if(item.pageMark=="1"){
				pageMarkTD.append($("<span></span>").addClass("glyphicon glyphicon-star")).append("");
			}else{
				pageMarkTD.append($("<span></span>").addClass("glyphicon glyphicon-star-empty")).append("");
			}
			//var isMark = $("<td></td>").append(item.isMark=="1"?"已收藏":"未收藏");
			pageMarkTD.click(function(){
				mark_handle(item.pageId,result);
			});
			
			var editBtn = $("<button></button>").addClass("btn btn-info btn-xs edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
			editBtn.attr("edit-id",item.pageId);
			
			var viewBtn = $("<button></button>").addClass("btn btn-info btn-xs view_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("查看");
			viewBtn.attr("view-id",item.pageId);
			
			var linkBtn = $("<button></button>").addClass("btn btn-info btn-xs link_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-share-alt")).append("打开");
			linkBtn.attr("link-id",item.pageId);
			
			var fetchBtn = $("<button></button>").addClass("btn btn-warning btn-xs fetch_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-arrow-down")).append("抓链接");
			fetchBtn.attr("fetch-id",item.pageId);
			
			var resultBtn = $("<button></button>").addClass("btn btn-success btn-xs result_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-arrow-down")).append("看结果");
			resultBtn.attr("result-id",item.pageId);
		
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs del_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			delBtn.attr("del-id",item.pageId);
			
			var opt = $("<td></td>").append(editBtn).append(" ").append(viewBtn).append(" ")
						.append(linkBtn).append(" ").append(fetchBtn).append(" ").append(resultBtn).append(" ").append(delBtn);
			var trObj =  $("<tr></tr>").append(checkBox).append(pageIdTD)
						.append(categoryNameTD).append(pageHerfTD).append(pageNameTD).append(linkTotalTD).append(addTimeTD).append(pageMarkTD)
						.append(opt);
			trObj.appendTo("#data_table tbody");
		});
	}
	
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
					var optionEl = $("<option></option>").append(this.webName+" - "+this.webUrl).attr("value",this.webId);
					$("#"+key+"_web_id").append(optionEl);
				});
			}
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
		$("#input_web_id").val("");
		$("#input_category_id").val("");
		$("#input_pageId").val("");
		$("#input_pageHerf").val("");
		$("#input_pageName").val("");
		$("#input_linkTag").val("a");
		$("#input_linkTagAttr").val("abs:href");
		$("#input_linkKeys").val("");
	}
	
	function init_add_model(result,type){
		var object = result.extend.object;
		$("#input_web_id").val([object.webCategory.webId]);
		
		getCategoryListByWebId(object.webCategory.webId,"input");
		$("#input_category_id").val([object.categoryId]);
		
		$("#input_pageId").val(object.pageId);
		$("#input_pageHerf").val(object.pageHerf);
		$("#input_pageNo").val(object.pageNo);
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
		$("#input_web_id opption[value="+object.webCategory.webId+"]").attr("selected",true);
		$("#input_category_id opption[value="+object.categoryId+"]").attr("selected",true);
	}
	//编辑
	$(document).on("click",".edit_btn",function (){
		getWebsites("input");
		var id = $(this).attr("edit-id");
		$.ajax({
			url:"${APP_PATH}/editPage",
			data:"id="+id,
			type:"POST",
			success:function(result){
				clear_model();
				init_add_model(result,"edit");
				$(".modal-title").text("编辑分页信息");
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
			url:"${APP_PATH}/editPage",
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
		var link = $(this).parents("tr").find("td:eq(3)").text();
		window.open(link, "前往");
	});
	
	//获取链接
	$(document).on("click",".fetch_btn",function(){
		$('#loadingModal').modal('show');
		var id = $(this).attr("fetch-id");
		$.ajax({
			url:"${APP_PATH}/fetchPages",
			data:"id="+id,
			type:"POST",
			success:function(result){
				if(result.code==200){
					AlertModal("success",result.message);
				}else{
					AlertModal("error",result.message);
				}
				$('#loadingModal').modal('hide');
				to_page(currPageNum);
			}
		});
	});
	
	$(document).on("click",".result_btn",function (){
		var pageid = $(this).attr("result-id");
		window.open("${APP_PATH}/linkPage?pageId="+pageid, "抓取链接结果");
	});
	
	//联动绑定
	$(document).on("change","#input_web_id",function(){
		var webId = $("#input_web_id option:selected").val();
		//alert(webId);
		getCategoryListByWebId(webId,"input");
	}).trigger("change");
	//联动调用
	function getCategoryListByWebId(webId,key){
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
					$("#"+key+"_category_id").append(optionEl);
				});
			}
		});
	}
	function mark_handle(id,result){
		var pageInfo = result.extend.pageInfo;
		$.ajax({
			url:"${APP_PATH}/markPage",
			data:"id="+id,
			type:"POST",
			success:function(result){
				if(result.code==200){
					alert(result.message);
				}else{
					alert(result.message);
				}
				to_page(pageInfo.pageNum);
			}
		});
	}

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
			url:"${APP_PATH}/delPages/"+ids,
			//data:"ids="+ids,
			type:"DELETE",
			success:function(result){
				if(result.code==200){
					alert(result.message);
				}else{
					alert(result.message);
				}
				to_page(currPageNum);
				$("#check_all").prop("checked",false);
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
		if(confirm("确认删除【"+picNames+" 】吗?")){
			del_handle(ids);
		}
		
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
	
	$(document).on("change","#update_web_id",function(){
		var webId = $("#update_web_id option:selected").val();
		//alert(webId);
		getWebList("update",webId);
	}).trigger("change");
	
	function getWebList(key,webId){
		$.ajax({
			url:"${APP_PATH}/categorylist",
			data:"webId="+webId,
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#"+key+"_categoryId").empty();
				$("#"+key+"_categoryId").append($("<option value=\"\">请选择</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.categoryName+"-"+this.categoryUrl).attr("value",this.categoryId);
					$("#"+key+"_categoryId").append(optionEl);
				});
			}
		});
	}
	$(document).on("change","#update_categoryId",function(){
		var categoryId = $("#update_categoryId option:selected").val();
		//alert(webId);
		getpageList("update",categoryId,null);
	}).trigger("change");
	
	function getpageList(key,categoryId,initVal){
		$.ajax({
			url:"${APP_PATH}/pagelist",
			data:"categoryId="+categoryId,
			type:"POST",
			success:function(result){
				//console.log(result);
				$("#"+key+"_pageId").empty();
				$("#"+key+"_pageId").append($("<option value=\"\">全部分页</option>)"));
				$.each(result.extend.list,function(){
					var optionEl = $("<option></option>").append(this.pageName+"-"+this.pageHerf).attr("value",this.pageId);
					$("#"+key+"_pageId").append(optionEl);
				});
			}
		});
	}
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
			url:"${APP_PATH}/updateLinkRules",
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
	
	//批量获取链接
	$(document).on("click","#fetch_batch_btn",function(){
		getWebsites("batch");
		$("#batchModal").modal({
			backdrop:"static"
		});
	});
	
	$(document).on("click","#batch_btn",function(){
		$('#loadingModal').modal('show');
		var webId = $("#batch_web_id option:selected").val();
		var categoryId = $("#batch_categoryId option:selected").val();
		var pageId = $("#batch_pageId option:selected").val();
		//alert(" webId:"+webId+",categoryId:"+categoryId+",pageId:"+pageId)
		if(webId==null ||webId==""){
			alert("请选择网站");
			return false;
		}
		if(categoryId==null ||categoryId==""){
			alert("请选择网站");
			return false;
		}
		if(pageId==null ||pageId==""){
			$("#batch_pageId_block").text("未选择分页则抓取全部分页的链接");
		}
		$.ajax({
			url:"${APP_PATH}/fetchBatchByPages",//?webId="+webId+"&categoryId"+"&pageId"+pageId
			data:{ "webId":webId,"categoryId":categoryId,"pageId": pageId},
			type:"POST",
			success:function(result){
				if(result.code==200){
					AlertModal("success",result.message);
				}else{
					AlertModal("error",result.message);
				}
				to_page(currPageNum);
				//$('#loadingModal').modal('hide');
			}
		});
	})
	$(document).on("change","#batch_web_id",function(){
		var webId = $("#batch_web_id option:selected").val();
		getWebList("batch",webId);
	}).trigger("change");
	
	$(document).on("change","#batch_categoryId",function(){
		var categoryId = $("#batch_categoryId option:selected").val();
		getpageList("batch",categoryId,null);
	}).trigger("change");
</script>
<!-- 新增模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">新增类型分页地址</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       	<div class="form-group">
       	  	<input type="" name="pageId" class="form-control" id="input_pageId">
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
		      <div class="col-sm-5">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="categoryId" id="input_category_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
		  <div class="form-group">
		    <label for="inputpageHerf" class="col-sm-2 control-label">分页地址</label>
		    <div class="col-sm-10">
		      <input type="text" name="pageHerf" class="form-control" id="input_pageHerf" placeholder="分页地址">
		      <span id="pageHref_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputpageNo" class="col-sm-2 control-label">页码</label>
		    <div class="col-sm-10">
		      <input type="text"name="pageNo" class="form-control" id="input_pageNo" placeholder="分页页码" >
		      <span id="pageNo_block" class="help-block"></span>
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="inputlinkTag" class="col-sm-2 control-label">图页链接标签</label>
		    <div class="col-sm-10">
		      <input type="text" name="linkTag" class="form-control" id="input_linkTag" data-toggle="tooltip" title="图页链接标签,一般是a标签" value="a">
		      <span id="linkTag_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputlinkTagAttr" class="col-sm-2 control-label">图页链接标签属性</label>
		    <div class="col-sm-10">
		      <input type="text"name="linkTagAttr" class="form-control" id="input_linkTagAttr" data-toggle="tooltip" title="抓取图页链接标签属性值,默认是a标签的属性href" value="abs:href">
		      <span id="linkTagAttr_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputlinkKeys" class="col-sm-2 control-label">图页链接关键字</label>
		    <div class="col-sm-10">
		      <input type="text"name="linkKeys" class="form-control" id="input_linkKeys" placeholder="分页链接中必有的关键字" >
		      <span id="linkKeys_block" class="help-block"></span>
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
<!-- 批量更新 -->
<div class="modal fade" id="updateModal" tabindex="-10" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="updateModalLabel">批量更新规则</h4>
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
       	   <label for="update_pageId" class="col-sm-2 control-label">网站分页</label>
		      <div class="col-sm-7">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="pageId" id="update_pageId">
			    	<option value="">全部</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
		   <div class="form-group">
		    <label for="update_linkTag" class="col-sm-2 control-label">图页链接标签</label>
		    <div class="col-sm-10">
		      <input type="text" name="linkTag" class="form-control" id="update_linkTag" data-toggle="tooltip" title="图页链接标签,一般是a标签" value="a">
		      <span id="linkTag_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="update_linkTagAttr" class="col-sm-2 control-label">图页链接标签属性</label>
		    <div class="col-sm-10">
		      <input type="text"name="linkTagAttr" class="form-control" id="update_linkTagAttr" data-toggle="tooltip" title="抓取图页链接标签属性值,默认是a标签的属性href" value="abs:href">
		      <span id="linkTagAttr_block" class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="update_linkKeys" class="col-sm-2 control-label">图页链接关键字</label>
		    <div class="col-sm-10">
		      <input type="text"name="linkKeys" class="form-control" id="update_linkKeys" placeholder="分页链接中必有的关键字" >
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

<!-- 批量抓取 -->
<div class="modal fade" id="batchModal" tabindex="-10" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="batchModalLabel">批量抓取链接</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       	<div class="form-group">
       	   <label for="batch_web_id" class="col-sm-2 control-label">网站地址</label>
		      <div class="col-sm-5">
		    	 <select class="form-control" name="webId" id="batch_web_id">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
       	  <div class="form-group">
       	   <label for="batch_categoryId" class="col-sm-2 control-label">网站分类名称</label>
		      <div class="col-sm-6">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="categoryId" id="batch_categoryId">
			    	<option value="">请选择</option>
				</select>
		     	<span id="web_id_block" class="help-block"></span>
		    </div>
		   </div>
		   <div class="form-group">
       	   <label for="batch_pageId" class="col-sm-2 control-label">网站分页</label>
		      <div class="col-sm-7">
		    	<!-- 提交部门ID -->
			    <select class="form-control" name="pageId" id="batch_pageId">
			    	<option value="">全部</option>
				</select>
		     	<span id="batch_pageId_block" class="help-block"></span>
		    </div>
		   </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="batch_btn">批量抓取</button>
      </div>
    </div>
  </div>
</div>
</html>