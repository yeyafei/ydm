layui.config({
  base:'admin/js/'
}).use(['jquery','element','layer','navtab'],function(){
    window.jQuery = window.$ = layui.jquery;
	  window.layer = layui.layer;
    var element = layui.element(),
         navtab = layui.navtab({
               elem: '.yyf-tab-box'
         });

  
   //iframe自适应
  $(window).on('resize', function() {
    var $content = $('#yyf-tab .layui-tab-content');
    $content.height($(this).height() - 163);
    $content.find('iframe').each(function() {
      $(this).height($content.height());
    });
    tab_W = $('#yyf-tab').width();
    // yyf-footer：p-admin宽度设定
    var yyfFoot = $('#yyf-footer').width();
    $('#yyf-footer p.p-admin').width(yyfFoot - 300);
  }).resize();
  
  // 左侧菜单导航-->tab
$(function(){
    $('#yyf-nav-side').click(function(){
        if($(this).attr('lay-filter')!== undefined){
            $(this).children('ul').find('li').each(function(){
                var $this = $(this);
                if($this.find('dl').length > 0){
                   var $dd = $this.find('dd').each(function(){
                       $(this).click(function(){
                           var $a = $(this).children('a');
                           var href = $a.data('url');
                           var title = $a.children('span').text();
                           var data = {
                                 href: href,
                                 title: title
                           }
                           navtab.tabAdd(data);
                       });
                   });
                }else{
                    $this.click(function(){
                           var $a = $(this).children('a');
                           var href = $a.data('url');
                           var title = $a.children('span').text();
                           var data = {
                                 href: href,
                                 title: title
                           }
                           navtab.tabAdd(data);
                    });
                }
            });
        }
    })
})


});