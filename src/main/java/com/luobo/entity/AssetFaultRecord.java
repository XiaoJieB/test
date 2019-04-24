package com.luobo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * . Description: Date: 2019/4/24 09:51
 *
 * @author: ws
 * @version: 1.0
 */
@Entity
@Table(name="asset_fault_record")
public class AssetFaultRecord {
	@Id
	@GeneratedValue
	private Long id;

	private String recNo;

	private State state;

	private Long assetId;

	public enum State{
		handling,//处理中
		handled//结束
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
