package com.laughing.generater.template.model;

import java.util.List;
import java.util.Map;

public class Template {
    //模版
    private List<Item> items=null;
    //默认配置
    private Map<String,Object> options=null;

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public Map<String, Object> getOptions() {
        return options;
    }
    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}
