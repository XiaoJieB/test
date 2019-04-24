package com.luobo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * . Description: Date: 2019/4/23 10:21
 *
 * @author: ws
 * @version: 1.0
 */
@Entity
@DiscriminatorValue("IN_RACK")
@Table(name = "asset_in_rack")
@PrimaryKeyJoinColumn(name = "in_rack_asset_id")
public class InRackAsset extends Asset {
	@Column(name = "position_in_rack")
	private Integer positionInRack;// 所在机柜u位

	public InRackAsset() {
		super();
		super.setType(Type.IN_RACK);
	}

	public Integer getPositionInRack() {
		return positionInRack;
	}

	public void setPositionInRack(Integer positionInRack) {
		this.positionInRack = positionInRack;
	}
}
