package com.luobo.service;

import com.luobo.entity.BigWork;
import java.util.List;

public interface BigWorkService extends BaseService<BigWork,Long>{
	List<BigWork> findAllByTeacher(Long teacherId);
}
