package com.learnersacademy.entity;

public class SubjectObject {
	
private Integer subjectId;	
private Integer teacherId;
private String subjName;
private String subjLang;
private Integer classId;

public SubjectObject() {
}

public Integer getSubjectId() {
	return subjectId;
}

public void setSubjectId(Integer subjectId) {
	this.subjectId = subjectId;
}

public Integer getTeacherId() {
	return teacherId;
}

public void setTeacherId(Integer teacherId) {
	this.teacherId = teacherId;
}

public String getSubjName() {
	return subjName;
}

public void setSubjName(String subjName) {
	this.subjName = subjName;
}

public String getSubjLang() {
	return subjLang;
}

public void setSubjLang(String subjLang) {
	this.subjLang = subjLang;
}

public Integer getClassId() {
	return classId;
}

public void setClassId(Integer classId) {
	this.classId = classId;
}

@Override
public String toString() {
	return "SubjectObject [teacherId=" + teacherId + ", subjName=" + subjName + ", subjLang=" + subjLang + ", classId="
			+ classId + "]";
   }
}
