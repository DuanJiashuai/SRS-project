package model;

import java.util.Collection;

public class Professor extends Person {
	private String title;//ְ��
	private String department;//����
	private Collection<Section> sectionsTaught;//���а�εļ���
	private Collection<Course> coursesTaught;//���̹��Ŀγ̵ļ���
	private Collection<Student> advisees; 
	
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

	public Collection<Student> getAdvisees() {
		return advisees;
	}

	public void setAdvisees(Collection<Student> advisees) {
		this.advisees = advisees;
	}
	
	public void addAdvisee(Student s){
		if(!isInAdvisees(s)){
			advisees.add(s);
		}
	}
	
	private boolean isInAdvisees(Student s){
		boolean flag=false;
		for(Student student:advisees){
			if(student.equals(s)){
				flag=true;
				break;
			}else{
				continue;
			}
		}
		return flag;
	}
}
