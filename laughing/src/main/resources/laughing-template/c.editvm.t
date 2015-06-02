{"":""}
/${webappPath}/WEB-INF/pages/${moduleName}/${entityReferName}.edit.vm
\#parse("./common/include-base.vm")
</head>
<body>
	<div id="content">
		<div id="content-header" class="height37"></div>
		<div id="breadcrumb"> <a href="#" title="返回首页" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#">OTS</a> <a href="#" class="current">添加</a> </div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
		        	<form id="${entityClassName}" class="form-horizontal" method="post" action="edit" enctype="multipart/form-data" >
						<div class="widget-box">
							<div class="widget-title"><h5 class="font16">编辑</h5></div>
							<div class="widget-content nopadding">
#foreach($attr in $!attrs)
								<div class="control-group">
									<label class="control-label">$attr.cnName</label>
									<div class="controls1"><input type="text" id="$attr.aliasInJava" name="$attr.aliasInJava" value="$!${entityReferName}.${attr.aliasInJava}" style="width:200px;" maxlength="20"></div>
								</div>
#end
								<div class="control-group gray_back">
			                        <div class="controls1">
			                           <button class="btn btn-primary" type="submit" id="submitButton" >确定</button>
			                        </div>
								</div>
								
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<br/>
		\#parse("./common/include-foot.vm")
	</div>
</body>
</html>