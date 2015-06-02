{"serviceReferName":"${entityReferName}Service","entityServiceClassName":"${entityName}Service","entityServicePackageName":"${basePackageName}.biz.${moduleName}.service"}
/${javaPath}/#packagetopath("${entityServicePackageName}.${entityServiceClassName}").java
package ${entityServicePackageName};

import com.laughing.framework.abs.GenericService;
import ${entityMapperPackageName}.${entityMapperClassName};
import ${entityPackageName}.${entityClassName};

public interface ${entityServiceClassName} extends GenericService<${entityClassName},${entityMapperClassName}> {

}