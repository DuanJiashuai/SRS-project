package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.CourseDao;
import dao.ProfessorDao;
import dao.StudentDao;
import dao.TranscriptEntryDao;
import dao.dataAccess;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import model.TranscriptEntry;
import util.DBUtil;

public class SectionImpl {
	public static List<Section> getAllSections() {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Section";
		List<Section> sectionList = new ArrayList<Section>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				String courseNo = rs.getString("courseNo");
				String Pssn = rs.getString("Pssn");
				String room=rs.getString("room");
				int seatingCapacity=rs.getInt("seatingCapacity");
				
				CourseDao ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				ProfessorDao ip = dataAccess.createProfessorDao();
				Professor professor = ip.getProfessor(Pssn);

				Section section = new Section();
				section.setDayOfWeek(dayOfWeek);
				section.setRepresentedCourse(course);
				section.setRoom(room);
				section.setSeatingCapacity(seatingCapacity);
				section.setSectionNo(sectionNo);
				section.setInstructor(professor);
				section.setTimeOfDay(timeOfDay);
				sectionList.add(section);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionList;
	}

	public Section getSection(int sectionNo) {
		Section section = new Section();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Section where sectionNo='" + sectionNo + "'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				String courseNo = rs.getString("courseNo");
				String Pssn = rs.getString("Pssn");
				
				CourseDao ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				ProfessorDao ip = dataAccess.createProfessorDao();
				Professor professor = ip.getProfessor(Pssn);
				String room=rs.getString("room");
				int seatingCapacity=rs.getInt("seatingCapacity");
				
				section.setRoom(room);
				section.setSeatingCapacity(seatingCapacity);
				section.setDayOfWeek(dayOfWeek);
				section.setRepresentedCourse(course);
				section.setRoom(room);
				section.setSectionNo(sectionNo);
				section.setInstructor(professor);
				section.setTimeOfDay(timeOfDay);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}
	
	public List<Student> getEnrolledStudents(Section section){
		int sectionNo=section.getSectionNo();
		List<Student> enrolledStudents=new ArrayList<Student>();
		String sql="select * from Student_Section where sectionNo='"+sectionNo+"'";
		Connection Conn = DBUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String Sssn=rs.getString("Sssn");
				StudentDao sd=dataAccess.createStudentDao();
				Student student=sd.getStudent(Sssn);
				enrolledStudents.add(student);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enrolledStudents;
	}

	public HashMap<Student, TranscriptEntry> getAssignedGrades(Section section){
		int sectionNo=section.getSectionNo();
		String sql="select * from TranscriptEntry where sectionNo='"+sectionNo+"'";
		HashMap<Student, TranscriptEntry> assignedGrades=new HashMap<Student, TranscriptEntry>();
		Connection Conn = DBUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String Sssn=rs.getString("Sssn");
				StudentDao sd=dataAccess.createStudentDao();
				Student student=sd.getStudent(Sssn);
				
				int transEntryNo=rs.getInt("transEntryNo");
				TranscriptEntryDao ted=dataAccess.createTranscriptEntryDao();
				TranscriptEntry transEntry=ted.getTranscriptEntry(transEntryNo);
				assignedGrades.put(student, transEntry);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assignedGrades;
	}
	
	public void addSection(Section section) {
		int sectionNo = section.getSectionNo();
		String dayOfWeek = section.getDayOfWeek();
		String timeOfDay = section.getTimeOfDay();
		String courseNo = section.getRepresentedCourse().getCourseNo();
		String pssn = section.getInstructor().getSsn();
		String room = section.getRoom();
		int seatingCapacity=section.getSeatingCapacity();
		
		String sql = "insert into Section values('" + sectionNo + "','" + dayOfWeek + "','" + timeOfDay + "','"
				+ courseNo + "','" + pssn + "','" + room + "','"+seatingCapacity+"')";
		Connection conn = DBUtil.getSqliteConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入班次异常：" + e.getMessage());
		}
	}

	public void deleteSection(Section section) {
		int sectionNo = section.getSectionNo();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from Section where sectionNo='" + sectionNo + "'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除班次异常：" + e.getMessage());
		}
	}
}
