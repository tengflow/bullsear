/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.laughing.generater;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 房间</br>
 * 映射TBL_ROOM
 * @author Albert
 * 
 */
public class Room {

	private Long id;
	/**
	 *  版本
	 */
	private Integer version;
	/**
	 * 房型编码
	 */
	private String roomCode;
	/**
	 * 房型名称
	 */
	private String roomName;
	/**
	 * 酒店外键ID
	 */
	private Integer hotelId;
	/**
	 * 房型面积
	 */
	private BigDecimal roomArea;
	/**
	 * 床型外键ID
	 */
	private Integer bedId;
	/**
	 * 是否有宽带（0、false  1、true）
	 */
	private boolean hasBroadent;
	/**
	 * 宽带价格
	 */
	private BigDecimal broadentPrice;
	/**
	 * 房间最多可入住人数
	 */
	private Integer personCount;
	/**
	 * 是否可以加床(0、false  1、true)
	 */
	private boolean isAddBed;
	/**
	 * 加床价格
	 */
	private BigDecimal addBedPrice;
	/**
	 * 房型描述
	 */
	private String describtion;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 是否有效
	 */
	private boolean isEffective;
	/**
	 * 创建时间
	 */
	private String gmtCreated;
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 二维码值房预授权金额
	 */
	private BigDecimal qrcodeAuthAmount;
	/**
	 * 楼层信息
	 */
	private String floor;

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public BigDecimal getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(BigDecimal roomArea) {
		this.roomArea = roomArea;
	}

	public Integer getBedId() {
		return bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public boolean getHasBroadent() {
		return hasBroadent;
	}

	public void setHasBroadent(boolean hasBroadent) {
		this.hasBroadent = hasBroadent;
	}

	public BigDecimal getBroadentPrice() {
		return broadentPrice;
	}

	public void setBroadentPrice(BigDecimal broadentPrice) {
		this.broadentPrice = broadentPrice;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public boolean getIsAddBed() {
		return isAddBed;
	}

	public void setIsAddBed(boolean isAddBed) {
		this.isAddBed = isAddBed;
	}

	public BigDecimal getAddBedPrice() {
		return addBedPrice;
	}

	public void setAddBedPrice(BigDecimal addBedPrice) {
		this.addBedPrice = addBedPrice;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(boolean isEffective) {
		this.isEffective = isEffective;
	}

	public String getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setAddBed(boolean isAddBed) {
		this.isAddBed = isAddBed;
	}

	public void setEffective(boolean isEffective) {
		this.isEffective = isEffective;
	}
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public BigDecimal getQrcodeAuthAmount() {
        return qrcodeAuthAmount;
    }

    public void setQrcodeAuthAmount(BigDecimal qrcodeAuthAmount) {
        this.qrcodeAuthAmount = qrcodeAuthAmount;
    }

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}