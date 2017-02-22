'use strict';
layui.use(['jquery','layer','element'],function(){
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
  var element = layui.element();
  
// yyf-side-menu向左折叠
$('.yyf-side-menu').click(function() {
  var sideWidth = $('#yyf-side').width();
  if(sideWidth === 200) {
      $('#yyf-body').animate({
        left: '0'
      }); //admin-footer
      $('#yyf-footer').animate({
        left: '0'
      });
      $('#yyf-side').animate({
        width: '0'
      });
  } else {
      $('#yyf-body').animate({
        left: '200px'
      });
      $('#yyf-footer').animate({
        left: '200px'
      });
      $('#yyf-side').animate({
        width: '200px'
      });
  }
});

 
$(function(){
   // 刷新iframe操作
    $("#refresh_iframe").click(function(){
       $(".layui-tab-content .layui-tab-item").each(function(){
          if($(this).hasClass('layui-show')){
             $(this).children('iframe')[0].contentWindow.location.reload(true);
          }
       });
    });

    
});


});
