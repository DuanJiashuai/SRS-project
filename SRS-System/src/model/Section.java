package model;

public class Section {
	private int sectionNo;// ��κ�
	private String dayOfWeek;// ����
	private String timeOfDay;// ʱ��
	private Room room;// ����
	private Course represents;//�γ�
	private Professor taughtBy;//�ον�ʦ
	private int studentsRegistered;
	
	public int getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(int sectionNo) {
		this.sectionNo = sectionNo;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Course getRepresents() {
		return represents;
	}

	public void setRepresents(Course represents) {
		this.represents = represents;
	}

	public Professor getTaughtBy() {
		return taughtBy;
	}

	public void setTaughtBy(Professor taughtBy) {
		this.taughtBy = taughtBy;
	}

	public int getStudentsRegistered() {
		return studentsRegistered;
	}

	public void setStudentsRegistered(int studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}

}
