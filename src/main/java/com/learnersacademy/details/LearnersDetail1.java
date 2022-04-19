package com.learnersacademy.details;

import java.util.List;

import com.error.exception.ApplicationException;
import com.learnersacademy.entity.ClassObject;
import com.learnersacademy.entity.StudentObject;
import com.learnersacademy.entity.SubjectObject;
import com.learnersacademy.entity.TeacherObject;

public interface LearnersDetail1 {

	   public boolean userLogin(String username,String password)throws ApplicationException;
	    
			public List<ClassObject> ClassList() throws ApplicationException;
			public List<SubjectObject> SubjectList() throws ApplicationException;
			public List<StudentObject> StudentList() throws ApplicationException;
			public List<TeacherObject> TeacherList() throws ApplicationException;
			
			public boolean deleteClass(Integer classId) throws ApplicationException;
			public boolean deleteStudent(Integer studentId) throws ApplicationException;
			public boolean deleteSubject(Integer subjectId) throws ApplicationException;
			public boolean deleteTeacher(Integer teacherId) throws ApplicationException;
			
			public boolean addClass(ClassObject cl)throws ApplicationException;
			public boolean addStudent(StudentObject student) throws ApplicationException;
			public boolean addSubject(SubjectObject subject) throws ApplicationException;
			public boolean addTeacher(TeacherObject teacher) throws ApplicationException;
			
			public boolean updateStudent(StudentObject student) throws ApplicationException;
			public boolean updateSubject(SubjectObject subject) throws ApplicationException;
			public boolean updateTeacher(TeacherObject teacher) throws ApplicationException;
			
			public List<Object> generateReport(ClassObject cl) throws ApplicationException;
			
}
