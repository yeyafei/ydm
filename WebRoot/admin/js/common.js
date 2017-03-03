$(function(){
	$("#searchFormButton").on('click',function(){
		$("#pageNum").val("1");
		submitFrom();
	});
	
	//$("#addButton").on('click',function(){
	//	var url = $("#searchForm").attr("action").replace("list.do","edit.do");
	//	window.open(url);
	//});
});

function searchPage(page){
	$("#pageNum").val(page);
	submitFrom();
}

function submitFrom(){
	var index = layer.load(0, {shade: false});
	$("#list_page").hide();
	$("#searchForm").ajaxSubmit({
		success: function(result){
			var result_ = JSON.parse(result);
			$("#list_table").html(result_.table);
			$("#currentPage").val(result_.pageNum);
			$("#pageAllCount").html(result_.pageAllCount);
			if(result_.operate != "")
				$("#list_operate").html(result_.operate);
			else
				$("#list_operate").hide();
			
			$("table tr").each(function(){
				var tr = $(this).attr("xh");
				if(tr != null && tr != '' && tr == '1'){
					$(this).click();
				}
			});
			if(result_.pageAllCount > 1){
				$("#list_page").show();
				laypage({
					cont: 'list_table_page',
					pages: result_.pageAllCount,
					curr: result_.pageNum,
					skin: 'yahei',
					groups: 7,
					jump: function(obj, frist){
						if(!frist){
							if(hideBtn instanceof Function) {//判断方法是否存在
								hideBtn();
							}
							searchPage(obj.curr);
						}
					}
				});
			}
			layer.close(index);
		},
		error: function(result){
			console.error("查询表单报错");
		}
	});
}

function changeTrColor(tr,xh){
	var selectTr = $(".selectTrColor");
	if(selectTr.length > 0){
		if(selectTr[0].getAttribute("xh") % 2 == 1)
			selectTr[0].setAttribute("style","background-color: #E6EEEE;");
		else
			selectTr[0].setAttribute("style","background-color: #FFFFFF;");
		selectTr[0].setAttribute("class","");
	}
	tr.setAttribute("class","selectTrColor");
	tr.setAttribute("style","background-color: #FFCC9A;");
	
	// 每个调用这个changeTrColor方法的页面都必须要存在这个loadsub方法
	if(loadsub) //判断方法是否存在
		loadsub(xh);
}

function skipPage(){
	searchPage($("#currentPage").val());
}

//添加 url
function addLOU(url) {
	layer.open( {
		type : 2,
		title : false,
		shadeClose : false,
		shade : 0.8,
		area : [ '600px', '500px' ],
		content : url
	});
}

//添加 url wight,hight
function addLOUS(url,wight,hight) {
	layer.open( {
		type : 2,
		title : false,
		shadeClose : false,
		shade : 0.8,
		area : [ wight, hight  ],
		content : url
	});
}

//添加 title,url
function addLOTU(title,url) {
	layer.open( {
		type : 2,
		title : title,
		shadeClose : false,
		shade : 0.8,
		area : [ '600px', '500px' ],
		content : url
	});
}



//添加  title,url,wight,hight
function addLOTUS(title,url,wight,hight) {
	layer.open( {
		type : 2,
		title : title,
		shadeClose : false,
		shade : 0.8,
		area : [wight, hight ],
		content : url
	});
}



function getCuName(num){
	$("#whichCu").val(num);
	parent.layer.open({
	  type: 2,
	  title: '选择客户信息',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['80%', '65%'],
	  content: '/customer/culist.do'
	}); 
}

function getAddCuName(num){
	$("#whichCu").val(num);
	layer.open({
	  type: 2,
	  title: '选择客户信息',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['80%', '65%'],
	  content: '/customer/culist.do'
	}); 
}