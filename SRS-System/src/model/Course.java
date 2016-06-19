package model;

import java.util.Collection;

public class Course {
	private int courseNo;// �γ̺�
	private String courseName;// �γ���
	private int credits;// ѧ��
	private Collection<Section> offeredAs;//���а�εļ���
	private Collection<Professor> professors;//���н��ڸÿγ̵Ľ�ʦ�ļ���
	

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
