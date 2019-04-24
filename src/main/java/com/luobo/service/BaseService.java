package com.luobo.service;

import com.luobo.repository.BaseRepository;
import java.io.Serializable;
import java.util.List;

public interface BaseService<T,ID extends Serializable> {

	public BaseRepository<T, ID> getDao();

	T load(ID id);

	T get(ID id);

	T merge(T entity);

	List<T> findAll();

	void persist(T entity);

	ID save(T entity);

	void update(T entity) throws Exception;

	void delete(ID id);

	void flush();
}
