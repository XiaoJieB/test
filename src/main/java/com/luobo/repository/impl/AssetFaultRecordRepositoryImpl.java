package com.luobo.repository.impl;

import com.luobo.entity.Asset.Status;
import com.luobo.entity.AssetFaultRecord;
import com.luobo.entity.AssetFaultRecord.State;
import com.luobo.repository.AssetFaultRecordRepository;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * . Description: Date: 2019/4/24 09:54
 *
 * @author: ws
 * @version: 1.0
 */
@Repository
public class AssetFaultRecordRepositoryImpl
	extends BaseRepositoryImpl<AssetFaultRecord,Long> implements AssetFaultRecordRepository {
	public void updateStatus(Long id,State state) {
		String hql = "update AssetFaultRecord record set state = :state where id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("state",state);
		query.setParameter("id",id);
		query.executeUpdate();
	}

}
