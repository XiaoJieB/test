package com.luobo.repository.impl;

import com.luobo.entity.InRackAsset;
import com.luobo.repository.InRackAssetRepository;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * . Description: Date: 2019/4/23 14:44
 *
 * @author: ws
 * @version: 1.0
 */
@Repository
public class InRackAssetRepositoryImpl extends BaseRepositoryImpl<InRackAsset,Long> implements
	InRackAssetRepository {

	@Override
	public void update(InRackAsset asset) {
		String hql = "update AssetRack asset set name = :name,positionInRack=:positionInRack where id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("name",asset.getName());
		query.setParameter("positionInRack",asset.getPositionInRack());
		query.setParameter("id",asset.getId());
		query.executeUpdate();
	}
}
