<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8"/>
		<title>通告</title>
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

		layui.use('layedit', function(){
			  var layedit = layui.layedit
			  ,$ = layui.jquery;

			  //自定义工具栏
			layedit.build('AREA', {			  
			    tool: [	'strong' //加粗
			        	,'italic' //斜体
			        	,'underline' //下划线
			        	,'del' //删除线
						,'|' //分割线			   				  
			        	,'left' //左对齐
			        	,'center' //居中对齐
			        	,'right' //右对齐
			        	,'link' //超链接
			        	,'unlink' //清除链接
			        	,'face' //表情
			        	 ]
			    ,height: 100
			  })
			  
			  //构建一个默认的编辑器
			//  var index = layedit.build('AREA');
			  
			  //编辑器外部操作
			  var active = {
			    content: function(){
			      alert(layedit.getContent(index)); //获取编辑器内容
			    }
			    ,text: function(){
			      alert(layedit.getText(index)); //获取编辑器纯文本内容
			    }
			    ,selection: function(){
			      alert(layedit.getSelection(index));
			    }
			  };
			  
			  $('.site-demo-layedit').on('click', function(){
			    var type = $(this).data('type');
			    active[type] ? active[type].call(this) : '';
			  });
			  

			});
		</script>
	</head>
	<body>
		<div>
			<div class="tableDiv">
					<div>
						<form id="searchForm" action="/notice/list.do" method="post">
							<input type="hidden" id="pageSize" name="pageSize" value="5"/>
							<input type="hidden" id="pageNum" name="pageNum" value="1"/>
							<input type="hidden" id="xh" value="" />
							<input type="hidden" id="ids" value="" />
							<input type="hidden" id="parentid" name="parentid" value="${parentid }" />
		                </form>
					</div>
				<fieldset>
					<div id="list_operate">
					
					</div>
					<div id="list_table">
					</div>
					<div id="list_page">
						<div id="list_table_page" style="float:right;">
						</div>
					</div>
				</fieldset>	
			</div>
			<fieldset>
			<div style="margin-bottom: 20px; width: ">
			<form class="layui-form" action="">
			  <div class="layui-form-item">
      		<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
  			</div>
  			<textarea class="layui-textarea" id="AREA" style="display: none">
    		</textarea>
    		<br>
    		  <div class="layui-form-item">
    <button class="layui-btn" lay-submit="" lay-filter="">提交公告</button>
  </div>
    			</form>
			</div> 
		
			</fieldset>
		</div>
	</body>
</html>

