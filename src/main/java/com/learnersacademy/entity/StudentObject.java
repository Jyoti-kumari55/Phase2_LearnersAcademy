package com.learnersacademy.entity;

import java.util.Date;

public class StudentObject {
	private Integer studentId;
	private String studentName;
	private Date studentdob;
	private Integer classId;

	public StudentObject() {
		// TODO Auto-generated constructor stub
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public java.sql.Date getStudentdob() {
		return (java.sql.Date) studentdob;
	}

	public void setStudentdob(Date studentdob) {
		this.studentdob = studentdob;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "StudentObject [studentId=" + studentId + ", studentName=" + studentName + ", studentdob=" + studentdob
				+ ", classId=" + classId + "]";
	}
}
