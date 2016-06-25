package model;

public class WaitListEntry {
	private int waitListEntryId;
	private Student student;
	private Section section;

	public WaitListEntry(int wlId, Student s, Section se) {
		this.setWaitListEntryId(wlId);
		this.setStudent(student);
		this.setSection(se);
	}

	public WaitListEntry() {

	}

	public int getWaitListEntryId() {
		return waitListEntryId;
	}

	public void setWaitListEntryId(int waitListEntryId) {
		this.waitListEntryId = waitListEntryId;
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
