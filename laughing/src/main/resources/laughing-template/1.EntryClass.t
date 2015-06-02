{"entityReferName":"#lowerfirst($entityName)","entityClassName":"${entityName}Entity","entityPackageName":"${basePackageName}.biz.${moduleName}.entity"}
/${javaPath}/#packagetopath("${entityPackageName}.${entityClassName}").java
package ${entityPackageName};

import com.laughing.framework.abs.GenericModel;
/*
*TableName为 $!tableName
*$!comment
*/
public class ${entityClassName} extends GenericModel<${pkType}>{
#foreach($attr in $!attrs)
#if($attr.aliasInJava!="id" && $attr.aliasInJava!="version" && $attr.aliasInJava!="createTime" && $attr.aliasInJava!="updateTime")
	/**
	*$!attr.comment
	*$!attr.aliasInDb
	*/ 
	private $attr.typeInJava $attr.aliasInJava ;
#end
#end
#foreach($attr in $!attrs)
#if($attr.aliasInJava!="id" && $attr.aliasInJava!="version" && $attr.aliasInJava!="createTime" && $attr.aliasInJava!="updateTime")
	/**
	*$!attr.comment
	*/ 
	public $attr.typeInJava get#upperfirst(${attr.aliasInJava})(){
		return this.$attr.aliasInJava;
	}
	/**
	*$!attr.comment
	*/ 
	public void set#upperfirst(${attr.name})($attr.typeInJava ${attr.aliasInJava}){
		this.$attr.aliasInJava=${attr.aliasInJava};
	}
#end
#end
}