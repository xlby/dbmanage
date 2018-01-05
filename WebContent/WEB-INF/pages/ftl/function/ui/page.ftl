[#macro page listAction pagination]
<form action="${listAction}" method="post">
<div>
<div class="float_right padding5 paging">
			<div class="float_left padding_top4">
				[#if pagination.firstPage ]
					<span class="paging_disabled"><a href="javascript:;">上一页</a></span>
				[#else]	
					<span><a href="javascript:_gotoPage(${pagination.prePage});">上一页</a></span>
				[/#if]
				
				[#if pagination.totalPage<=10]
					[#list 1.. pagination.totalPage as value]
						[#if value==pagination.pageNo]
							<span class="paging_current"><a href="javascript:_gotoPage(${value});">${value}</a></span> 
						[#else]
							<span><a href="javascript:_gotoPage(${value});">${value}</a></span>
						[/#if]
					[/#list]
				[#else]
					[#if pagination.pageNo<=5]
						[#list 1..7 as value]
							[#if value==pagination.pageNo]
								<span class="paging_current"><a href="javascript:_gotoPage(${value});">${value}</a></span> 
							[#else]
							<span><a href="javascript:_gotoPage(${value});">${value}</a></span>
							[/#if]
						[/#list]
						<span>...</span>
						[#list pagination.totalPage-1..pagination.totalPage as value]
								<span><a href="javascript:_gotoPage(${value});">${value}</a></span> 
						[/#list]
					[#else]
						<span><a href="javascript:_gotoPage(1);">1</a></span>
						<span>...</span>
						[#if pagination.pageNo<(pagination.totalPage-4)]
							[#list (pagination.pageNo-2)..pagination.pageNo+2 as value]
								[#if value==pagination.pageNo]
									<span class="paging_current"><a href="javascript:_gotoPage(${value});">${value}</a></span>
								[#else]
									<span><a href="javascript:_gotoPage(${value});">${value}</a></span>
								[/#if]
							[/#list]
								<span>...</span>
							[#list pagination.totalPage-1..pagination.totalPage as value]
								<span><a href="javascript:_gotoPage(${value});">${value}</a></span> 
							[/#list]
						[#else]
							[#list (pagination.totalPage-7)..pagination.totalPage as value]
								[#if value==pagination.pageNo]
									<span class="paging_current"><a href="javascript:_gotoPage(${value});">${value}</a></span>
								[#else]
									<span><a href="javascript:_gotoPage(${value});">${value}</a></span>
								[/#if]
							[/#list]
						[/#if]
					[/#if]
				[/#if]
				[#if pagination.lastPage ]
					<span class="paging_disabled"><a href="javascript:;">下一页</a></span>
				[#else]	
					 <span> <a href="javascript:_gotoPage(${pagination.nextPage});">下一页</a></span>
				[/#if]
			&nbsp;每页
			</div>
			<div class="float_left padding_top3">
				<select autoWidth="true" onchange="_gotoPage();" id="page.pageSize" name="page.pageSize" >
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
					<option>150</option>
				</select>
			</div>
			<div class="float_left padding_top6">条记录</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
[#list RequestParameters?keys as pkey]
	[#if pkey?starts_with('query')]<input type="hidden" name="${pkey}" value="${(.data_model[pkey])!?string}"/>[/#if>[#t/]
[/#list]	
<input type="hidden" id="page.pageNo"  name="page.pageNo" value="${pagination.pageNo!}"/>
<script type="text/javascript">
$("#page\\.pageSize").val(${pagination.pageSize});
function _gotoPage(pageNo) {
	try{
		var formPageNo = $("#page\\.pageNo")
		var tableForm = $(formPageNo).closest("form");
		if(pageNo){
			$("#page\\.pageNo").val(pageNo);
		}else{
			$("#page\\.pageNo").val(1);
		}
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
		alert('_gotoPage(pageNo)方法出错');
	}
}
</script>
</div>
</form>
[/#macro]