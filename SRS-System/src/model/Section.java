package model;

public class Section {
	private int sectionNo;
	private String dayOfWeek;
	private String timeOfDay;
	private String semester;
	private Room room;
	
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
}
