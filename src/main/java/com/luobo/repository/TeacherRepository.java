package com.luobo.repository;

import com.luobo.entity.Teacher;

/**
 * Created by ws
 * On 2/2/2017.2:25 PM
 */
public interface TeacherRepository extends BaseRepository<Teacher,Long> {

	Teacher findByNo(String no);

}