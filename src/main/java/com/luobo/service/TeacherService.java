package com.luobo.service;

import com.luobo.entity.Teacher;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherService extends BaseService<Teacher,Long> {

	Teacher findByNo(String no);
}
