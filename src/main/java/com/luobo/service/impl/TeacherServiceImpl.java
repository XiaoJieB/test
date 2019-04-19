package com.luobo.service.impl;

import com.luobo.entity.Teacher;
import com.luobo.repository.BaseRepository;
import com.luobo.repository.TeacherRepository;
import com.luobo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * . Description: Date: 2019/3/11 17:29
 *
 * @author: ws
 * @version: 1.0
 */
@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Long> implements TeacherService {

	@Autowired
	private TeacherRepository repository;

	@Override
	public BaseRepository<Teacher, Long> getDao() {
		return repository;
	}

	@Override
	public Teacher findByNo(String no){
		return repository.findByNo(no);
	}
}
