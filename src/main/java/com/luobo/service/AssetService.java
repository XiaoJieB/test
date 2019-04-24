package com.luobo.service;

import com.luobo.entity.Asset;
import com.luobo.entity.Asset.Status;
import com.luobo.entity.dto.AssetDto;

public interface AssetService extends BaseService<Asset,Long> {

	Long save(AssetDto assetDto);

	AssetDto getDto(Long id);

	void updateStatus(Long id,Status status);
}
