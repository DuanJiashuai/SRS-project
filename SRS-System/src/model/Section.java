package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

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

	public boolean register(Student s,String semester){
		boolean completed=s.successfullyCompleted(this,semester);
		if(completed){
			//ע��ѧ��������true
			String sql = "insert into TranscriptEntry (Sssn,sectionNo) values('"+s.getSsn()+"','"+sectionNo+"')";
			Connection conn = DBUtil.getSqliteConnection();
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
				conn.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("����ɼ����쳣��" + e.getMessage());
				return false;
			}
		}else{
			//ע��ʧ�ܲ�����false
			return false;
		}
	}
}
