<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8"/>
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="../../../admin/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="../../../admin/laypage/skin/laypage.css"/>
		<link rel="stylesheet" type="text/css" href="../../../admin/layer/skin/layer.css"/>
		<script type="text/javascript" src="../../../admin/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="../../../admin/js/jquery.form.js"></script>
		<script type="text/javascript" src="../../../admin/laypage/laypage.js"></script>
		<script type="text/javascript" src="../../../admin/layer/layer.js"></script>
		<script type="text/javascript" src="../../../admin/js/common.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../../../common/layui/css/layui.css" media="all">
	
		<script type="text/javascript">
		$(function(){searchPage(1);	});
		function loadsub(xh){
			$("#list_operate").show();
			$("#name").val($("#NAME"+xh).val());
		}
		function hideBtn(){
			$("#list_operate").hide();
			}
		function add(){
			alert("come on")
			}
		</script>
	</head>
	<body>
		<div id="bodyDiv">
			<div class="tableDiv">
				<fieldset>
					<div class="searchDiv">
						<form id="searchForm" action="/user/list.do" method="post">
							<input type="hidden" id="pageSize" name="pageSize" value="10"/>
							<input type="hidden" id="pageNum" name="pageNum" value="1"/>
							<input type="hidden" id="xh" value="" />
							<input type="hidden" id="name" value="" />
	 						<table class="layui-table" >
								<tr>
									<td><label>用户名&nbsp;</label></td>
									<td><input type="text" name="name" value="" class="textInput"/></td>
					                <td><label>用户级别&nbsp;</label></td>
						            <td><input type="text" name="status" value="" class="textInput"/></td>
						            <td><input type="button" id="searchFormButton" class="layui-btn layui-btn-small"  value="查 询"/>
						            </td>
				                </tr>
			                </table>
		                </form>
					</div>
				</fieldset>
				<fieldset>
					<div id="list_operate">
						<label>操作：</label>
						<input type="button"  class="layui-btn layui-btn-mini" onclick="javascript:add()"  value="新增"/>
						<input type="button"  class="layui-btn layui-btn-mini" onclick="javascript:add()"  value="修改"/>
						<input type="button"  class="layui-btn layui-btn-mini" onclick="javascript:add()"  value="删除"/>
					</div>
					<div id="list_table">
					</div>
					<div id="list_page">
						<div id="list_table_page_skip" style="float:right;">
							<a href="javascript:skipPage()">转到</a>
							<input type="text" class="textInput" id="currentPage" value=""/>
							/
							<span id="pageAllCount"></span>
						</div>
						<div id="list_table_page" style="float:right;">
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</body>
</html>

