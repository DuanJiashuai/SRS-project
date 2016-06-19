package model;

import java.util.Collection;

public class Course {
	private int courseNo;// 课程号
	private String courseName;// 课程名
	private int credits;// 学分
	private Collection<Section> offeredAs;//所有班次的集合
	private Collection<Professor> professors;//所有教授该课程的教师的集合
	

	public int getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Collection<Section> getOfferedAs() {
		return offeredAs;
	}

	public void setOfferedAs(Collection<Section> offeredAs) {
		this.offeredAs = offeredAs;
	}

	public Collection<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(Collection<Professor> professors) {
		this.professors = professors;
	}

}
