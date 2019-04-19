package com.luobo.service.impl;

import com.luobo.entity.Student;
import com.luobo.repository.BaseRepository;
import com.luobo.repository.StudentRepository;
import com.luobo.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * . Description: Date: 2019/3/11 16:01
 *
 * @author: ws
 * @version: 1.0
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Long> implements StudentService {

	@Autowired
	private StudentRepository repository;

	@Override
	public BaseRepository<Student, Long> getDao() {
		return repository;
	}

	public Student findByNo(String no){
		return repository.findByNo(no);
	}

	@Override
	public List<Student> findAllByTeacher(Long teacherId) {
		return repository.findAllByTeacher(teacherId);
	}
}
