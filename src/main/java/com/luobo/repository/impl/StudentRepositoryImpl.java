package com.luobo.repository.impl;

import com.luobo.entity.Student;
import com.luobo.repository.BigWorkRepository;
import com.luobo.repository.StudentRepository;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * . Description: Date: 2019/3/11 14:19
 *
 * @author: ws
 * @version: 1.0
 */
@Repository
public class StudentRepositoryImpl extends BaseRepositoryImpl<Student,Long> implements StudentRepository {

	@Autowired
	private BigWorkRepository bigWorkRepository;

	public Student findByNo(String no) {
		String hql = "from Student s where s.number = :no";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("no", no);
		List<Student> students = query.list();
		if (students != null && students.size() != 0) {
			return (Student) students.get(0);
		}
		return null;
	}

	@Override
	public void update(Student student) {
		String hql = "update Student s set s.bigWork.id=:bigWorkId where id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("bigWorkId", student.getBigWork().getId());
		query.setParameter("id", student.getId());
		query.executeUpdate();
	}

	@Override
	public List<Student> findAllByTeacher(Long teacherId) {
		List<Long> studentIds = bigWorkRepository.findAllStuIdsByTeacher(teacherId);
		String hql = "from Student s where id in (:studentIds)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("studentIds", studentIds);
		List<Student> students = query.list();
		return students;
	}
}
