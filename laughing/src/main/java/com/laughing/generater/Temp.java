package com.laughing.generater;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 系统临时数据表TBL_TEMP
 * @author zhichao.sun
 * @since 2014年6月18日19:25:34
 */
public class Temp {

  private Long id;
  /**
   * 创建时间：CREATE_TIME
   */
  private Date createTime;
  /**
   * 类型：TYPE
   */
  private String type;
  /**
   * 临时数据来源
   */
  private Long tempId;
  /**
   * 临时数据
   */
  private String temp;
  

  public Temp() {
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public Long getTempId() {
    return tempId;
  }
  public void setTempId(Long tempId) {
    this.tempId = tempId;
  }
  public String getTemp() {
    return temp;
  }
  public void setTemp(String temp) {
    this.temp = temp;
  }
}
