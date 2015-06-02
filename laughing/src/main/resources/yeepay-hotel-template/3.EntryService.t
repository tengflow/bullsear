{"serviceReferName":"${entityReferName}Service","entityServiceClassName":"${entityName}Service","entityServicePackageName":"${basePackageName}.core.biz.${moduleName}"}
/${javaPath}/#packagetopath("${entityServicePackageName}.${entityServiceClassName}").java
package ${entityServicePackageName};

import java.util.List;
import java.util.Map;

import com.yeepay.hotel.utils.PageInfo;
import ${entityPackageName}.${entityClassName};

public interface ${entityServiceClassName} {
    void create(${entityClassName} t);
    
    int deleteByPk(Object pk);
    int delete(${entityClassName} t);
    int delete(Map<String, Object> conditions);

    int update(${entityClassName} t);
    int updateAll(Map<String, Object> conditions);
    
    ${entityClassName} get(Object id);
    List<${entityClassName}> findAll(Map<String, Object> conditions);
    int findCount(Map<String, Object> conditions);
    PageInfo<${entityClassName}> findList(int pageSize, int currentPage, Map<String, Object> conditions);
}