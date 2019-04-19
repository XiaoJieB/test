package com.luobo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * . Description: Date: 2019/3/11 10:30
 *
 * @author: ws
 * @version: 1.0
 */
@Entity
@Table(name = "teacher")
public class Teacher {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "created")
	private Long created = System.currentTimeMillis();

	private String name;

	@Column(name = "phone")
	private String phone;

	private String number;

	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinTable(name = "teachers_students", joinColumns = {
		@JoinColumn(name = "teacher_id") }
		, inverseJoinColumns = { @JoinColumn(name = "student_id") })
	private Set<Student> students;

	private String password;

	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private List<BigWork> bigWorks = new ArrayList<BigWork>();//一个课题对应一份大作业

	public List<BigWork> getBigWorks() {
		return bigWorks;
	}

	public void setBigWorks(List<BigWork> bigWorks) {
		this.bigWorks = bigWorks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
