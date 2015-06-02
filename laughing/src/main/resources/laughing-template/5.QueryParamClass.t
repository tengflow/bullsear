{"entityQueryName":"${entityReferName}Query","entityQueryClassName":"${entityName}Query","entityQueryPackageName":"${basePackageName}.biz.${moduleName}.controller.query"}
/${javaPath}/#packagetopath("${entityQueryPackageName}.${entityQueryClassName}").java
package ${entityQueryPackageName};

import org.apache.commons.lang.StringUtils;

import com.laughing.framework.page.PageQuery;

public class ${entityQueryClassName} extends PageQuery{
#foreach($attr in $!attrs)
	/**
	*$!attr.comment
	*$!attr.aliasInDb
	*/ 
	private $attr.typeInJava $attr.aliasInJava ;
#end
#foreach($attr in $!attrs)
	/**
	*$!attr.comment
	*/
	public $attr.typeInJava get#upperfirst(${attr.aliasInJava})(){
#if($attr.typeInJava=="java.lang.String"||$attr.typeInJava=="String")
		if(StringUtils.isBlank(${attr.aliasInJava})){
			return null;
		}
#end
		return this.$attr.aliasInJava;
	}
	/**
	*$!attr.comment
	*/ 
	public void set#upperfirst(${attr.name})($attr.typeInJava ${attr.aliasInJava}){
		this.$attr.aliasInJava=${attr.aliasInJava};
	} 
#end
}