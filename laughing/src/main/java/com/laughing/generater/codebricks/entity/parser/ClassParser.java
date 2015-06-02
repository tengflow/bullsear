package com.laughing.generater.codebricks.entity.parser;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.velocity.context.Context;

import com.laughing.generater.codebricks.entity.data.EntryAttribute;
import com.laughing.generater.codebricks.entity.data.ObjectEntry;

/**
 * ClassParser
 *
 * @author yongteng.huo 2015年5月13日
 * @see
 * @since 1.0
 */
public class ClassParser extends BaseEntityParser {
    private List<Class> classes = new ArrayList<>();
    /**
     * 
     * @param clazz
     */
    public ClassParser(String moduleName,Class clazz) {
        this.setModuleName(moduleName);
        this.classes.add(clazz);
    }

    /**
     * @param classes
     */
    public ClassParser(String moduleName,List<Class> classes) {
        this.setModuleName(moduleName);
        this.classes.addAll(classes);
    }

    public static String transferJavaTypeToJdbcType(String typeInjava) {
        typeInjava = typeInjava.toLowerCase();
        String typeInDb;
        if (ArrayUtils.contains(new String[] { "varchar", "string", "char" }, typeInjava)) {
            typeInDb = "VARCHAR";
        } else if (ArrayUtils.contains(new String[] { "integer", "int" }, typeInjava)) {
            typeInDb = "INTEGER";
        } else if (ArrayUtils.contains(new String[] { "long" }, typeInjava)) {
            typeInDb = "BIGINT";
        } else if (ArrayUtils.contains(new String[] { "decimal","bigdecimal" }, typeInjava)) {
            typeInDb = "DECIMAL";
        } else if (ArrayUtils.contains(new String[] { "double", "float"}, typeInjava)) {
            typeInDb = "DOUBLE";
        } else if (ArrayUtils.contains(new String[] { "boolean" }, typeInjava)) {
            typeInDb = "BIGINT";
        } else if (ArrayUtils.contains(new String[] { "time", "date", "datetime", "timestamp", "canlendar" }, typeInjava)) {
            typeInDb = "TIMESTAMP";
        } else {
            System.out.println("!!!!捕获未知类型！！！！"+typeInjava);
            typeInDb = typeInjava;
        }
        return typeInDb;
    }
    public static String transferJavaTypeToDbType(String typeInjava) {
        typeInjava = typeInjava.toLowerCase();
        String typeInDb;
        if (ArrayUtils.contains(new String[] { "varchar", "string", "char" }, typeInjava)) {
            typeInDb = "VARCHAR";
        } else if (ArrayUtils.contains(new String[] { "integer", "int" }, typeInjava)) {
            typeInDb = "INTEGER";
        } else if (ArrayUtils.contains(new String[] { "decimal","bigdecimal" }, typeInjava)) {
            typeInDb = "DECIMAL";
        } else if (ArrayUtils.contains(new String[] {"double", "float"}, typeInjava)) {
            typeInDb = "DOUBLE";
        } else if (ArrayUtils.contains(new String[] { "long" }, typeInjava)) {
            typeInDb = "LONG";
        } else if (ArrayUtils.contains(new String[] { "time", "date", "datetime", "timestamp", "canlendar" }, typeInjava)) {
            typeInDb = "TIMESTAMP";
        } else if (ArrayUtils.contains(new String[] { "boolean" }, typeInjava)) {
            typeInDb = "BIGINT";
        }else {
            System.out.println("!!!!捕获未知类型！！！！"+typeInjava);
            typeInDb = typeInjava;
        }
        return typeInDb;
    }
    /**
     * Class 属性，如getName，name即为class的attr
     * 
     * @param attrString
     * @return
     */
    public static EntryAttribute parseFromClassAttr(String originName, Class returnClazz) {
        String aliasInjava = originName.substring(0, 1).toLowerCase() + originName.substring(1);
        String aliasInDb = humpToHyphen(aliasInjava);
        String typeInJava = shortName(returnClazz);
        String typeInDb = transferJavaTypeToDbType(returnClazz.getSimpleName());
        String typeInJdbc = transferJavaTypeToJdbcType(returnClazz.getSimpleName());
        return new EntryAttribute(originName,aliasInjava,typeInJava,aliasInDb,typeInDb,typeInJdbc,aliasInjava,0);
    }
    /**
     * 防止未知类型的数据
     * @param returnClazz
     * @return
     */
    private static String shortName(Class returnClazz){
        if(returnClazz==Integer.class||returnClazz==int.class){
            return "Integer";
        }else if(returnClazz==Long.class||returnClazz==long.class){
            return "Long";
        }else if(returnClazz==Float.class||returnClazz==float.class){
            return "Float";
        }else if(returnClazz==Double.class||returnClazz==double.class){
            return "Double";
        }else if(returnClazz==Byte.class||returnClazz==byte.class){
            return "Byte";
        }else if(returnClazz==Character.class||returnClazz==char.class){
            return "Character";
        }else if(returnClazz==Boolean.class||returnClazz==boolean.class){
            return "Boolean";
        }else if(returnClazz==Short.class||returnClazz==short.class){
            return "Short";
        }else if(returnClazz==String.class){
            return "String"; 
        }else if(returnClazz==Date.class){
            return "java.util.Date"; 
        }else if(returnClazz==Timestamp.class){
            return "java.sql.Timestamp"; 
        }else if(returnClazz==java.sql.Date.class){
            return "java.sql.Date"; 
        }else if(returnClazz==BigDecimal.class){
            return "java.math.BigDecimal"; 
        }else{
            System.out.println("!!!!");
            System.out.println("!!!!捕获未知类型！！！！"+returnClazz);
            System.out.println("!!!!");
            throw new RuntimeException("捕获未知类型！");
        }
    }
    private static String humpToHyphen(String aliasInjava) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < aliasInjava.length(); i++) {
            char c = aliasInjava.charAt(i);
            if (c <= 90 && c >= 65&&i!=0) {//大写字母
                temp.append('_');
            }
            temp.append(c);
        }
        return temp.toString().toUpperCase();
    }

    @Override
    public List<Context> parse() {
        List<Context> entrys = new ArrayList<Context>();
        for (Class clazz : classes) {
            ObjectEntry obj = new ObjectEntry();
            List<EntryAttribute> attrs = new ArrayList<EntryAttribute>();
            Method[] ms = clazz.getMethods();
            for (Method m : ms) {
                if (m.getName().startsWith("get")&&((m.getModifiers()&Modifier.PUBLIC)==1)&&!m.getName().equalsIgnoreCase("getClass")) {
                    attrs.add(parseFromClassAttr(m.getName().substring(3), m.getReturnType()));
                }else if (m.getName().startsWith("is")&&((m.getModifiers()&Modifier.PUBLIC)==1)&&!m.getName().equalsIgnoreCase("getClass")) {
                    attrs.add(parseFromClassAttr(m.getName().substring(2), m.getReturnType()));
                }
            }
            obj.setAttrs(attrs);
            obj.setEntityName(clazz.getSimpleName());
            obj.setTableName("TBL_"+humpToHyphen(obj.getEntityName()));
            entrys.add(toContext(obj));
        }
        return entrys;
    }
}
