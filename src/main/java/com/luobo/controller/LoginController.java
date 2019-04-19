
package com.luobo.controller;

import com.luobo.entity.Student;
import com.luobo.entity.Teacher;
import com.luobo.service.StudentService;
import com.luobo.service.TeacherService;
import com.luobo.util.Constants;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController{

	@Autowired
	StudentService studentService;

	@Autowired
	TeacherService teacherService;

	@RequestMapping("/student/login.action")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request,Student student) {
		Map<String, Object> result = new HashMap<String, Object>();
		Student stu = studentService.findByNo(student.getNumber());
		if (stu == null) {
			result.put("msg", "该用户不存在或密码错误！");
			result.put("code", "201");
			return result;
		}
		if (stu.getPassword().equals(student.getPassword())) {
			setSessionStudent(request, stu);
			result.put("msg", "参数不合法！");
			result.put("code", "0");
			return result;
		} else {
			result.put("msg", "该用户不存在或密码错误！");
			result.put("code", "201");
			return result;
		}
	}

	@RequestMapping("/teacher/login.action")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request,Teacher teacher) {
		Map<String, Object> result = new HashMap<String, Object>();
		Teacher stu = teacherService.findByNo(teacher.getNumber());
		if (stu == null) {
			result.put("msg", "该用户不存在或密码错误！");
			result.put("code", "201");
			return result;
		}
		if (stu.getPassword().equals(teacher.getPassword())) {
			setSessionTeacher(request, stu);
			result.put("msg", "参数不合法！");
			result.put("code", "0");
			return result;
		} else {
			result.put("msg", "该用户不存在或密码错误！");
			result.put("code", "201");
			return result;
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		getSessionStudent(request);
		if (getSessionStudent(request) != null) {
			setSessionStudent(request, null);
		} else {
			setSessionTeacher(request, null);
		}
		return "/login/login";
	}

	public void setSessionStudent(HttpServletRequest request,Student student) {
		request.getSession().setAttribute(Constants.STUDENT_CONTEXT, student);
	}
	public Student getSessionStudent(HttpServletRequest request) {
		return (Student) request.getSession().getAttribute(Constants.STUDENT_CONTEXT);
	}

	public void setSessionTeacher(HttpServletRequest request,Teacher teacher) {
		request.getSession().setAttribute(Constants.TEACHER_CONTEXT, teacher);
	}
	public Teacher getSessionTeacher(HttpServletRequest request) {
		return (Teacher) request.getSession().getAttribute(Constants.TEACHER_CONTEXT);
	}
}

