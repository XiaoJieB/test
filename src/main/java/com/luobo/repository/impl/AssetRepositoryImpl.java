package com.luobo.repository.impl;

import com.luobo.entity.Asset;
import com.luobo.entity.Asset.Status;
import com.luobo.repository.AssetRepository;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * . Description: Date: 2019/4/22 10:17
 *
 * @author: ws
 * @version: 1.0
 */
@Repository
public class AssetRepositoryImpl extends BaseRepositoryImpl<Asset,Long> implements AssetRepository {
	private static Class<Asset> assetClass = (Class<Asset>) Asset.class;

//	@Override
//	public void update(Asset asset) throws Exception {
//		String update = "";
//		Field fields[] = assetClass.getDeclaredFields();
//		Field.setAccessible(fields, true);
//		List<Field> names = new ArrayList<Field>();
//		for (Field field : fields) {
//			String name = field.getName();
//			if (field.get(asset) != null
//				&& field.getName() != "id") {
//				names.add(field);
//				if (field.getType().toString().indexOf("java.") != -1 || field.getType().toString().indexOf(".Asset") != -1) {
//					update += ",b." + name + "=:" + field.getName();
//				} else {
//					update += ",b." + field.getName() + ".id"+ "=:" + field.getName()+"Id";
//				}
//			}
//		}
//		String hql = "update Asset b set b.id=:id" + update + " where b.id=:id";
//		System.out.println(hql);
//		Query query = getCurrentSession().createQuery(hql);
//		Method[] methods = assetClass.getDeclaredMethods();
//		for (Method method : methods) {
//			String methodStr = method.getName();
//			if (methodStr.indexOf("get") != -1) {
//				for (int i = 0; i < names.size(); i++) {
//					Field field = names.get(i);
//					if (methodStr.toLowerCase().indexOf(field.getName().toLowerCase()) != -1) {
//						if (field.getType().toString().indexOf("java.") != -1 || field.getType().toString().indexOf(".Asset") != -1) {
//							query.setParameter(field.getName(),method.invoke(asset));
//						} else {
////							if (methodStr.toLowerCase().indexOf("teacher") != -1) {
////								query.setParameter("teacherId"
////									, asset.getTeacher().getId());
////							}
////							if (methodStr.toLowerCase().indexOf("score") != -1) {
////								query.setParameter("scoreId"
////									, asset.getScore().getId());
////							}
//						}
//					}
//				}
//			}
//		}
//		query.setParameter("id",asset.getId());
//		query.executeUpdate();
//	}

	public void update(Asset asset) {
		String hql = "update Asset asset set name = :name where id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("name",asset.getName());
		query.setParameter("id",asset.getId());
		query.executeUpdate();
	}

//	public void updateStatus(Long id) {
//		String hql = "update Asset asset set status = :status where id = :id";
//		Query query = getCurrentSession().createQuery(hql);
//		query.setParameter("status", Status.abnormal);
//		query.setParameter("id",id);
//		query.executeUpdate();
//	}

	@Override
	public void updateStatus(Long id, Status status) {
		String hql = "update Asset asset set status = :status where id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("id",id);
		query.executeUpdate();
	}
}
