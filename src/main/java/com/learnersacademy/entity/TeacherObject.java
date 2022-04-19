package com.learnersacademy.entity;

public class TeacherObject {
	private Integer tId;
	private String teacherName;
	private String teacherCategory;
	private Integer experience;

	public TeacherObject() {
}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherCategory() {
		return teacherCategory;
	}

	public void setTeacherCategory(String teacherCategory) {
		this.teacherCategory = teacherCategory;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "TeacherObject [tId=" + tId + ", teacherName=" + teacherName + ", teacherCategory=" + teacherCategory
				+ ", experience=" + experience + "]";
	}
	
}
