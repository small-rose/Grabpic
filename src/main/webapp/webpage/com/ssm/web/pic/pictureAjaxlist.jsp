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
	http://localhost:8080/Grabpic
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
		  	<h2>Grabpic--独立页面抓取结果</h2><h5><a href="/Grabpic">返回首页</a></h5><h5><a href="/Grabpic/picUrlPage">返回抓取页</a></h5>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-12">
				<form class="form-inline" id="queryForm">
					  <div class="form-group">
					    <label for="exampleInputName2">原名称</label>
					    <input type="text" name="picOldname" class="form-control" id="querypicOldname" placeholder="">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail2">新名称</label>
					    <input type="text" name="picName"  class="form-control" id="querypicName" placeholder="">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail2">文件格式</label>
					    <input type="text" name="picSuffix"  class="form-control" id="querypicSuffix" placeholder=".gif|.jpg|.jpeg|.png|.GIF|.JPG|.PNG)">
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
						 <button type="button" class="btn btn-success" id="pic_refresh"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
						 <button type="button" class="btn btn-danger" id="pic_delete_all"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
					  	 <button type="button" class="btn btn-warning" id="pic_view_all"><span class="glyphicon glyphicon-list-alt"></span>批次网页预览</button>
					  	 <button type="button" class="btn btn-warning" id="pic_view_all_roll"><span class="glyphicon glyphicon-list-alt"></span>批次滚动预览</button>
					  	 
					</form>
					 
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
		    			<th hidden="hidden">原地址</th>
		    			<th>原名称</th>
		    			<th>图片新名称</th>
		    			<th>格式</th>
		    			<th hidden="hidden">picType</th> 
		    			<th>大小</th>
		    			<th>路径</th>
		    			<th hidden="hidden">isDel</th> 
		    			<th hidden="hidden">源地址</th>
		    			<th>抓取时间</th>
		    			<th>收藏</th>
		    			<th hidden="hidden">img</th>
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
				url:"${APP_PATH}/savePic",
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
			//getDepts();
			//弹出模态框
			$("#addModal").modal({
				backdrop:"static"
			});
		});
	});
	
	function to_page(pn){
		var query = $("#queryForm").serializeArray();
		console.log(query);
		var pageNum = $(".page_size_change").children('option:selected').val();
		if(undefined==pageNum){
			pageNum = 5;
		}
		$.ajax({
			url:"${APP_PATH}/picsAjax",
			data:"pn="+pn+"&pageNum="+pageNum+"&urlId="+'${urlId}'+'&'+query,
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
			var picId = $("<td hidden=\"hidden\"></td>").append(item.picId);
			var urlName = $("<td hidden=\"hidden\"></td>").append(item.picUrl.addrName);
			var picName = $("<td></td>").append(item.picName);
			var oldName = item.picOldname;
			oldName = oldName.replace(/([^\u0000-\u00FF])+/g, function ($) { return "****";/* escape($);  */});
			//alert(oldName);
			var picOldname = $("<td></td>").append(oldName);
			var picSuffix = $("<td></td>").append(item.picSuffix);
			var picType = $("<td hidden=\"hidden\"></td>").append(item.picType);
			var picSize = $("<td></td>").append(item.picSize);
			var picPath = item.picPath;
			picPath = picPath.replace(/([^\u0000-\u00FF])+/g, function ($) { return "****";/* escape($);  */});
			var picPath = $("<td></td>").append(picPath);
			var picAddr = $("<td hidden=\"hidden\"></td>").append(item.picAddr);
			var isDel = $("<td hidden=\"hidden\"></td>").append(item.isDel);
			var addTime = $("<td></td>").append(item.addTime);
			var isMark = $("<td></td>");
			if(item.isMark=="1"){
				isMark.append($("<span></span>").addClass("glyphicon glyphicon-star")).append("");
			}else{
				isMark.append($("<span></span>").addClass("glyphicon glyphicon-star-empty")).append("");
			}
			//var isMark = $("<td></td>").append(item.isMark=="1"?"已收藏":"未收藏");
			isMark.click(function(){
				mark_handle(item.picId,result);
			});
			
			var editBtn = $("<button></button>").addClass("btn btn-info btn-xs")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("重命名");
		
			editBtn.click(function(){
				edit_page_handle(item.picId);
			});
			var viewBtn = $("<button></button>").addClass("btn btn-info btn-xs")
					.append($("<span></span>").addClass("glyphicon glyphicon-eye-open")).append("查看");
			viewBtn.click(function(){
				view_page_handle(item.picId);
			});
			
			var viewpicBtn = $("<button></button>").addClass("btn btn-info btn-xs edit_btn")
						.append($("<span></span>").addClass("glyphico glyphicon-picture")).append("预览");
			viewpicBtn.click(function(){
					viewpic_page_handle(item.picId);
				});
			var redownpicBtn = $("<button></button>").addClass("btn btn-warning btn-xs redown_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-arrow-down")).append("重新获取");
			redownpicBtn.attr("redown-id",item.picId);
			
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs del_btn")
						.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			delBtn.attr("del-id",item.picId);
			
			var imgtd = $("<td hidden=\"hidden\"></td>");
			var img = $("<img></img>").addClass("img-thumbnail").attr("src","tmpImg/"+item.picOldname).css("width","30px").css("height","30px");
			imgtd.append(img);
			var opt = $("<td></td>").append(editBtn).append(" ").append(viewBtn).append(" ")
						.append(viewpicBtn).append(" ").append(redownpicBtn).append(" ").append(delBtn);
			var trObj =  $("<tr></tr>").append(checkBox).append(picId)
						.append(urlName).append(picOldname).append(picName)
						.append(picSuffix).append(picType).append(picSize)
						.append(picPath).append(picAddr)
						.append(isDel).append(addTime).append(isMark)
						.append(imgtd).append(opt);
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
				if(i>10){
					select.append($("<option>"+((i-10)*100)+"</option>"));
				}else{
					select.append($("<option>"+(i*10)+"</option>"));
				}
				
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
		$("#inputPicName").val("");
	}
	
	function init_add_model(result){
		var picture = result.extend.picture;
		$("#picId").val(picture.picId);
		$("#inputPicName").val(picture.picName);
		$("#inputPicOldName").val(picture.picOldname);
		//$("#inputimg").attr("src","tmpImg/"+picture.picOldname);
	}
	
	function init_view_model(result){
		var picture = result.extend.picture;
		//console.log(picture);
		$("#viewpicId").text(picture.picId);
		$("#viewpicName").text(picture.picName);
		$("#viewpicOldName").text(picture.picOldname);
		$("#viewpicAddr").text(picture.picAddr);
		$("#viewpicSize").text(picture.picSize);
		$("#viewpicPath").text(picture.picPath);
		//$("#viewimg").attr("src","tmpImg/"+picture.picOldname);
	}
	//重命名
	function edit_page_handle(id){
		$.ajax({
			url:"${APP_PATH}/editPic",
			data:"id="+id,
			type:"POST",
			success:function(result){
				//console.log(result);
				//{"code":200,"message":"处理成功","extend":{"depts":[{"deptId":1,"deptName":"销售部"},{"deptId":2,"deptName":"开发部"}]}}
				clear_model();
				init_add_model(result);
				$("#addModal").modal({
					backdrop:"static"
				});
			}
		});
	}
	//查看
	function view_page_handle(id){
		$.ajax({
			url:"${APP_PATH}/editPic",
			data:"id="+id,
			type:"POST",
			success:function(result){
				//console.log(result);
				//清空数据
				clear_model();
				init_view_model(result);
				$("#viewModal").modal({
					backdrop:"static"
				});
			}
		});
	}
	
	//预览
	function viewpic_page_handle_dailog(id){
		$.ajax({
			url:"${APP_PATH}/editPic",
			data:"id="+id,
			type:"POST",
			success:function(result){
				$("#viewpicimg").attr("src","tmpImg/"+result.extend.picture.picOldname);
				$("#viewpicModal").modal({
					backdrop:"static"
				});
			}
		});
	}
	
	
	function viewpic_page_handle(id){
		window.open("${APP_PATH}/viewPic?id="+id, "图片预览");
	}
	
	function mark_handle(id,result){
		var pageInfo = result.extend.pageInfo;
		$.ajax({
			url:"${APP_PATH}/markPic",
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
			url:"${APP_PATH}/delPic/"+ids,
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
			alert("请选择要删除的记录");
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
	
	$("#pic_view_all").click(function(){
		var selvar = $(".check_item:checked").length;
		if(selvar<1){
			AlertTip("请至少选择一条记录");
			return false;
		}
		var ids = "";
		$.each($(".check_item:checked"),function(){
			ids += $(this).parents("tr").find("td:eq(1)").text()+",";
		});
		window.open("${APP_PATH}/viewPicList?ids="+ids, "多图预览");
	});
	
	$("#pic_view_all_roll").click(function(){
		var selvar = $(".check_item:checked").length;
		if(selvar<1){
			AlertTip("请至少选择一条记录");
			return false;
		}
		var ids = "";
		$.each($(".check_item:checked"),function(){
			ids += $(this).parents("tr").find("td:eq(1)").text()+",";
		});
		window.open("${APP_PATH}/viewRollList?ids="+ids, "相册预览");
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
	
	function alertTip(msg){
		//alert(msg);
		$("#msgContent").text(msg);
		$("#msgModal").modal({
			backdrop:"static"
		});
	}
</script>
<!-- 新增模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">重命名页面</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		  <div class="form-group">
		  	<input type="hidden" name="picId"  class="form-control" id="picId" placeholder="重命名">
		    <label for="inputEmpName" class="col-sm-2 control-label">新名称</label>
		    <div class="col-sm-10">
		      <input type="text" name="picName"  class="form-control" id="inputPicName" placeholder="重命名">
		    </div>
		  </div>
		 
		  <div class="form-group">
		    <label for="inputEmpEmail" class="col-sm-2 control-label">原名称</label>
		    <div class="col-sm-10">
		      <input type="text" readonly="readonly" name="picOldNameTmp" class="form-control" id="inputPicOldName" >
		    </div>
		  </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">图片</label>
		    <div class="col-sm-10">
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
<!-- viewModal模态框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">查看页面</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal" style="word-break:break-all">
       		<div class="form-group">
		    <label for="inputEmpName" class="col-sm-2 control-label">系统编号</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" readonly="readonly" name="viepicId"  class="form-control" id="viewpicId" > -->
		      <p class="form-control-static" id="viewpicId"></p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="viewpicName" class="col-sm-2 control-label">新名称</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" readonly="readonly" name="viewpicName"  class="form-control" id="viewpicName"> -->
		      <p class="form-control-static" id="viewpicName"></p>
		    </div>
		  </div>
		 
		  <div class="form-group">
		    <label for="viewpicOldName" class="col-sm-2 control-label">原名称</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" readonly="readonly" name="viewpicOldName" class="form-control" id="viewpicOldName" > -->
		      <p class="form-control-static" id="viewpicOldName"></p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="viewpicAddr" class="col-sm-2 control-label">源地址</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" readonly="readonly" name="viewpicAddr" class="form-control" id="viewpicAddr" > -->
		      <p class="form-control-static" id="viewpicAddr"></p>
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="viewpicSize" class="col-sm-2 control-label">文件大小</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" readonly="readonly" name="viewpicSize" class="form-control" id="viewpicSize" > -->
		      <p class="form-control-static" id="viewpicSize"></p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="viewpicPath" class="col-sm-2 control-label">系统路径</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" readonly="readonly" name="viewpicPath" class="form-control" id="viewpicPath" > -->
		      <p class="form-control-static" id="viewpicPath"></p>
		    </div>
		  </div>
		 
		 <div class="form-group">
		    <label class="col-sm-2 control-label">图片</label>
		    <div class="col-sm-10">
		      <img id="viewimg" src="">
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 查看图片信息的模态框 -->
<div class="modal fade" id="viewpicModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">查看页面</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		 <div class="form-group">
		    <label class="col-sm-2 control-label">图片</label>
		    <div class="col-sm-10">
		      <img id="viewpicimg" src="">
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>

<!-- msg模态框 -->
<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="msgModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">信息提示</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		 <div class="form-group">
		    <label class="col-sm-12 control-label" id="msgContent">图片</label>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
</html>