{"entityServiceImplClassName":"${entityName}ServiceImpl","entityServiceImplPackageName":"${basePackageName}.biz.${moduleName}.service.impl"}
/${javaPath}/#packagetopath("${entityServiceImplPackageName}.${entityServiceImplClassName}").java
package ${entityServiceImplPackageName};

import javax.annotation.Resource;

import com.laughing.framework.abs.GenericServiceImpl;
import ${entityPackageName}.${entityClassName};
import ${entityMapperPackageName}.${entityMapperClassName};
import ${entityServicePackageName}.${entityServiceClassName};

public class ${entityServiceImplClassName} extends GenericServiceImpl<${entityClassName},${entityMapperClassName}>  implements ${entityServiceClassName}{
    @Resource 
    private ${entityMapperClassName} ${mapperReferName};
    @Override
    public ${entityMapperClassName} defaultMapper() {
        return ${mapperReferName};
    }
}