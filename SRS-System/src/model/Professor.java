package model;

import java.util.Collection;

public class Professor extends Person {
	private String title;//职称
	private String department;//部门
	private Collection<Section> sectionsTaught;//所有班次的集合
	private Collection<Course> coursesTaught;//曾教过的课程的集合
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Collection<Section> getSectionsTaught() {
		return sectionsTaught;
	}

	public void setSectionsTaught(Collection<Section> sectionsTaught) {
		this.sectionsTaught = sectionsTaught;
	}

	public Collection<Course> getCoursesTaught() {
		return coursesTaught;
	}

	public void setCoursesTaught(Collection<Course> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}
}
