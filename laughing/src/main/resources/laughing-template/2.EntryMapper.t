{"mapperReferName":"${entityReferName}Mapper","entityMapperClassName":"${entityName}Mapper","entityMapperPackageName":"${basePackageName}.biz.${moduleName}.mapper"}
/${javaPath}/#packagetopath("${entityMapperPackageName}.${entityMapperClassName}").java
package ${entityMapperPackageName};

import com.laughing.framework.abs.GenericMapper;
import ${entityPackageName}.${entityClassName};


public interface ${entityMapperClassName} extends GenericMapper<${entityClassName}>{
}