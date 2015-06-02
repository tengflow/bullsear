package com.laughing.generater.codebricks.entity.parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.context.Context;

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
public class DataBaseTableParser extends BaseEntityParser {
    String driverName="com.ibm.db2.jcc.DB2Driver";
    String dbUrl="jdbc:db2://172.17.106.194:50000/hotel:currentSchema=HOTEL;";
    String user="db2inst";
    String passwd="dev8132430";
    private String[] tables=null;
    /**
     * 不要太复杂哦
     * @param pdm
     */
    public DataBaseTableParser(String moduleName,String[] tables) {
        this.setModuleName(moduleName);
        this.tables=tables;
    }
    public DataBaseTableParser(String moduleName,String driverName,String dbUrl,String user,String passwd,String[] tables) {
        this.setModuleName(moduleName);
        this.driverName=driverName;
        this.dbUrl=dbUrl;
        this.user=user;
        this.passwd=passwd;
        this.tables=tables;
    }

    @Override
    public List<Context> parse() {
        List<Context> entrys=new ArrayList<Context>();
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            Connection conn=DriverManager.getConnection(dbUrl, user, passwd);
            
            for(String table:tables){
                ObjectEntry oe= new ObjectEntry();
                oe.setTableName(table);//表名
                oe.setEntityName(ParseUtils.tranferDbTableNameToJava(oe.getTableName()));//实体名称，表名去除TBL_后的骆驼命名法

                ResultSetMetaData rsmd=conn.createStatement().executeQuery("select * from "+table).getMetaData();
                List<EntryAttribute> list=new ArrayList<EntryAttribute>();
                for(int i=1;i<=rsmd.getColumnCount();i++){
                    list.add(genObject(rsmd, i));
                }
                oe.setAttrs(list);//设置字段属性            
                entrys.add(toContext(oe));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entrys;
    }
    private EntryAttribute genObject(ResultSetMetaData rsmd, int i) throws SQLException {
        EntryAttribute ea=new EntryAttribute();
        String column=rsmd.getColumnName(i);
        String type=rsmd.getColumnTypeName(i);
        String comment=rsmd.getColumnLabel(i).equals(column)? null:rsmd.getColumnLabel(i);
        Integer length=rsmd.getColumnDisplaySize(i);
        //字段在db中的code
        ea.setAliasInDb(column);
        //改为骆驼命名法
        ea.setAliasInJava(ParseUtils.hyphenToHump(ea.getAliasInDb()));
        //第一个字母大写的名字
        ea.setName(ParseUtils.upperFirst(ea.getAliasInJava()));
        //中文名称
        ea.setCnName(ea.getCnName());
        //注释
        ea.setComment(comment);
        //db中的类型
        ea.setTypeInDb(ParseUtils.clearDataType(type));
        //转为java中的类型
        ea.setTypeInJava(ParseUtils.transferDbTypeToJavaType(ea.getTypeInDb()));
        //转为jdbc中的类型
        ea.setTypeInJdbc(ParseUtils.transferDbTypeToJdbcType(ea.getTypeInDb()));
        //length
        ea.setLengthInDb(length);
        return ea;
    }
}
