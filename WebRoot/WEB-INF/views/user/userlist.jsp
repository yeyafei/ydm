<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8"/>
		<title>用户管理</title>
		<jsp:include page="../../../default.jsp" />
	
		<script type="text/javascript">
		$(function(){
			searchPage(1);	
		});
		//动态控制三级菜单显示
		function loadsub(xh){
			$("#list_operate").show();
			$("#ids").val($("#IDS"+xh).val());
		}
		
		function hideBtn(){ 
			$("#list_operate").hide();
			}
		//添加
		function add(){
			addLOUS("edit.do","600px","336px");
			}
		//修改
		function upd(){
			addLOUS("edit.do?id="+$("#ids").val(),"600px","336px");
			}
		//删除
		function del() {
			var id =$("#ids").val()
			if (id == '') {
				parent.layer.alert('请选择用户!', {
					icon : 0,
				});
				return;
			}
			layer.confirm('请确定是否要删除该用户?', {
				btn : [ '删除', '取消' ]
			}, function() {
				$.post("del.do", {id : id}, function(result) {
					layer.msg(result);
					searchPage($("#pageNum").val());
					});
				
				});
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
							<input type="hidden" id="ids" value="" />
							<input type="hidden" id="parentid" name="parentid" value="${parentid }" />
	 						<table class="layui-table" >
								<tr>
									<td><label>用户名&nbsp;</label></td>
									<td><input type="text" name="name" value="" class="textInput" /></td>
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

