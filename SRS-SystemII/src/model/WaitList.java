package model;

public class WaitList {
	private int waitListId;
	private Student student;
	private Section section;

	public int getWaitListId() {
		return waitListId;
	}

	public void setWaitListId(int waitListId) {
		this.waitListId = waitListId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}
