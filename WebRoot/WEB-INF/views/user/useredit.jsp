<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>用户管理</title>
		<jsp:include page="../../../default.jsp" />
		<script type="text/javascript">
		//关闭窗口	
		function closeWin(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		if("${ifoper}" == 'true'){
			parent.location.reload();
			}
			parent.layer.close(index);
		}

		layui.use('form', function(){
			//监听提交
			var form = layui.form();
			form.on('submit(userForm)', function(data){
			    $("#userForm").submit();
			    return false;
			  });
			});	
		$(function(){
			if("${user}"!=""){
				$("#tbn").text("修改");
				$("#level").val("${user.level}");
			}		
			});
		
		</script>

	</head>
	<body>
		<div>
			<fieldset>
				<div>
					<form id ="userForm" class="layui-form" action="/user/save.do" method="POST">
						<input type="hidden" id="id" name="ids"
							value="${user.id eq 0 ? '' : user.id}" />
						<table class="layui-table">
							<tr>
								<th align="right" >
									<label>账号名:</label>
								</th>
								<td >
									<c:if test="${user eq null}">
									<input type="text" name="name" value="${user.name }"  lay-verify="required"  class="layui-input" />
									</c:if>
									<c:if test="${user ne null}">
										<input type="hidden" name="name" value="${user.name}" />
										<input type="text" disabled="disabled" value="${user.name }"
											class="layui-input layui-disabled" />
									</c:if>
								</td>
							</tr>
							<tr>
								<th align="right">
									<label>密码:</label>
								</th>
								<td >
									<input type="text" name="passWord"
										value="${user.passWord }"  lay-verify="required"  class="layui-input" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<label>级别:</label>
								</th>
								<td>
								<select id="level" name="level"  class="layui-input layui-unselect">
									<option value="A">默认</option>
									<option value="S">管理员</option>
								</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									<label>是否激活:</label>
								</th>
								<td>
									<input type="radio" name="active" value="true" title="激活" checked="checked"/>
									<input type="radio" name="active" value="false" title="冻结"  />
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
								 <button lay-submit class="layui-btn" id="tbn"  lay-filter="userForm">新增</button>
									<input type="button" id="off" onclick="closeWin()" class="layui-btn"
										value="关 闭" />
									<span class="green">${ msg }</span>
									<span class="red">${ errormsg }</span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</fieldset>
		</div>
	</body>
</html>

									