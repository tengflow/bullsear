package com.laughing.generater.codebricks.httpapi.data;

import java.util.List;


public class ApiInfo {
    private List<String> askAttrs;
    private List<String> replyAttrs;
    
    private String apiName;
    private String address;
    private String addressForTest;
    private String askDataType="xml";//json or xml 默认xml
    private String replyDataType="xml";//json or xml 默认xml
    
    public String getApiName() {
        return apiName;
    }
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
    public String getAskDataType() {
        return askDataType;
    }
    public void setAskDataType(String askDataType) {
        this.askDataType = askDataType;
    }
    public String getReplyDataType() {
        return replyDataType;
    }
    public void setReplyDataType(String replyDataType) {
        this.replyDataType = replyDataType;
    }
    
    public List<String> getAskAttrs() {
        return askAttrs;
    }
    public void setAskAttrs(List<String> askAttrs) {
        this.askAttrs = askAttrs;
    }
    public List<String> getReplyAttrs() {
        return replyAttrs;
    }
    public void setReplyAttrs(List<String> replyAttrs) {
        this.replyAttrs = replyAttrs;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddressForTest() {
        return addressForTest;
    }
    public void setAddressForTest(String addressForTest) {
        this.addressForTest = addressForTest;
    }
    
}
