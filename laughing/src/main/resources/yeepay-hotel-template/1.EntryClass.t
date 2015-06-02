{"entityReferName":"#lowerfirst($entityName)","entityClassName":"${entityName}Entity","entityPackageName":"${basePackageName}.core.dao.${moduleName}.entity"}
/${javaPath}/#packagetopath("${entityPackageName}.${entityClassName}").java
package ${entityPackageName};

import com.yeepay.g3.utils.protocol.JsonUtils2;
/*
*TableName为 $!tableName
*$!comment
*/
public class ${entityClassName}{
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
		return this.$attr.aliasInJava;
	}
	/**
	*$!attr.comment
	*/ 
	public void set#upperfirst(${attr.name})($attr.typeInJava ${attr.aliasInJava}){
		this.$attr.aliasInJava=${attr.aliasInJava};
	}
#end
	public String toString() {
		return JsonUtils2.obj2json(this);
	}
}