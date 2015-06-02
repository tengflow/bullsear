{"entityQueryName":"${entityReferName}Query"}
/${webappPath}/WEB-INF/pages/${moduleName}/${entityReferName}.list.vm
\#parse("./common/include-base.vm")
</head>
<body>
<div id="content">
	<div id="content-header" class="height37"></div>
	<div id="breadcrumb"> <a href="#" title="返回首页" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#">${entityClassName}</a> <a href="#" class="current">${entityClassName}管理</a></div>
	<div class="container-fluid">
    	<div class="row-fluid" id="searchDiv">
    		<div class="span12">
            	<div class="widget-box">
              		<div class="widget-content nopadding">
                		<form id="${entityQueryName}" name="${entityQueryName}" class="form-horizontal" method="post" action="search">
							<input type="hidden" id="pageSize" name="pageSize" value="$!${entityQueryName}.pageSize"/>
							<input type="hidden" id="currentPage" name="currentPage" value="$!${entityQueryName}.currentPage"/>
#foreach($attr in $!attrs)
							<div class="control-group">
                        		<label class="control-label" style="width:100px;">$attr.cnName</label>
		                        <div style="float:left;padding-top:8px;padding-bottom:8px;margin-left:12px;">
		                            <input type="text" name="$attr.aliasInJava" id="$attr.aliasInJava" value="$!${entityQueryName}.${attr.aliasInJava}" placeholder="$!${entityQueryName}.${attr.aliasInJava}" style="width:160px;"/>
		                        </div>
							</div>
#end
							<div class="control-group">
		                        <label class="control-label"  style="width:35px;"></label>
								<div style="float:left;padding-top:8px;padding-bottom:8px;margin-left:12px;">
									<button class="btn btn-primary" id="querybtn">查询</button> <a href="create.init">添加</a>
								</div>
               	 			</div>
           				</form>
          			</div>
       			</div>
    		</div>
		</div>
		
		<div class="row-fluid">
    		<div class="span12">
	            <table id="data-tab" data-filter="#filter" class="table table-bordered table-striped footable table-hover table-condensed">
	           		<tbody>
	                	<tr class="title_table">
#foreach($attr in $!attrs)
							<td>$attr.cnName</td>
#end
		                    <td>操作</td>
		                </tr>
		                \#foreach($$entityReferName in \$pageInfo.items)
		                <tr >
#foreach($attr in $!attrs)
							<td>$!${entityReferName}.${attr.aliasInJava}</td>
#end
							<td><a href="edit.init?id=$!${entityReferName}.id">修改</a><a href="delete?id=$!${entityReferName}.id">删除</a></td>
						</tr>
						\#end
		 			</tbody>
 				</table>
				\#parse("./common/page2.vm")
				#_PAGE_VIEW_($pageInfo)
			</div>
		</div>
	</div>
	\#parse("./common/include-foot.vm")
</div>
</body>
<script>
function gopage(page){
	$(":input[name='currentPage']").val(page);
	$("#${entityQueryName}").submit();
}
</script>
</html>