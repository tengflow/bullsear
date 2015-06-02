{"mapperReferName":"${entityReferName}Mapper","entityMapperClassName":"${entityName}Mapper","entityMapperPackageName":"${basePackageName}.core.dao.${moduleName}.mapper"}
/${javaPath}/#packagetopath("${entityMapperPackageName}.${entityMapperClassName}").java
package ${entityMapperPackageName};

import java.util.List;
import java.util.Map;

import ${entityPackageName}.${entityClassName};


public interface ${entityMapperClassName} {

    int insert(${entityClassName} entity);

    int update(Map<String, Object> conditions);

    int delete(Map<String, Object> conditions);

    List<${entityClassName}> findList(Map<String, Object> conditions);

    int findCount(Map<String, Object> conditions);
}