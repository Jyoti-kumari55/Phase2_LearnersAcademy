package com.learnersacademy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.error.exception.ApplicationException;

import com.learnersacademy.entity.ClassObject;
import com.learnersacademy.entity.StudentObject;
import com.learnersacademy.entity.SubjectObject;
import com.learnersacademy.entity.TeacherObject;
import com.management.connection.DbConnection;

public class LearnersDao2 implements LearnersDao1 {

	
	@Override
	public boolean userLogin(String username,String password)throws ApplicationException{
		Boolean b1 = false;
		try (
			Connection con = DbConnection.getConn())
		{
			String s1 = "select 1 from learnersacademy_user where username=? and password=?";
			PreparedStatement ps = con.prepareStatement(s1);
			ps.setString(1, username);
			ps.setString(2, password);
			System.out.println(password);
			ResultSet result  = ps.executeQuery();
			if(result.next()) {
				b1= true;
				return b1;
			}
			else {
				throw new  ApplicationException("Username and Password doesn't match:");
			}		
		} catch (ClassNotFoundException e) {
			throw new  ApplicationException("Internal error "+e);
		}
		catch (SQLException e) {
			throw new ApplicationException("Internal error "+e);
		}
	}
	
	@Override
	public List<ClassObject> ClassList() throws ApplicationException {
		List<ClassObject> cList=new ArrayList<ClassObject>();
		try
		(Connection conn= DbConnection.getConn())
		{
			String s1="select classId, division, stdandard from learnersacademy_class";
			PreparedStatement  ps=conn.prepareStatement(s1);
			
			ResultSet result=ps.executeQuery();
			
			while(result.next()) {
				ClassObject cl=new ClassObject();
				
				cl.setClassId(result.getInt("classId"));
				cl.setDivision(result.getString("division"));
				cl.setStandard(result.getInt("stdandard"));
				cList.add(cl);
			}
			
			return cList;
		} catch (ClassNotFoundException e) {
			throw new ApplicationException("Internal Error "+e);
		} catch (SQLException e) {
			throw new ApplicationException("Internal Error "+e);
		}
	}
	
	@Override
	public List<TeacherObject> TeacherList() throws ApplicationException {
		List<TeacherObject> techList=new ArrayList<TeacherObject>();
		try(Connection conn=DbConnection.getConn())
		{
			String s1="select tId,teacherName,teacherCategory,experience from learnersacademy_teacher";
			PreparedStatement  ps=conn.prepareStatement(s1);
			
			ResultSet result=ps.executeQuery();
			
			while(result.next()) {
				TeacherObject teacher=new TeacherObject();
				
				teacher.settId(result.getInt("tId"));
				teacher.setTeacherCategory(result.getString("teacherCategory"));
				teacher.setTeacherName(result.getString("teacherName"));
				teacher.setExperience(result.getInt("experience"));
				
				techList.add(teacher);
					}
			
			return techList;
			
		} catch (ClassNotFoundException e) {
			throw new ApplicationException("Internal Error contact sysadmin with error message "+e);
		} catch (SQLException e) {
			throw new ApplicationException("Internal Error contact sysadmin with error message "+e);
		}
	}

	@Override
	
	public List<StudentObject> StudentList() throws ApplicationException {
		List<StudentObject> sList=new ArrayList<StudentObject>();
		try(Connection conn=DbConnection.getConn())
		{
			String s1="select studentId,studentName,studentdob,classId from learnersacademy_student";
			PreparedStatement  ps=conn.prepareStatement(s1);
			
			ResultSet result=ps.executeQuery();
			
			while(result.next()) {
				StudentObject student=new StudentObject();
				
				student.setStudentId(result.getInt("studentId"));
				student.setClassId(result.getInt("classId"));
				student.setStudentdob(result.getDate("studentdob"));
				student.setStudentName(result.getString("studentName"));
				
				sList.add(student);
			
			}
			
			return sList;
			
		} catch (ClassNotFoundException e) {
			throw new ApplicationException("Internal Error "+e);
		} catch (SQLException e) {
			throw new ApplicationException("Internal Error "+e);
		}
	}

	@Override
	public boolean deleteSubject(Integer subjectId) throws ApplicationException {
		try
		(Connection conn=DbConnection.getConn())
		{
		String s1="delete from learnersacademy_subject where subjectId=?";
		PreparedStatement ps=conn.prepareStatement(s1);
		ps.setInt(1, subjectId);
		int c=ps.executeUpdate();
		if(c!=1) {
			throw new ApplicationException("Deletion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new ApplicationException("Internal Error "+e);
		}
	}


	@Override
	public boolean deleteClass(Integer classId) throws ApplicationException {
		try(Connection conn = DbConnection.getConn())
		{
			String s1 = "{call la_update_record_pkg.del_class(?,?,?)}";
			CallableStatement cs = conn.prepareCall(s1);
			cs.setInt(1, classId);
		
			cs.registerOutParameter(3, java.sql.Types.NUMERIC);
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);

			cs.execute();
			Integer success_code=cs.getInt(3);
			String err_msg=cs.getString(2);
		if(success_code!=1) {
			throw new ApplicationException("Deletion Failed. "+err_msg);
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new ApplicationException("Internal Error "+e);
		}	
}

	@Override
	public boolean deleteTeacher(Integer teacherId) throws ApplicationException {
		try(Connection conn=DbConnection.getConn())
		{
			String s1 = "{call la_update_record_pkg.del_teacher(?,?,?)}";
			CallableStatement cs = conn.prepareCall(s1);
			cs.setInt(1, teacherId);
		
			cs.registerOutParameter(3, java.sql.Types.NUMERIC);
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);

			cs.execute();
			Integer success_code=cs.getInt(3);
			String err_msg=cs.getString(2);
			
		if(success_code!=1) {
			throw new ApplicationException("Deletion Failed ."+err_msg);
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new ApplicationException("Internal Error "+e);
		}
	}


	@Override
	public boolean deleteStudent(Integer studentId) throws ApplicationException{
		try(Connection conn=DbConnection.getConn())
		{
		String s1="delete from learnersacademy_student where studentId=?";
		PreparedStatement ps=conn.prepareStatement(s1);
		ps.setInt(1, studentId);
		int c=ps.executeUpdate();
		if(c!=1) {
			throw new  ApplicationException("Deletion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new  ApplicationException("Internal Error contact sysadmin with error message "+e);
		}	
	}
	
	@Override
	public boolean addSubject(SubjectObject subject) throws ApplicationException{
		try
		(Connection conn=DbConnection.getConn())
		{
		String s1="insert into learnersacademy_subject (subjName,subjLang) values (?,?)";
		PreparedStatement ps=conn.prepareStatement(s1);
		ps.setString(1, subject.getSubjName());
		ps.setString(2, subject.getSubjLang());
		int c=ps.executeUpdate();
		if(c!=1) {
			throw new  ApplicationException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new  ApplicationException("Internal Error contact sysadmin with error message "+e);
		}	}

	@Override
	public boolean addClass(ClassObject cls) throws ApplicationException {
		try(Connection conn=DbConnection.getConn())
		{
		String s1="Insert into learnersacademy_class (stdandard,division) values(?,?)";
		PreparedStatement ps=conn.prepareStatement(s1);
		ps.setInt(1, cls.getStandard());
		ps.setString(2, cls.getDivision());
		int c=ps.executeUpdate();
		if(c!=1) {
			throw new  ApplicationException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new  ApplicationException("Internal Error "+e);
		}	
	}
	
	

	@Override
	public boolean addTeacher(TeacherObject teacher) throws ApplicationException {
		try(Connection conn=DbConnection.getConn())
		{
		String sql="Insert into learnersacademy_teacher(teacherName,teacherCategory,experience) values(?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, teacher.getTeacherName());
		ps.setString(2, teacher.getTeacherCategory());
		ps.setInt(3, teacher.getExperience());
		int c=ps.executeUpdate();
		if(c!=1) {
			throw new  ApplicationException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new  ApplicationException("Internal Error contact system admin with error message "+e);
		}	}
	
	@Override
	public boolean addStudent(StudentObject student) throws ApplicationException {
		try(Connection conn=DbConnection.getConn())
		{
		String s1="Insert into learnersacademy_student(studentName,studentdob) values(?,?)";
		PreparedStatement ps=conn.prepareStatement(s1);
		ps.setString(1, student.getStudentName());
		ps.setDate(2, student.getStudentdob());
		int c=ps.executeUpdate();
		if(c!=1) {
			throw new ApplicationException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new  ApplicationException("Internal Error contact system admin with error message "+e);
		}	}

	@Override
	public boolean updateSubject(SubjectObject subject) throws ApplicationException {
		try (Connection conn= DbConnection.getConn()) {
			String s1 = "{call la_update_record_pkg.update_subject(?,?,?,?,?,?,?)}";
			CallableStatement cas = conn.prepareCall(s1);
			cas.setInt(2, subject.getSubjectId());
			cas.setString(1,subject.getSubjName());
		    cas.setString(3,subject.getSubjLang());
			cas.setInt(4,subject.getClassId());
			cas.setInt(5,subject.getTeacherId());

			cas.registerOutParameter(7, java.sql.Types.NUMERIC);
			cas.registerOutParameter(6, java.sql.Types.VARCHAR);

			cas.execute();

			Integer success_code=cas.getInt(7);
			String err_msg=cas.getString(6);
			
			if(success_code==1) {
				return true;
			}
			else {
				throw new  ApplicationException(err_msg);
				
			}	
		} 
		catch (ClassNotFoundException | SQLException e) {
			throw new ApplicationException("Internal error occured .. contact to system admin with error message "+e);
		}
	}
	

	@Override
	public boolean updateTeacher(TeacherObject teacher) throws ApplicationException {
		try(Connection conn=DbConnection.getConn())
		
		{
		String s1="update learnersacademy_teacher tea set tea.teacherName=nvl(?,tea.teacherName),"
				+ "tea.teacherCategory=nvl(?,tea.teacherCategory),tea.experience=nvl(?,tea.experience) " + 
				"where tea.teacherId=?";
		PreparedStatement ps=conn.prepareStatement(s1);
		ps.setString(1, teacher.getTeacherName());
		
			ps.setString(2, teacher.getTeacherCategory());
		
		ps.setInt(3, teacher.getExperience());
		ps.setInt(4, teacher.gettId());
		
		int c=ps.executeUpdate();
		if(c!=1) {
			throw new  ApplicationException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new ApplicationException("Internal Error contact to system admin with error message "+e);
		}	}
	
	@Override
	public boolean updateStudent(StudentObject student) throws ApplicationException {
		try (Connection conn = DbConnection.getConn()) {
			String s1 = "{call la_update_record_pkg.upd_student(?,?,?,?,?,?)}";
			CallableStatement cas = conn.prepareCall(s1);
			cas.setInt(1, student.getStudentId());
			cas.setString(2,student.getStudentName());
			cas.setDate(3, student.getStudentdob() );
			cas.setInt(4,student.getClassId());

			cas.registerOutParameter(6, java.sql.Types.NUMERIC);
			cas.registerOutParameter(5, java.sql.Types.VARCHAR);

			cas.execute();

			Integer success_code=cas.getInt(6);
			String err_msg=cas.getString(5);
			
			if(success_code==1) {
				return true;
			}
			else {
				throw new  ApplicationException(err_msg);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new ApplicationException("Internal error occured ...contact to system admin with error message "+e);
		}
		}

	@Override
	public List<Object> generateReport(ClassObject cl) throws ApplicationException {
		List<Object> reportList=new ArrayList<Object>();
		
		
		try(Connection conn=DbConnection.getConn())
		{
			String s1="select subjName,subjectId,subjLang,classId,teacherId from learnersacademy_subject where classId=?";
			PreparedStatement  ps=conn.prepareStatement(s1);
			ps.setInt(1, cl.getClassId());
			ResultSet resultSubject=ps.executeQuery();
			
			
			while(resultSubject.next()) {
				SubjectObject subject=new SubjectObject();
				subject.setClassId(resultSubject.getInt("classId"));
				subject.setSubjectId(resultSubject.getInt("subjectId"));
				subject.setSubjLang(resultSubject.getString("subjLang"));
				subject.setSubjName(resultSubject.getString("subjName"));
				subject.setTeacherId(resultSubject.getInt("teacherId"));
				reportList.add(subject);
			}
			
			String s2="select tId,teacherName,teacherCategory,experience from learnersacademy_teacher tea where "
					+ "tea.tId in (select ts.tId from learnersacademy_subject ts where ts.classId=?)";
			PreparedStatement  ps2=conn.prepareStatement(s2);
			ps2.setInt(1, cl.getClassId());
			ResultSet resultTeacher=ps2.executeQuery();
			
			while(resultTeacher.next()) {
				TeacherObject teacher=new TeacherObject();
				
				teacher.settId(resultTeacher.getInt("tId"));
				teacher.setTeacherCategory(resultTeacher.getString("teacherCategory"));
				teacher.setTeacherName(resultTeacher.getString("teacherName"));
				teacher.setExperience(resultTeacher.getInt("experience"));
				reportList.add(teacher);
					}
			String s3="select studentId,studentName,studentdob,classId from learnersacademy_student where classId=?";
			PreparedStatement  ps3=conn.prepareStatement(s3);
			ps3.setInt(1, cl.getClassId());
			ResultSet resultStudent=ps3.executeQuery();
			
			while(resultStudent.next()) {
				StudentObject student=new StudentObject();
				
				student.setStudentId(resultStudent.getInt("studentId"));
				student.setClassId(resultStudent.getInt("classId"));
				student.setStudentdob(resultStudent.getDate("studentdob"));
				student.setStudentName(resultStudent.getString("studentName"));
				reportList.add(student);
			
			}
			return reportList;
		} 
		catch (ClassNotFoundException e) {
			throw new  ApplicationException("Internal Error contact sysadmin with error message "+e);
		} 
		catch (SQLException e) {
			throw new  ApplicationException("Internal Error contact sysadmin with error message "+e);
		}
		
	}

	@Override
	public List<SubjectObject> SubjectList() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
}
