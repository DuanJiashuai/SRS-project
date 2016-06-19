package model;

import java.util.Collection;

import dao.IScheduleOfClasses;
import dao.dataAccess;

public class Student extends Person {
	private String major;//专业
	private String degree;//学历
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public boolean successfullyCompleted(Section s,String semester){
		int seatingCapacity=s.getRoom().getSeatingCapacity();
		int studentsRegistered=s.getStudentsRegistered();
		Course course=s.getRepresents();
		boolean flag=false;
		if(studentsRegistered<seatingCapacity){//判断该班次是否有余量
			IScheduleOfClasses isc=dataAccess.createScheduleOfClassesDao();
			ScheduleOfClasses sc=isc.getScheduleOfClasses(semester, this);
			Collection<Course> courses=sc.getCourses();
			for(Course c:courses){//判断该课程是否在该学生的课程计划中
				if(c.equals(course)){
					flag=true;
					break;
				}else{
					continue;
				}
			}
		}else{
			flag=false;
		}
		return flag;
	}
}
