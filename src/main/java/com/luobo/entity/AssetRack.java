package com.luobo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * . Description: Date: 2019/4/23 10:20
 *
 * @author: ws
 * @version: 1.0
 */
@Entity
@DiscriminatorValue("RACK")
@PrimaryKeyJoinColumn(name = "asset_rack_id")
@Table(name = "asset_rack")
public class AssetRack extends Asset {
	@Column(name = "rack_size")
	private Integer rackSize; // 机柜总容量 U（unit）

	public AssetRack() {
		super();
		super.setType(Type.RACK);
	}

	public Integer getRackSize() {
		return rackSize;
	}

	public void setRackSize(Integer rackSize) {
		this.rackSize = rackSize;
	}
}
