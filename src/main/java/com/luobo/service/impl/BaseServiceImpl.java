package com.luobo.service.impl;

import com.luobo.repository.BaseRepository;
import com.luobo.service.BaseService;
import java.io.Serializable;
import java.util.List;

/**
 * . Description: Date: 2019/4/18 17:16
 *
 * @author: ws
 * @version: 1.0
 */
public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T,ID> {

	public BaseRepository<T, ID> getDao() {
		return null;
	}

	@Override
	public T load(ID id) {
		return getDao().load(id);
	}

	@Override
	public T get(ID id) {
		return getDao().get(id);
	}

	@Override
	public T merge(T entity) {
		return (T)getDao().merge(entity);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public void persist(T entity) {
		getDao().persist(entity);
	}

	@Override
	public ID save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void update(T entity) throws Exception {
		getDao().update(entity);
	}

	@Override
	public void delete(ID id) {
		getDao().delete(id);
	}

	@Override
	public void flush() {
		getDao().flush();
	}
}
