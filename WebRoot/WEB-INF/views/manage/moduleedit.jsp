<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>模块管理</title>
		<jsp:include page="../../../default.jsp" />
		<script type="text/javascript">
		function tx(){
			if("${module}"!=""){
  				$("#tbn").text("修改");
				}
			}
		layui.use('form', function(){
			var form = layui.form();
			form.on('select(level)', function(data){
				getParent(data,form);
				
			});

			form.on('select(parent)', function(data){
				setParent(data);
				});
			
			  //监听提交
			 form.on('submit(formDemo)', function(data){
			    $("#moduleForm").submit();
			    return false;
			  });

	
			});

	$(function(){
		tx()
		var moduleid= $("#id").val();
		if(moduleid != ''){
			var active = "${module.active}";
			$('input[name="active"]').each(function(){
			  	if($(this).val() == active){
				    $(this).attr("checked","checked");
			  	}
			});

			var level = "${module.level}";
			$('#level').find("option").each(function(){
				if($(this).val() == level){
					$(this).attr("selected","selected");
				}					
			});

			if(level == 'A'){
				$("#parent").parent().parent().hide();	
				$("#parent").attr("ignore","ignore");
				$("#parentid").val("0");
			}else{
				$("#parent").find("option").remove();
				$("#parent").attr("ignore","");
				$.post("/module/getparent.do", {level:level}, function(result){
					var result_ = JSON.parse(result);
					if(result_[0].key == '-1'){
						console.error(result_[0].value);					
					}else{
						$.each(result_,function(i){
							
							$("#parent").append("<option value='" + result_[i].key + "'>" + result_[i].value + "</option>");
						});
						var parentid = "${module.parentid}";
						$('#parent').find("option").each(function(){
							if($(this).val() == parentid){
								$(this).attr("selected","selected");
							}					
						});
						$("#parentid").val(parentid);
					}
				});
			}
		}
	});

	//关闭窗口	
	function closeWin(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		//if("${ifoper}" == 'true')
		//	parent.iframeName.window.refresh(); //刷新父页面
		parent.layer.close(index);

	}

	//根据选择的级别模块获取该等级的所有上级模块
	function getParent(option ,form){
		var level = option.value;
		var parentTr = $("#parent").parent().parent();
		if(level == ''){
			return ;
		}else if(level == 'A'){
			parentTr.hide();
			$("#parent").attr("ignore","ignore");
			$("#parentid").val("0");
		}else{		
			parentTr.show();
			$("#parent").find("option").remove();
			/*
			 * 选择二级模块,获取所有的该用户所拥有权限的一级模块
			 * 选择三级模块,获取所有的该用户所拥有权限的二级模块
			*/
			
			$.post("/module/getparent.do", {level:level}, function(result){
				var result_ = JSON.parse(result);
				if(result_[0].key == '-1'){
					console.error(result_[0].value);					
				}else{
					$.each(result_,function(i){
						if(i==0){
							$("#parentid").val(result_[0].key);
							}
						$("#parent").append("<option value='" + result_[i].key + "'>" + result_[i].value + "</option>");
						
					});
					form.render('select'); //刷新渲染！
				}
			});
		}
	}

	//根据选择的上级模块给 input设置值 以便表单提交
	function setParent(option){
		$("#parentid").val(option.value);
		console.info("选择的上级模块编码为:" + option.value);
	}
</script>

	</head>
	<body>
		<div>
			<fieldset class="openWin">
				<!--  <legend>
					模块${module eq null ? '新增' : '修改'}
				</legend>-->
				<div class="warp">
					<form id ="moduleForm" class="layui-form" action="/module/save.do" method="POST">
						<input type="hidden" id="id" name="ids"
							value="${module.id eq 0 ? '' : module.id}" />
						<input type="hidden" id="parentid" name="parentid" value="" />
						<table class="layui-table">
							<tr>
								<th align="right" >
									<label>名称:</label>
								</th>
								<td >
									<input type="text" name="description"
										value="${module.description }"  lay-verify="required"  placeholder="角色代码[1-6个中文]"
										class="layui-input" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<label>代码:</label>
								</th>
								<td>
									<c:if test="${module eq null}">
										<input type="text" name="code"
											style="text-transform: uppercase;" value="${module.code }"
											class="layui-input"  lay-verify="required" placeholder="模块代码[2-20位数字和字母]" />
									</c:if>
									<c:if test="${module ne null}">
										<input type="hidden" name="code" value="${module.code }" />
										<input type="text" disabled="disabled" value="${module.code }"
											class="layui-input layui-disabled" />
									</c:if>
								</td>
							</tr>
							<tr>
								<th align="right">
									<label>级别:</label>
								</th>
								<td>
								<select id="level" name="level" lay-filter="level" class="layui-input layui-unselect">
									<option value="">请选择</option>
									<option value="A">一级模块</option>
									<option value="B">二级模块</option>
									<option value="C">三级模块</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									<label>上级模块:</label>
								</th>
								<td>
									<select id="parent"  lay-filter="parent"  class="layui-input layui-unselect">
									<option value="" >请选择</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									<label>URL:</label>
								</th>
								<td>
									<input type="text" name="url" value="${module.url }"
										class="layui-input" />
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
								<th></th>
								<td>
								 <button lay-submit class="layui-btn" id="tbn"  lay-filter="formDemo">新增</button>
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

									