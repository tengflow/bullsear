{"entityServiceImplClassName":"${entityName}ServiceImpl","entityServiceImplPackageName":"${basePackageName}.core.biz.${moduleName}"}
/${javaPath}/#packagetopath("${entityServiceImplPackageName}.${entityServiceImplClassName}").java
package ${entityServiceImplPackageName};

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yeepay.hotel.utils.PageInfo;
import com.yeepay.g3.utils.protocol.JsonUtils2;
import com.yeepay.g3.utils.UtilMisc;

import ${entityPackageName}.${entityClassName};
import ${entityMapperPackageName}.${entityMapperClassName};
import ${entityServicePackageName}.${entityServiceClassName};

public class ${entityServiceImplClassName} implements ${entityServiceClassName} {
    @Resource 
    private ${entityMapperClassName} ${mapperReferName};
    
    public void create(${entityClassName} t){
    	${mapperReferName}.insert(t);
    }
    
    public int deleteByPk(Object pk){
    	return ${mapperReferName}.delete(UtilMisc.toMap("id",pk));
    }
    
    public int delete(${entityClassName} t){
    	return ${mapperReferName}.delete(JsonUtils2.pojo2map(t));
    }
    
    public int delete(Map<String, Object> conditions){
    	return ${mapperReferName}.delete(conditions);
    }

    public int update(${entityClassName} t){
    	Map<String,Object> args=JsonUtils2.pojo2map(t);
    	Object id=t.getId();
    	args.put("_id",id);
    	//开启乐观锁
    	//args.put("version",(Long)args.get("version")+1);
    	//Object version=t.getVersion();
    	//args.put("_version",version);
    	return ${mapperReferName}.update(args);
    }
    /**
    *条件前加“_” 设置值不用变
    */
    public int updateAll(Map<String, Object> conditions){
    	return ${mapperReferName}.update(conditions);
    }
    
    public ${entityClassName} get(Object id){
    	List<${entityClassName}> list=${mapperReferName}.findList(UtilMisc.toMap("id",id));
    	return(list!=null&&list.size()>0)?list.get(0):null;
    }
    /**
	*亦可使用startIndex，endIndex限制查询结果数量
    */
    public List<${entityClassName}> findAll(Map<String, Object> conditions){
    	return ${mapperReferName}.findList(conditions);
    }
    public int findCount(Map<String, Object> conditions){
    	return ${mapperReferName}.findCount(conditions);
    }
    public PageInfo<${entityClassName}> findList(int pageSize, int currentPage, Map<String, Object> conditions){
        Integer totalCount = ${mapperReferName}.findCount(conditions);
        Integer startIndex = PageInfo.startIndex(currentPage, pageSize);
        Integer endIndex = PageInfo.endIndex(currentPage, pageSize);

        conditions.putAll(UtilMisc.toMap("startIndex", startIndex, "endIndex", endIndex));
        List<${entityClassName}> items = ${mapperReferName}.findList(conditions);
        return PageInfo.createPage(items, totalCount, pageSize, currentPage);
    }
}