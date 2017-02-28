<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<link rel="stylesheet" href="../../../common/ztree/css/demo.css"
			type="text/css">
		<link rel="stylesheet" href="../../../common/ztree/css/manage.css"
			type="text/css">
		<link rel="stylesheet"
			href="../../../common/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css">
		<script type="text/javascript"
			src="../../../common/ztree/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src="../../../common/ztree/js/jquery.ztree.core.js"></script>
			<link rel="stylesheet" type="text/css" href="../../../common/layui/css/layui.css" media="all">
		<script type="text/javascript">
		
	var setting = {
		view : {
			showIcon : true
		},
		data : {
			key : {
				name : "name",
				id : "id",
				pId : "pId",
				active : "active",
				level : "level"
			},
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : zTreeOnClick
		}
	};

	function zTreeOnClick(event, treeId, treeNode) {
		if (treeNode.active == 'true') {
			$("#active").val("false");
			$("#MANAGE_MODULE_ACTIVE").attr('value','冻结')
		} else {
			$("#active").val("true");
			$("#MANAGE_MODULE_ACTIVE").attr('value','激活')
		}
		$("#moduleid").val(treeNode.id);
	};

	var  zNodes= ${zNodes};
	var treeObj;

	$(document).ready(function() {
		treeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
		treeObj.expandAll(true);

		$('span[class="node_name"]').each(function() {
			var id = $(this).attr("id");
			id = id.replace("_span", "");
			var node = treeObj.getNodeByTId(id);
			if ("true" == node.active && "2" == node.level)
				$("#" + id + "_span").attr('style', 'color:green;');

			if ("false" == node.active)
				$("#" + id + "_span").attr('style', 'color:red;');

		});

		var flag = false;
		var parentid = '';
		var count = 1;
		$('li[class="level2"]').each(function(i) {
			var parentid_ = $(this).parent().attr('id');
			if (parentid_ != parentid) {
				parentid = parentid_;
				count = 1;
			} else {
				count++;
			}
			var id = $(this).attr('id');
			var liclass = $("#" + id + "_switch").attr('class');

			if (count % 16 == 0) //一行最多显示16个
					flag = true;
				else
					flag = false;

				if ("button level2 switch center_docu" == liclass && !flag)
					$(this).attr('style', 'float:left;')
			});
		$('li[class="level0"]').each(function() {
			var id = $(this).attr('id');
			var node = treeObj.getNodeByTId(id)
			if ("false" == node.active)
			$("#" + id + "_span").attr('style', 'color:red;');
		});

	});

	//激活 / 冻结模块
	function active() {
		var num = $("#moduleid").val();
		if (num == '') {
			parent.layer.alert('请选择模块!', {
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}

		var active = $("#active").val();
		var active_cn = active == 'false' ? "冻结" : "激活";
		parent.layer.confirm('请确定是否要' + active_cn + '该模块?', {
			btn : [ active_cn, '取消' ]
		}, function() {
			//冻结模块 [判断是否冻结该模块的所有子模块] 激活模块[判断该模块的上级模块是否冻结中,如果冻结中,提示请先激活上级模块]
				$.post("/module/chstate.do", {
					num : num,
					active : active
				}, function(result) {
					var result_ = JSON.parse(result);
					if (result_.code == '0') { //code为0表示可以直接 冻结或激活 
							freezeOrActivateModule(num, active, 'false');
						} else if (result_.code == '-1') { //code为-1表示 冻结[模块有子模块没有全部冻结] 激活[激活模块所有冻结的子模块] 
							console.info(result_.msg);
							parent.layer.confirm('是否' + active_cn + '所有子模块?', {
								btn : [ active_cn, '取消' ]
							}, function() {
								freezeOrActivateModule(num, active, 'true');
							}, function() {
								freezeOrActivateModule(num, active, 'false');
							});
						} else if (result_.code == '-2') { //激活[上级模块冻结中]
							parent.layer.msg(result_.msg);
						}
					});
			});
	}

	//直接冻结或激活模块 
	function freezeOrActivateModule(num, active, flag) {
		//flag为true 表示冻结/激活所有子模块 为false表示只冻结/激活本身
		var active_cn = active == 'false' ? '冻结' : '激活';
		$.post("/module/active.do", {
			num : num,
			active : active,
			flag : flag
		}, function(result) {
			result = JSON.parse(result)
			if (result.code == '0') {
				refresh();
			}
			parent.layer.msg(result.msg);
		});
	}

	//添加模块
	function add() {
	parent.layer.open( {
			type : 2,
			title : false,
			shadeClose : true,
			shade : 0.8,
			area : [ '600px', '500px' ],
			content : '/module/edit.do'
		});
	}

	//修改
	function upd() {
		var moduleid = $("#moduleid").val();
		if (moduleid == '') {
			parent.layer.alert('请选择模块!', {
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		parent.layer.open( {
			type : 2,
			title : '模块操作',
			shadeClose : true,
			shade : 0.8,
			area : [ '600px', '500px' ],
			content : '/module/edit.do?id=' + moduleid
		});
	}

	//删除模块
	function del() {
		var num = $("#moduleid").val();
		if (num == '') {
			parent.layer.alert('请选择模块!', {
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}

		parent.layer.confirm('请确定是否要删除该模块?', {
			btn : [ '删除', '取消' ]
		}, function() {
			// 判断要删除的模块有没有下级模块
				$.post("/module/ifchildmodule.do", {
					num : num
				}, function(result) {
					if ("true" == result) {
						parent.layer.confirm('该模块的下级模块是否一起删除?', {
							btn : [ '删除', '取消' ]
						}, function() {
							$.post("/module/delete.do", {
								num : num
							}, function(result) {
								result = JSON.parse(result);
								if (result.code == '0') {
									parent.layer.msg(result.msg);
									refresh(); //刷新缓存
								} else {
									parent.layer.msg(result.msg);
								}
							});
						}, function() {
							return;
						});
					} else {
						$.post("/module/delete.do", {
							num : num
						}, function(result) {
							result = JSON.parse(result);
							if (result.code == '0') {
								parent.layer.msg(result.msg);
								refresh(); //刷新缓存
							} else {
								parent.layer.msg(result.msg);
							}
						});
					}
				});
			});
	}

	//刷新父页面
	function refresh() {
		location.reload();
	}
</script>
		<title>菜单管理</title>
	</head>
	<body>
		<div id="bodyDiv">
			<c:if test="${menulist ne null}">
				<div class="manage_oper">
					<span><b>操作:</b>
					</span>
					<c:forEach var="menu" items="${menulist}">
					<input type="button"  class="layui-btn layui-btn-mini" id="${menu.code }" onclick="javascript:${menu.url }"  value="${menu.description}"/>
					</c:forEach>
				</div>
			</c:if>
			<ul id="tree" class="ztree" style="width: 99%; height: 500px;"></ul>
			<div>
				<input type="hidden" id="moduleid" value="" />
				<input type="hidden" id="active" value="" />
			</div>
		</div>
	</body>
</html>
