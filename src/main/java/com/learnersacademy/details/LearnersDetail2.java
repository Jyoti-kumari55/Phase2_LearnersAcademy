package com.learnersacademy.details;

import java.util.List;

import com.error.exception.ApplicationException;

import com.learnersacademy.dao.LearnersDao2;
import com.learnersacademy.entity.ClassObject;
import com.learnersacademy.entity.StudentObject;
import com.learnersacademy.entity.SubjectObject;
import com.learnersacademy.entity.TeacherObject;

public class LearnersDetail2 implements LearnersDetail1 {

	@Override
	public boolean userLogin(String username,String password)throws ApplicationException{
		boolean b1 = false;
		
		if(!username.isEmpty() && !password.isEmpty()) {
			if(username.matches("[A-Za-z0-9]") && password.matches("[A-Za-z0-9]")) {
				LearnersDao2 dao2=new LearnersDao2();
				dao2.userLogin(username, password);
				b1 = true;
				return b1;
				}
			else {
				throw new ApplicationException("Invalid Credentials");
			}
		}
			else {
				throw new ApplicationException("Username or Password cannot be null");
			}
	}
	
		@Override
		public List<ClassObject> ClassList()throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			List<ClassObject> cList = dao2.ClassList();
			return cList;
		
		}
		@Override
		public List<StudentObject> StudentList()throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			List<StudentObject> sList = dao2.StudentList();
			return sList;
	}
		@Override
		public List<SubjectObject> SubjectList()throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			List<SubjectObject> subList = dao2.SubjectList();
			return subList;
	}
		@Override
		public List<TeacherObject> TeacherList()throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			List<TeacherObject> techList = dao2.TeacherList();
			return techList;
    }
		@Override
		public boolean deleteClass(Integer classId)throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			return dao2.deleteClass(classId);
		}
		@Override
		public boolean deleteStudent(Integer studentId)throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			return dao2.deleteStudent(studentId);
		
		}
		@Override
		public boolean deleteSubject(Integer subjectId)throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			return dao2.deleteSubject(subjectId);
		}
		@Override
		public boolean deleteTeacher(Integer teacherId)throws ApplicationException{
			LearnersDao2 dao2=new LearnersDao2();
			return dao2.deleteTeacher(teacherId);
		}
		//******************************Subject Added********************************************//
		@Override
		public boolean addSubject(SubjectObject subject)throws ApplicationException{
			if(subject.getSubjName().isEmpty()||subject.getSubjLang().isEmpty()) {
				throw new ApplicationException("Subject name or language cannot be Empty!");
			}
			else {
				if(subject.getSubjName().matches("^[A-Za-z\\S]+$")){
					if(subject.getSubjLang().matches("^[A-Za-z]+$")) {
						LearnersDao2 dao2=new LearnersDao2();
						return dao2.addSubject(subject);
					}
					else {
						throw new ApplicationException("Subject language have only alphabets, No white Space allowed:");
					}
					
				}
					else {
						throw new ApplicationException("Subject name have only alphabets and Space:");
					}
				}
					
				}
		//*******************New Class Added***************************//
		@Override
		public boolean addClass(ClassObject cl)throws ApplicationException{
			if(cl.getStandard()==null|| cl.getDivision().isEmpty()) {
				throw new ApplicationException("Subject name or language cannot be Empty!");
			}
			else {
				if(cl.getStandard()>0||cl.getStandard()<12){
					if(cl.getDivision().matches("^[A-E]{1}")) {
						LearnersDao2 dao2=new LearnersDao2();
						return dao2.addClass(cl);
					}
					else {
						throw new ApplicationException("Choose Your Class Division from A, B, C, D, E :");
					}}
					else {
						throw new ApplicationException("Standard should be between 1 and 12:");	
			}

		}
		
	}
		//*****************************New Student Added*********************************************//
		
		public boolean addStudent(StudentObject student)throws ApplicationException{
			if(student.getStudentName().isEmpty()||student.getStudentdob()==null){
				throw new ApplicationException("Student name and dob cannot be Empty!");
			}
			else {
				if(student.getStudentName().matches("^[A-Za-z\\S]+$")){
					LearnersDao2 dao2=new LearnersDao2();
						return dao2.addStudent(student);
				}
				else {
						throw new ApplicationException("Student name only have alphabets and space:");
					}
			  }
					
		}
		
		//****************************************New Teacher Added******************************************//
		@Override
		public boolean addTeacher(TeacherObject teacher)throws ApplicationException{
			if(teacher.getTeacherName().isEmpty()||teacher.getTeacherCategory().isEmpty()||teacher.getExperience()==null){
				throw new ApplicationException("Teacher name,Category or Experience cannot be empty:");
			}
			else {
			
					if(teacher.getTeacherName().matches("^[A-Za-z\\s]+$")) {
						if(teacher.getTeacherCategory().matches("^[A-Za-z]+$")) {
						LearnersDao2 dao2=new LearnersDao2();
						return dao2.addTeacher(teacher);
					}
					else {
						throw new ApplicationException("Teacher Category should have only alphabets and no spaces:");
					}
					}
					else {
						throw new ApplicationException("Teacher name should have only alphabets and spaces:");	
			}
		}
}	
		//**********************************UPDATE STUDENT**************************************//
		@Override
				public boolean updateStudent(StudentObject student)throws ApplicationException{
					if(student.getStudentName().isEmpty() && student.getStudentdob()==null && student.getClass()==null) {

						throw new ApplicationException("Student name,dob or classId cannot be empty:");
					}
					else {
					
							if(student.getStudentName().matches("^[A-Za-z\\s]+$") || student.getStudentName().isEmpty()) {
						
								LearnersDao2 dao2=new LearnersDao2();
								return dao2.addStudent(student);
							}
							else {
								throw new ApplicationException("Student name should have only alphabets and no spaces allowed:");
							
					}
				}
		}	
		
		//****************************UPDATE TEACHER*******************************//
	@Override
		public boolean updateTeacher(TeacherObject teacher)throws ApplicationException{
			if(teacher.getTeacherName().isEmpty() && teacher.getTeacherCategory().isEmpty() && teacher.getExperience()==null){
				throw new ApplicationException("Teacher name,Category or Experience cannot be empty:");
			}
			else {
			
					if(teacher.getTeacherName().matches("^[A-Za-z\\s]+$") || teacher.getTeacherName().isEmpty()) {
						if(teacher.getTeacherCategory().matches("^[A-Za-z]+$") || teacher.getTeacherCategory().isEmpty()) {
						LearnersDao2 dao2=new LearnersDao2();
						return dao2.addTeacher(teacher);
					}
					else {
						throw new ApplicationException("Teacher Category should have only alphabets and no spaces allowed:");
					}
					}
					else {
						throw new ApplicationException("Teacher name should have only alphabets and spaces:");	
			}
		}
}	
		//**********************************UPDATE TEACHER**************************************//
		
				public boolean updateSubject(SubjectObject subject)throws ApplicationException{
					if(subject.getSubjName().matches("^[A-Za-z\\S]+$")|| subject.getSubjName().isEmpty()) {
						if (subject.getSubjLang().matches("^[A-Za-z]+$")|| subject.getSubjLang().isEmpty()) {

							LearnersDao2 dao2=new LearnersDao2();
							return dao2.addSubject(subject);
						}
			
					else {
					
				     		throw new ApplicationException("Subject language name should have only alphabets and no spaces allowed:");
							
					}
				}
					else {
						throw new ApplicationException("Subject name should have only alphabets and spaces:");
					}
			}	
	
      
        @Override
     public List<Object> generateReport(ClassObject cl)throws ApplicationException{
        	
			LearnersDao2 dao2=new LearnersDao2();
			List<Object> reportList = dao2.generateReport(cl);
			return reportList;
			
		}
}
