package com.luobo.service.impl;

import com.luobo.entity.Asset.Status;
import com.luobo.entity.AssetFaultRecord;
import com.luobo.entity.AssetFaultRecord.State;
import com.luobo.repository.AssetFaultRecordRepository;
import com.luobo.repository.AssetRepository;
import com.luobo.repository.BaseRepository;
import com.luobo.service.AssetFaultRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * . Description: Date: 2019/4/24 09:57
 *
 * @author: ws
 * @version: 1.0
 */
@Service
public class AssetFaultRecordServiceImpl
	extends BaseServiceImpl<AssetFaultRecord,Long> implements
	AssetFaultRecordService {

	@Autowired
	AssetFaultRecordRepository repository;
	@Autowired
	AssetRepository assetRepository;
	@Override
	public BaseRepository<AssetFaultRecord, Long> getDao() {
		return repository;
	}

	@Override
	public void update(AssetFaultRecord assetFaultRecord) {
		repository.updateStatus(assetFaultRecord.getId(),State.handled);
		assetRepository.updateStatus(assetFaultRecord.getId(), Status.dumping);
	}
}
