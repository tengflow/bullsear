package com.laughing.generater.codebricks.entity.parser.util;

import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import com.laughing.generater.codebricks.entity.data.ObjectEntry;

public class ParseUtils {
    /**
     * 将数据转化为velocity能用的东西
     * @param entry
     * @return
     */
    public static Context parseContext(ObjectEntry entry) {
        Context context=new VelocityContext();
        //类配置。
        context.put("entityName", entry.getEntityName());
        context.put("tableName", entry.getTableName());
        context.put("attrs", entry.getAttrs());
        return context;
    }
    /**
     * 首字母大写
     * @param inCase
     * @return
     */
    public static String upperFirst(String inCase){
        if(StringUtils.isEmpty(inCase)) return inCase;
        return StringUtils.upperCase(String.valueOf(inCase.charAt(0)))+inCase.substring(1);
    }
    /**
     * 将数据库中的column字段名改成java中的属性名称
     * FEILD_NAME 转成  feildName
     * @param aliasInDb
     * @return
     */
    public static String hyphenToHump(String aliasInDb) {
        aliasInDb=aliasInDb.toLowerCase();
        StringBuffer temp = new StringBuffer();
        boolean flag=false;
        for (int i = 0; i < aliasInDb.length(); i++) {
            char c = aliasInDb.charAt(i);
            if (c=='_') {//下一次变成大写字母
                flag=true;
                continue;
            }else{
                temp.append(flag?String.valueOf(c).toUpperCase():c);
                flag=false;
            }
        }
        return temp.toString();
    }
    /**
     * 数据库中有些type带（），如varchar(32)
     * 将长度后面的去除，因为在生成code时无用
     * @param type
     * @return
     */
    public static String clearDataType(String type) {
        int x=Math.max(type.indexOf("("),type.indexOf("（"));
        return x!=-1?type.substring(0,x):type;
    }
    /**
     * 将数据库中的tableName转为java，如TBX_STUDENT,改为 Student；
     * @param tableName
     * @return
     */
    public static  String tranferDbTableNameToJava(String tableName) {
        tableName=tableName.replaceAll("^[A-Z]+_","").toLowerCase();
        StringBuffer temp = new StringBuffer();
        boolean flag=true;
        for (int i = 0; i < tableName.length(); i++) {
            char c = tableName.charAt(i);
            if (c=='_') {//下一次变成大写字母
                flag=true;
                continue;
            }else{
                temp.append(flag?String.valueOf(c).toUpperCase():c);
                flag=false;
            }
        }
        return temp.toString();
    }

//    ARRAY(Types.ARRAY),
//    BIT(Types.BIT),
//    TINYINT(Types.TINYINT),
//    SMALLINT(Types.SMALLINT),
//    INTEGER(Types.INTEGER),
//    BIGINT(Types.BIGINT),
//    FLOAT(Types.FLOAT),
//    REAL(Types.REAL),
//    DOUBLE(Types.DOUBLE),
//    NUMERIC(Types.NUMERIC),
//    DECIMAL(Types.DECIMAL),
//    CHAR(Types.CHAR),
//    VARCHAR(Types.VARCHAR),
//    LONGVARCHAR(Types.LONGVARCHAR),
//    DATE(Types.DATE),
//    TIME(Types.TIME),
//    TIMESTAMP(Types.TIMESTAMP),
//    BINARY(Types.BINARY),
//    VARBINARY(Types.VARBINARY),
//    LONGVARBINARY(Types.LONGVARBINARY),
//    NULL(Types.NULL),
//    OTHER(Types.OTHER),
//    BLOB(Types.BLOB),
//    CLOB(Types.CLOB),
//    BOOLEAN(Types.BOOLEAN),
//    CURSOR(-10), // Oracle
//    UNDEFINED(Integer.MIN_VALUE + 1000),
//    NVARCHAR(Types.NVARCHAR), // JDK6
//    NCHAR(Types.NCHAR), // JDK6
//    NCLOB(Types.NCLOB), // JDK6
//    STRUCT(Types.STRUCT);
    /**
     * 转成JDBC的type
     * @param typeInDb
     * @return
     */
    public static String transferDbTypeToJdbcType(String typeInDb){
        typeInDb = typeInDb.toLowerCase();
        String typeInJava;
        if (ArrayUtils.contains(new String[] { "varchar", "string", "char","text","long varchar" }, typeInDb)) {
            typeInJava = "VARCHAR";
        } else if (ArrayUtils.contains(new String[] { "integer", "int" }, typeInDb)) {
            typeInJava = "INTEGER";
        } else if (ArrayUtils.contains(new String[] { "bigint" }, typeInDb)) {
            typeInJava = "BIGINT";
        } else if (ArrayUtils.contains(new String[] { "long", "double", "float" }, typeInDb)) {
            typeInJava = "DOUBLE";
        } else if (ArrayUtils.contains(new String[] { "decimal" }, typeInDb)) {
            typeInJava = "DECIMAL";
        } else if (ArrayUtils.contains(new String[] { "boolean" }, typeInDb)) {
            typeInJava = "BOOLEAN";
        } else if (ArrayUtils.contains(new String[] { "time", "date", "datetime", "timestamp", "canlendar" }, typeInDb)) {
            typeInJava = "TIMESTAMP";
        } else if (ArrayUtils.contains(new String[] { "clob"}, typeInDb)) {
            typeInJava = "VARCHAR";
        }  else if (ArrayUtils.contains(new String[] { "blob"}, typeInDb)) {
            typeInJava = "BYTE";
        }  else {
            System.out.println("!!!!");
            System.out.println("!!!!捕获未知类型！！！！"+typeInDb);
            System.out.println("!!!!");
            throw new RuntimeException("!捕获未知类型！！");
        }
        return typeInJava;
    }

    /**
     * db中的Type改为java中的Type
     * @param typeInDb
     * @return
     */
    public static String transferDbTypeToJavaType(String typeInDb) {
        typeInDb = typeInDb.toLowerCase();
        String typeInJava;
        if (ArrayUtils.contains(new String[] { "varchar", "string", "char","text","long varchar" }, typeInDb)) {
            typeInJava = "String";
        } else if (ArrayUtils.contains(new String[] { "integer", "int" }, typeInDb)) {
            typeInJava = "Integer";
        } else if (ArrayUtils.contains(new String[] { "bigint" }, typeInDb)) {
            typeInJava = "Long";
        } else if (ArrayUtils.contains(new String[] { "long", "double", "float","decimal" }, typeInDb)) {
            typeInJava = "Double";
        } else if (ArrayUtils.contains(new String[] { "decimal" }, typeInDb)) {
            typeInJava = "java.math.BigDecimal";
        } else if (ArrayUtils.contains(new String[] { "boolean" }, typeInDb)) {
            typeInJava = "Boolean";
        } else if (ArrayUtils.contains(new String[] { "time", "date", "datetime", "timestamp", "canlendar" }, typeInDb)) {
            typeInJava = "java.util.Date";
        } else if (ArrayUtils.contains(new String[] { "blob"}, typeInDb)) {
            typeInJava = "Byte";
        }  else if (ArrayUtils.contains(new String[] { "clob"}, typeInDb)) {
            typeInJava = "String";
        } else {
            System.out.println("!!!!");
            System.out.println("!!!!捕获未知类型！！！！"+typeInDb);
            System.out.println("!!!!");
            throw new RuntimeException("!捕获未知类型！！");
        }
        return typeInJava;
    }
}
