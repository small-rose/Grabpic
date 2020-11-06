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
<title>员工列表</title>
<!--  web 路径问题
不以/开始的相对路径，找资源，以当前资源为基础
以/开始的路径，找资源，以服务器路径为标准（http://localhost:8080）
	http://localhost:8080/ssm
-->
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<jsp:include page="../../../../../main/top.jsp"></jsp:include>
<body>
	<div class="container">
		
		<!-- 标题 -->
		<div class="row">
		  <div class="col-md-12">
		  	<h2>SSM-AJAX DEMO</h2><h5><a href="/ssm">返回首页</a></h5>
		  </div>
		</div>
		<!-- 按钮 -->
		<div class="row">
		    <div class="col-md-4 col-md-offset-8">
		    	  <button type="button" class="btn btn-primary" id="add_model_btn">新增</button>
				  
				  <button type="button" class="btn btn-danger">删除</button>
		    </div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
		    <div class="col-md-12">
		    	<table id="data_table" class="table table-bordered table-hover">
		    		<thead>
		    		<tr>
		    			<th>#</th>
		    			<th>name</th>
		    			<th>sex</th>
		    			<th>email</th>
		    			<th>deptName</th>
		    			<th>opt</th>
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
	//1页面加载完成后发ajax
	$(function(){
		to_page(1);
		//绑定单击保存
		$("#save_btn").click(function(){
			//1.将模态框的内容提交后台保存到数据库
			//alert($("#addModal form").serialize());
			//1.校验表单数据
			if(!validate_add_form()){
				return false;
			}
			//2.发送ajax请求保存
	 		$.ajax({
				url:"${APP_PATH}/emp",
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
						//显示失败信息
						var error = result.extend.errorFields;
						if(undefined != error.empName){
							//alert(result.message);
							show_validate_msg("#inputEmpName","error",error.empName);
						}
						if(undefined != error.empEmail){
							show_validate_msg("#inputEmpEmail","error",error.empEmail);
						}
						
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
			getDepts();
			//弹出模态框
			$("#addModal").modal({
				backdrop:"static"
			});
		});
	});
	
	function to_page(pn){
		$.ajax({
			url:"${APP_PATH}/empsAjax",
			data:"pn="+pn,
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
			var empId = $("<td></td>").append(item.empId);
			var empName = $("<td></td>").append(item.empName);
			var empsex = $("<td></td>").append(item.sex=="M"?"男":"女");
			var empEmail = $("<td></td>").append(item.email);
			var empDepat = $("<td></td>").append(item.department.deptName);

			var editBtn = $("<button></button>)").addClass("btn btn-info btn-sm eidt_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
			editBtn.attr("edit-id",item.empId);
			var delBtn = $("<button></button>)").addClass("btn btn-danger btn-sm")
						.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
			delBtn.attr("del-id",item.empId);
			var opt = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(empId)
						.append(empName)
						.append(empsex)
						.append(empEmail)
						.append(empDepat)
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
		$("#page_info").append("当前第 "+pageInfo.pageNum +" 页，总 "+pageInfo.pages+"页，总 "+pageInfo.total+"条记录");
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
	
	
	function getDepts(){
		$.ajax({
			url:"${APP_PATH}/deptsAjax",
			type:"get",
			success:function(result){
				//console.log(result);
				//{"code":200,"message":"处理成功","extend":{"depts":[{"deptId":1,"deptName":"销售部"},{"deptId":2,"deptName":"开发部"}]}}
				$("#addModal select").empty();
				$("#update_dept_select").empty();
				$.each(result.extend.depts,function(){
					var optionEl = $("<option></option>").append(this.deptName).attr("value",this.deptId);
					$("#addModal select").append(optionEl);
					$("#update_dept_select").append(optionEl);
				});
			}
		});
	}
	
	function validate_add_form(){
		var empName = $("#inputEmpName").val();
		var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if(!regName.test(empName)){
			//alert("用户可以是2-5位中文或者6-16位英文和数字组合！");
			//$("#inputEmpName").parent().addClass("has-error");
			//$("#inputEmpName").next("span").text("用户可以是2-5位中文或者6-16位英文和数字组合！");
			//return false;
			return show_validate_msg("#inputEmpName","error","用户可以是2-5位中文或者6-16位英文和数字组合！");
		}else{
			//$("#inputEmpName").parent().addClass("has-success");
			//$("#inputEmpName").next("span").text("");
			show_validate_msg("#inputEmpName","success","格式正确");
		}
		var empEmail = $("#inputEmpEmail").val();
		var regEmail= /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if(!regEmail.test(empEmail)){
			//alert("邮箱格式不正确！");
			//$("#inputEmpEmail").parent().addClass("has-error");
			//$("#inputEmpEmail").next("span").text("邮箱格式不正确！");
			//return false;
			return show_validate_msg("#inputEmpEmail","error","邮箱格式不正确！");
		}else{
			//$("#inputEmpEmail").parent().addClass("has-success");
			//$("#inputEmpEmail").next("span").text("");
			show_validate_msg("#inputEmpEmail","success","格式正确");
		}
	}
	
	function show_validate_msg(ele,status,msg){
		//清除当前元素校验状态
		$(ele).parent().removeClass("has-error has-success");
		$(ele).next("span").text("");
		if("error"==status){
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
			return false;
		}else if("success"==status){
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		}
	}
	
	$(document).on("click",".eidt_btn",function(){
		//alert("edut");
		//获取部门下拉列表
		getDepts();
		getEmp($(this).attr("edit-id"));
		//弹出模态框
		$("#updateModal").modal({
			backdrop:"static"
		});
	});
	
	function getEmp(id){
		alert(id);
		$.ajax({
			url:"${APP_PATH}/getEmp",
			data:"id="+id,
			type:"GET",
			success:function(result){
				
				var emp = result.extend.employee;
				console.log(emp);
				$("#updateEmpName").val(emp.empName);
				$("#updateEmpEmail").val(emp.email);
				$("#updateModal input[name='sex']").val([emp.sex]);
				alert(emp.did);
				$("#updateModal select").val([emp.did]);
			}
		});
	}
</script>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">新增页面</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		  <div class="form-group">
		    <label for="inputEmpName" class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      <input type="text" name="empName"  class="form-control" id="inputEmpName" placeholder="Name">
		    </div>
		  </div>
		 
		  <div class="form-group">
		    <label for="inputEmpEmail" class="col-sm-2 control-label">empEmail</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="inputEmpEmail" placeholder="Email">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="inputEmpSex" class="col-sm-2 control-label">empSex</label>
		    <div class="col-sm-10">
		     	 <label class="radio-inline">
				  <input type="radio"  name="sex" checked="checked" id="inlineRadio1"  value="M"/> 男
				</label>
				<label class="radio-inline">
				  <input type="radio"  name="sex" id="inlineRadio2" value="F"/> 女
				</label>
		    </div>
		  </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    		<!-- 提交部门ID -->
			    <select class="form-control" name="did" id="dept_select">
				</select>
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
<!-- 修改模态框 -->
<div class="modal fade" id="updateModal" tabindex="-10" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="updateModalLabel">修改页面</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
		  <div class="form-group">
		    <label for="updateEmpName" class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      <input type="text" name="empName"  class="form-control" id="updateEmpName" placeholder="Name">
		    </div>
		  </div>
		 
		  <div class="form-group">
		    <label for="updateEmpEmail" class="col-sm-2 control-label">empEmail</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="updateEmpEmail" placeholder="Email">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="updateEmpSex" class="col-sm-2 control-label">empSex</label>
		    <div class="col-sm-10">
		     	 <label class="radio-inline">
				  <input type="radio"  name="sex" checked="checked" id="updateinlineRadio1"  value="M"/> 男
				</label>
				<label class="radio-inline">
				  <input type="radio"  name="sex" id="updateinlineRadio2" value="F"/> 女
				</label>
		    </div>
		  </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    		<!-- 提交部门ID -->
			    <select class="form-control" name="did" id="update_dept_select">
				</select>
		     </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="edit_save_btn">更新</button>
      </div>
    </div>
  </div>
</div>
</html>