package com.luobo.entity.dto;

import com.luobo.entity.Asset;
import com.luobo.entity.AssetRack;
import com.luobo.entity.InRackAsset;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * . Description: Date: 2019/4/23 16:04
 *
 * @author: ws
 * @version: 1.0
 */
public class AssetDto implements Serializable {

	private Asset asset;

	private AssetRack assetRack;

	private InRackAsset inRackAsset;

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public AssetRack getAssetRack() {
		return assetRack;
	}

	public void setAssetRack(AssetRack assetRack) {
		this.assetRack = assetRack;
	}

	public InRackAsset getInRackAsset() {
		return inRackAsset;
	}

	public void setInRackAsset(InRackAsset inRackAsset) {
		this.inRackAsset = inRackAsset;
	}
}
