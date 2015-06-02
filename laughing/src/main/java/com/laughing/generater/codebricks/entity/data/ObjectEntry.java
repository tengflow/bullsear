package com.laughing.generater.codebricks.entity.data;

import java.util.List;

public class ObjectEntry {
    private String entityName;
    private String tableName;
    private String comment;
    private List<EntryAttribute> attrs;
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<EntryAttribute> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<EntryAttribute> attrs) {
        this.attrs = attrs;
    }

}
