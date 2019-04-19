package com.luobo.repository.impl;

import com.luobo.entity.Student;
import com.luobo.entity.Teacher;
import com.luobo.repository.TeacherRepository;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * . Description: Date: 2019/3/11 14:11
 *
 * @author: ws
 * @version: 1.0
 */
@Repository
public class TeacherRepositoryImpl extends BaseRepositoryImpl<Teacher,Long> implements TeacherRepository {

	@Override
	public Teacher findByNo(String no) {
		String hql = "from Teacher s where s.number = :no";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("no",no);
		List<Teacher> teachers = query.list();
		if (teachers != null && teachers.size() != 0) {
			return (Teacher) teachers.get(0);
		}
		return null;
	}
}
