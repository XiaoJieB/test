package com.luobo.repository;

import com.luobo.entity.BigWork;
import java.util.List;

public interface BigWorkRepository extends BaseRepository<BigWork,Long> {

	List<BigWork> findAllByTeacher(Long teacherId);

	void update (BigWork bigWork) throws Exception;

	public List<Long> findAllStuIdsByTeacher(Long teacherId);
}
