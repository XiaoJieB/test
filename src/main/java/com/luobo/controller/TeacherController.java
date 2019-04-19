package com.luobo.controller;

import com.luobo.entity.Teacher;
import com.luobo.service.StudentService;
import com.luobo.service.TeacherService;
import com.luobo.util.Constants;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * . Description: Date: 2019/3/12 11:27
 *
 * @author: ws
 * @version: 1.0
 */
@Controller
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	@Autowired
	StudentService studentService;

	@RequestMapping("teacher/list")
	public String list(ModelMap map) {
		map.addAttribute("teacherList",teacherService.findAll());
		return "teacher/index";
	}

	@RequestMapping("teacher/findAllStudent")
	public String findAllStudent(ModelMap map, HttpServletRequest request) {
		Teacher teacher = (Teacher) request.getSession().getAttribute(Constants.TEACHER_CONTEXT);
		map.addAttribute("studentList"
			,studentService.findAllByTeacher(teacher.getId()));
		return "teacher/studentList";
	}
}
