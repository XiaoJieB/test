package com.luobo.repository;

import com.luobo.entity.Asset;
import com.luobo.entity.Asset.Status;

public interface AssetRepository extends BaseRepository<Asset,Long> {
//	void updateStatus(Long id);

	void updateStatus(Long id, Status dumping);
}
