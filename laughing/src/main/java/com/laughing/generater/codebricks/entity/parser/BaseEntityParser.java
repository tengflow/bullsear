package com.laughing.generater.codebricks.entity.parser;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.Context;

import com.laughing.generater.codebricks.entity.data.ObjectEntry;
import com.laughing.generater.codebricks.entity.parser.util.ParseUtils;
import com.laughing.generater.codebricks.parser.Parser;

public abstract class BaseEntityParser implements Parser{
    //模块名称，一般包名中会用到
    private String moduleName;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    protected Context toContext(ObjectEntry obj) {
        if(StringUtils.isBlank(this.getModuleName())){
            throw new IllegalArgumentException("ModuleName-模块名称不能为空");
        }
        //处理相关属性
        Context context=ParseUtils.parseContext(obj);
        context.put("moduleName", this.getModuleName());
        return context;
    }
}
