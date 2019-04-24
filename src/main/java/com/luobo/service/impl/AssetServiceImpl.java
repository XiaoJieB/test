package com.luobo.service.impl;

import com.luobo.entity.Asset;
import com.luobo.entity.Asset.Status;
import com.luobo.entity.Asset.Type;
import com.luobo.entity.AssetFaultRecord;
import com.luobo.entity.AssetFaultRecord.State;
import com.luobo.entity.AssetRack;
import com.luobo.entity.InRackAsset;
import com.luobo.entity.dto.AssetDto;
import com.luobo.repository.AssetFaultRecordRepository;
import com.luobo.repository.AssetRackRepository;
import com.luobo.repository.AssetRepository;
import com.luobo.repository.BaseRepository;
import com.luobo.repository.InRackAssetRepository;
import com.luobo.service.AssetService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * . Description: Date: 2019/4/22 10:19
 *
 * @author: ws
 * @version: 1.0
 */
@Service
public class AssetServiceImpl extends BaseServiceImpl<Asset,Long> implements AssetService {
	@Autowired
	private AssetRepository repository;
	@Autowired
	private AssetRackRepository assetRackRepository;
	@Autowired
	private InRackAssetRepository inRackAssetRepository;
	@Autowired
	private AssetFaultRecordRepository assetFaultRecordRepository;
	@Override
	public BaseRepository<Asset, Long> getDao() {
		return repository;
	}

	@Override
	public Long save(AssetDto assetDto) {
		if (assetDto.getAssetRack() != null) {
			AssetRack assetRack = assetDto.getAssetRack();
			assetRack.setStatus(Status.normal);
			return assetRackRepository.save(assetRack);
		}

		if (assetDto.getInRackAsset() != null) {
			InRackAsset inRackAsset = assetDto.getInRackAsset();
			inRackAsset.setStatus(Status.normal);
			return inRackAssetRepository.save(inRackAsset);
		}
		return null;
	}

	public AssetDto getDto(Long id) {
		AssetDto dto = new AssetDto();
		Asset asset = repository.get(id);
		if (asset.getType() == Type.IN_RACK) {
			dto.setInRackAsset(inRackAssetRepository.get(id));
			return dto;
		}
		if (asset.getType() == Type.RACK) {
			dto.setAssetRack(assetRackRepository.get(id));
			return dto;
		}
		return dto;
	}

	public void update(AssetDto assetDto) throws Exception {
		if (assetDto.getAssetRack() != null) {
			AssetRack assetRack = assetDto.getAssetRack();
			assetRackRepository.update(assetRack);
		}
		if (assetDto.getInRackAsset() != null) {
			InRackAsset inRackAsset = assetDto.getInRackAsset();
			inRackAssetRepository.update(inRackAsset);
		}
	}

	public void updateStatus(Long id, Status status) {
		repository.updateStatus(id,status);
		AssetFaultRecord assetFaultRecord = new AssetFaultRecord();
		assetFaultRecord.setAssetId(id);
		assetFaultRecord.setRecNo(String.valueOf(System.currentTimeMillis()));
		assetFaultRecord.setState(State.handling);
		assetFaultRecordRepository.save(assetFaultRecord);
	}
}
