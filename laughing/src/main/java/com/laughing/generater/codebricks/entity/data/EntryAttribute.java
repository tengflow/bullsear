package com.laughing.generater.codebricks.entity.data;

/**
 * AttributeEntry 属性实体
 *
 * @author yongteng.huo 2015年2月25日
 * @see
 * @since 1.0
 */
public class EntryAttribute{
    private String name;//属性名称，首字母大写，如FeildValue;
    private String cnName;//中文名称
    private String aliasInJava;//class中属性名称，驼峰命名法
    private String typeInJava;//java类型
    private String aliasInDb;//数据库中属性名称，大写_连字符。
    private String typeInDb;//数据库类型
    private Integer lengthInDb;//数据库中长度，为0 即为不受限制
    private String typeInJdbc;
    private String comment;
    public EntryAttribute() {
        super();
    }
    public EntryAttribute(String originName, String aliasInjava, String typeInJava, String aliasInDb, String typeInDb,String typeInJdbc,String cnName, int lengthInDb) {
        this.name=originName;
        this.aliasInJava=aliasInjava;
        this.typeInJava=typeInJava;
        this.aliasInDb=aliasInDb;
        this.typeInDb=typeInDb;
        this.lengthInDb=lengthInDb;
        this.typeInJdbc=typeInJdbc;
        this.cnName=cnName;
    }
    
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAliasInJava() {
        return aliasInJava;
    }
    public void setAliasInJava(String aliasInJava) {
        this.aliasInJava = aliasInJava;
    }
    public String getTypeInJdbc() {
        return typeInJdbc;
    }
    public void setTypeInJdbc(String typeInJdbc) {
        this.typeInJdbc = typeInJdbc;
    }
    public String getTypeInJava() {
        return typeInJava;
    }
    public void setTypeInJava(String typeInJava) {
        this.typeInJava = typeInJava;
    }
    public String getAliasInDb() {
        return aliasInDb;
    }
    public void setAliasInDb(String aliasInDb) {
        this.aliasInDb = aliasInDb;
    }
    public String getTypeInDb() {
        return typeInDb;
    }
    public void setTypeInDb(String typeInDb) {
        this.typeInDb = typeInDb;
    }
    public Integer getLengthInDb() {
        return lengthInDb;
    }
    public void setLengthInDb(Integer lengthInDb) {
        this.lengthInDb = lengthInDb;
    }
    public String getCnName() {
        return cnName;
    }
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}