package com.laughing.generater.codebricks.entity.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.context.Context;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import com.laughing.generater.codebricks.entity.data.EntryAttribute;
import com.laughing.generater.codebricks.entity.data.ObjectEntry;
import com.laughing.generater.codebricks.entity.parser.util.ParseUtils;
import com.laughing.generater.codebricks.parser.Parser;

/**
 * 解析PDM文件
 * TableParser
 *
 * @author yongteng.huo 2015年3月6日
 * @see
 * @since 1.0
 */
public class PdmFileParser extends BaseEntityParser {
    private File pdm=null;
    /**
     * 不要太复杂哦
     * @param pdm
     */
    public PdmFileParser(String moduleName,File pdm) {
        this.setModuleName(moduleName);
        this.pdm=pdm;
    }


    @SuppressWarnings("rawtypes")
    @Override
    public List<Context> parse() {
        List<Context>  entrys=new ArrayList<Context>();
        try {
            List tableDoms=DocumentHelper.parseText(FileUtils.readFileToString(pdm)).selectNodes("//c:Tables/o:Table");
            System.out.println("发现表的数量为"+tableDoms.size());
            //将xml转化为ObjectEntry
            for(Object o: tableDoms ){
                ObjectEntry entry = generateObjectEntry((Element)o);
                entrys.add(toContext(entry));
            }
            return entrys;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 根据从pdm中解析的对象，生成表
     * @param ele
     * @return
     */
    private ObjectEntry generateObjectEntry(Element ele) {
        ObjectEntry oe= new ObjectEntry();
        //表名
        oe.setTableName(ele.elementText("Code"));
        //实体名称，表名去除TBL_后的骆驼命名法
        oe.setEntityName(ParseUtils.tranferDbTableNameToJava(oe.getTableName()));
        //表的备注
        oe.setComment(StringUtils.isEmpty(ele.elementText("Comment"))?ele.elementText("Name"):ele.elementText("Comment"));
        
        List<EntryAttribute> list=new ArrayList<EntryAttribute>();
        //找出表的字段
        for(Object attrTemp:(ele).selectNodes(".//c:Columns/o:Column")){
            list.add(generateAttributeEntry((Element)attrTemp));
        }
        oe.setAttrs(list);
        return oe;
    }


    /**
     * 根据从pdm中解析的对象,生成实体的属性！
     * @param ele
     * @param attrTemp
     * @return
     */
    private EntryAttribute generateAttributeEntry(Element ele) {
        EntryAttribute ea=new EntryAttribute();
        //字段在db中的code
        ea.setAliasInDb(ele.elementText("Code"));
        //改为骆驼命名法
        ea.setAliasInJava(ParseUtils.hyphenToHump(ea.getAliasInDb()));
        //第一个字母大写的名字
        ea.setName(ParseUtils.upperFirst(ea.getAliasInJava()));
        //中文名称
        ea.setCnName(StringUtils.isEmpty(ele.elementText("Name"))?ea.getAliasInJava():ele.elementText("Name"));
        //注释
        ea.setComment(StringUtils.isEmpty(ele.elementText("Comment"))?ele.elementText("Name"):ele.elementText("Comment"));
        //db中的类型
        ea.setTypeInDb(ParseUtils.clearDataType(ele.elementText("DataType")));
        //转为java中的类型
        ea.setTypeInJava(ParseUtils.transferDbTypeToJavaType(ea.getTypeInDb()));
        //转为jdbc中的类型
        ea.setTypeInJdbc(ParseUtils.transferDbTypeToJdbcType(ea.getTypeInDb()));
        
        String length=ele.elementText("Length");
        if(!StringUtils.isEmpty(length)){
            ea.setLengthInDb(Integer.parseInt(length));
        }
        return ea;
    }
    
}
