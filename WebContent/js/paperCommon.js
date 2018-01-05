var winHeight = 0, winWidth = 0, openDivHeight = 335;
$(function(){
    $(window).load(function(){
        //placeholder兼容性
        $('input, textarea').placeholder();

        // 获取置顶对象
        var obj = $(".scrollTop"),scrollTop = null;
        obj.hide();
        // 置顶对象点击事件
        obj.click(function() {
            var timer = setInterval(function() {
                window.scrollBy(0, -50);
                if (scrollTop == 0) 
                    clearInterval(timer);
            }, 2);
        });
        // 窗口滚动检测
        window.onscroll = function() {
            scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
            if(scrollTop >= 100) obj.show();
            else obj.hide();
        };

        //公用表格 鼠标经过变色
        $(".table_yuejuan-ishover tr").each(function(){
            var obj = $(this);
            obj.mouseover(function(){
                obj.addClass("active");
                if(obj.parents("table").hasClass("table_guize")){
                    obj.find("td").find("a").show();
                }
            });
            obj.mouseout(function(){
                obj.removeClass("active");
                if(obj.parents("table").hasClass("table_guize")){
                    obj.find("td").find("a").hide();
                }
            });
        });

        //公用表格 最后一行td去掉下边框
        $(".table_yuejuan").each(function(){
            var obj = $(this);
            obj.children().children("tr").each(function(){
                $(this).children("td").last().addClass("last");
            });
        });
    });
});


//公用表格 鼠标经过变色
function tableMouse(){
  $(".table_yuejuan-ishover tr").each(function(){
      var obj = $(this);
      obj.mouseover(function(){
          obj.addClass("active");
          if(obj.parents("table").hasClass("table_guize")){
              obj.find("td").find("a").show();
          }
      });
      obj.mouseout(function(){
          obj.removeClass("active");
          if(obj.parents("table").hasClass("table_guize")){
              obj.find("td").find("a").hide();
          }
      });
  });
}

//自适应公用
function winAll_pub(obj){
    winWidth = $(window).width();
    winHeight = $(window).height();
    //弹窗-表格高度 scrollHeight
    var scrollHeight;
    if(obj.hasClass("openDiv-editques") || obj.hasClass("openDiv-normal")){
        scrollHeight = winHeight-180;
    }
    if(obj.hasClass("openDiv-examine")){
        scrollHeight = obj.find(".scrollDiv").children().height();
        if(scrollHeight > winHeight-210)
            scrollHeight = winHeight-210;
    }
    if(scrollHeight < 60) scrollHeight = 60;
    obj.find(".scrollDiv").css({"max-height":scrollHeight});
    if(obj.hasClass("openDiv-editques")){
        obj.find(".editques-list .scrollDiv").css({"max-height":scrollHeight-40});
        obj.find(".editques-con .scrollDiv").css({"max-height":scrollHeight});
    }
    if(obj.hasClass("openDiv-selectques")){
        obj.find(".editques-list .scrollDiv").css({"max-height":scrollHeight-40});
    }
    if(obj.hasClass("openDiv-setScore") && obj.find(".scrollDiv").length > 0){
        obj.find(".scrollDiv").css({"max-height":winHeight-330});
    }
    openDivHeight = obj.find('.openCon').height() + 70;
    
    //弹窗高度自适应
    if(openDivHeight > winHeight - 20){
        openDivHeight = winHeight - 20;
    }
    obj.height(openDivHeight);
    obj.css({"margin-top":-obj.height()/2});
    if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE6.0"){
        obj.css({"margin-top":"50px"});
    }

}

//打开弹窗
function showDiv(obj){
	//停止播放音频
	if ($(".jp-jplayer") != null && $(".jp-jplayer").html() != null ) {
		$(".jp-jplayer").jPlayer( "stop" );
	}
    obj.show();
    $(".openbg").show();
    winAll_pub(obj)
}
//关闭弹窗
function closeDiv(obj){
	//停止播放音频
	if ($(".jp-jplayer") != null && $(".jp-jplayer").html() != null ) {
		$(".jp-jplayer").jPlayer( "stop" );
	}
    obj.hide();
    $(".openbg").hide();
}

//公用-获取地址栏某参数
function UrlSearch(str) {
    var name,value;
    var num=str.indexOf("?") 
    str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]

    var arr=str.split("&"); //各个参数放到数组里
    for(var i=0;i < arr.length;i++){ 
        num=arr[i].indexOf("="); 
        if(num>0){ 
            name=arr[i].substring(0,num);
            value=arr[i].substr(num+1);
            this[name]=value;
        } 
    } 
}