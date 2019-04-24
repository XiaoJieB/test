package com.luobo.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ws
 * On 2/2/2017.2:28 PM
 */
public interface BaseRepository<T,PK extends Serializable>{
	T load(PK id);

	T get(PK id);

	List<T> findAll();

	void persist(T entity);

	PK save(T entity);

	T merge(T entity);

	void update(T entity) throws Exception;

	void delete(PK id);

	void flush();
}