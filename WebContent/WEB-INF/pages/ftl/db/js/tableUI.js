/* 
* tableUI 0.1 
* Copyright (c) 2015 LeiChen 
* Date: 2015-06-02 
* 使用tableUI可以方便地将表格提示使用体验。先提供的功能有奇偶行颜色交替，鼠标移上高亮显示 
*/ 
(function($){
	$.fn.tableUI = function(options){ 
		//各种属性、参数
		var defaults = {
			lastThClass : "lastTh",
			firstTdClass : "firstTd",	
			oddRowClass : "oddRow",
			activeRowClass : "activeRow"
		}
		var options = $.extend(defaults, options);//如果你在调用的时候写了新的参数，就用你新的参数，如果没有写，就用默认的参数
		this.each(function(){
		//插件实现代码
			var thisTable = $(this);
			// if(thisTable.find("th").length >0 ){//如果有标题行
			// 	thisTable.children("tbody").children("tr:even").addClass(options.oddRowClass);//添加偶数行颜色(因为有th,实际是除了第一行的奇数行)
			// 	thisTable.children("tbody").children("th:last").addClass(options.lastThClass);//去掉最后一个th的右边框
			// }
			// else{
			// 	thisTable.children("tbody").children("tr:odd").addClass(options.oddRowClass);//添加偶数行颜色
			// 	thisTable.children("tbody").children("tr:first").addClass(options.firstTdClass);//添加最后一个td的上边框
			// }
			//添加活动行颜色
			thisTable.children("tbody").children("tr").bind("mouseover",function(){
				$(this).addClass(options.activeRowClass);
			});
			thisTable.children("tbody").children("tr").bind("mouseout",function(){
				$(this).removeClass(options.activeRowClass);
			});
		});
	};
	$(".table_tips").tableUI();
})(jQuery);
