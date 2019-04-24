package com.luobo.repository;

import com.luobo.entity.AssetFaultRecord;
import com.luobo.entity.AssetFaultRecord.State;

public interface AssetFaultRecordRepository extends BaseRepository<AssetFaultRecord,Long>  {

	void updateStatus(Long id, State state);
}
