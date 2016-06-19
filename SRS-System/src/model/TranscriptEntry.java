package model;

public class TranscriptEntry {
	private int transEntryNo;
	private Student student;
	private Section section;
	private String gradeReceived;
	private int creditsEarned;

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getGradeReceived() {
		return gradeReceived;
	}

	public void setGradeReceived(String gradeReceived) {
		this.gradeReceived = gradeReceived;
	}

	public int getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(int creditsEarned) {
		this.creditsEarned = creditsEarned;
	}

	public void countCreditsEarned(String gradeReceived) {
		int cEarned = 0;
		switch (gradeReceived) {
		case "A":
			cEarned = section.getRepresents().getCredits() * 4;
		case "B":
			cEarned = section.getRepresents().getCredits() * 3;
		case "C":
			cEarned = section.getRepresents().getCredits() * 2;
		case "D":
			cEarned = section.getRepresents().getCredits() * 1;
		default:
			cEarned = 0;
		}
		this.creditsEarned = cEarned;
	}

	public int getTransEntryNo() {
		return transEntryNo;
	}

	public void setTransEntryNo(int transEntryNo) {
		this.transEntryNo = transEntryNo;
	}

}
