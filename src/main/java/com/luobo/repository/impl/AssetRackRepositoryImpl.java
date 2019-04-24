package com.luobo.repository.impl;

import com.luobo.entity.AssetRack;
import com.luobo.repository.AssetRackRepository;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * . Description: Date: 2019/4/23 14:36
 *
 * @author: ws
 * @version: 1.0
 */
@Repository
public class AssetRackRepositoryImpl extends BaseRepositoryImpl<AssetRack,Long> implements
	AssetRackRepository {
	@Override
	public void update(AssetRack asset) {
		String hql = "update AssetRack asset set name = :name,rackSize=:rackSize where id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("name",asset.getName());
		query.setParameter("rackSize",asset.getRackSize());
		query.setParameter("id",asset.getId());
		query.executeUpdate();
	}
}
