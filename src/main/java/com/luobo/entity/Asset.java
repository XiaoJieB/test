package com.luobo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * . Description: Date: 2019/4/22 10:11
 *
 * @author: ws
 * @version: 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)//超类使用joined整合到各自的表，SINGLE_TABLE,整合到一张表
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ASSET")
public class Asset {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Column(updatable = false, insertable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

	private Status status;

	public enum Status {
		normal,//正常
		abnormal,//异常
		dumping//报废
	}

	public enum Type{
		ASSET, // 普通资产
		RACK, // 机柜资产
		IN_RACK, // 在柜资产
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
