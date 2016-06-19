package mysqlDal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ICourse;
import dao.IProfessor;
import dao.IRoom;
import dao.dataAccess;
import model.Course;
import model.Professor;
import model.Room;
import model.Section;
import util.DBUtil;

public class SectionImpl {
	public List<Section> getAllSections() {
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Section";
		List<Section> sectionList = new ArrayList<Section>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				int courseNo = rs.getInt("courseNo");
				String Pssn = rs.getString("Pssn");
				int roomNo = rs.getInt("roomNo");
				ICourse ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				IProfessor ip = dataAccess.createProfessorDao();
				Professor professor = ip.getProfessor(Pssn);
				IRoom ir = dataAccess.createRoomDao();
				Room room = ir.getRoom(roomNo);

				Section section = new Section();
				section.setDayOfWeek(dayOfWeek);
				section.setRepresents(course);
				section.setRoom(room);
				section.setSectionNo(sectionNo);
				section.setTaughtBy(professor);
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
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Section where sectionNo='" + sectionNo + "'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				int courseNo = rs.getInt("courseNo");
				String Pssn = rs.getString("Pssn");
				int roomNo = rs.getInt("roomNo");
				ICourse ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				IProfessor ip = dataAccess.createProfessorDao();
				Professor professor = ip.getProfessor(Pssn);
				IRoom ir = dataAccess.createRoomDao();
				Room room = ir.getRoom(roomNo);

				section.setDayOfWeek(dayOfWeek);
				section.setRepresents(course);
				section.setRoom(room);
				section.setSectionNo(sectionNo);
				section.setTaughtBy(professor);
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

	public void addSection(Section section) {
		int sectionNo = section.getSectionNo();
		String dayOfWeek = section.getDayOfWeek();
		String timeOfDay = section.getTimeOfDay();
		int courseNo = section.getRepresents().getCourseNo();
		String pssn = section.getTaughtBy().getSsn();
		int roomNo = section.getRoom().getRoomNo();

		String sql = "insert into Section values('" + sectionNo + "','" + dayOfWeek + "','" + timeOfDay + "','"
				+ courseNo + "','" + pssn + "','" + roomNo + "')";
		Connection conn = DBUtil.getMySqlConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入教师异常：" + e.getMessage());
		}
	}

	public void deleteSection(Section section) {
		int sectionNo = section.getSectionNo();
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "delete from Section where sectionNo='" + sectionNo + "'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除教师异常：" + e.getMessage());
		}
	}
}
