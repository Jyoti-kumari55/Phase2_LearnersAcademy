package com.learnersacademy.entity;

public class ClassObject {
	private Integer classId;
	private Integer standard;
	private String division;
	
	 public ClassObject() {
		 
	 }
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getStandard() {
		return standard;
	}
	public void setStandard(Integer standard) {
		this.standard = standard;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	@Override
	public String toString() {
		return "ClassObject [classId=" + classId + ", standard=" + standard + ", division=" + division + "]";
	}

}
