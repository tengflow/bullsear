package com.laughing.generater;

import java.util.List;

/**
 * 酒店配置表
 * 
 * @author Albert
 *
 */
public class HotelConfig {
    /**
     * 
     */
    private Long id;
    /**
     * 酒店配置服务名称
     */
    private String name;
    /**
     * 酒店配置服务描述
     */
    private String describtion;
    /**
     * 酒店编号
     */
    private Long hotelId;
    /**
     * 酒店类型
     */
    private String type;
    /**
     * 父类型ID
     */
    private Long parentId;
    /**
     * 顺序
     */
    private Integer sequence;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
