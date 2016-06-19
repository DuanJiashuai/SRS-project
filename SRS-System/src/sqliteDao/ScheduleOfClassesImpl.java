package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ICourse;
import dao.IScheduleOfClasses;
import dao.dataAccess;
import model.Course;
import model.ScheduleOfClasses;
import util.DBUtil;

public class ScheduleOfClassesImpl implements IScheduleOfClasses {
	public List<ScheduleOfClasses> getAllScheduleOfClassess() {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from ScheduleOfClasses";
		List<ScheduleOfClasses> scheduleList = new ArrayList<ScheduleOfClasses>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String semester = rs.getString("semester");
				int courseNo = rs.getInt("CourseNo");
				ICourse ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				ScheduleOfClasses schedule = new ScheduleOfClasses();
				schedule.setSemester(semester);
				schedule.setCourse(course);
				scheduleList.add(schedule);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleList;
	}

	public ScheduleOfClasses getScheduleOfClassesBySemester(String semester) {
		String sql = "select * from ScheduleOfClasses where semester='" + semester + "'";
		Connection Conn = DBUtil.getSqliteConnection();
		ScheduleOfClasses schedule = new ScheduleOfClasses();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				schedule.setSemester(semester);
				int courseNo = rs.getInt("CourseNo");
				ICourse ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				schedule.setCourse(course);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedule;
	}

	public ScheduleOfClasses getScheduleOfClassesByCourseNo(int courseNo) {
		String sql = "select * from ScheduleOfClasses where CourseNo='" + courseNo + "'";
		Connection Conn = DBUtil.getSqliteConnection();
		ScheduleOfClasses schedule = new ScheduleOfClasses();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				schedule.setSemester(rs.getString("semester"));
				ICourse ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				schedule.setCourse(course);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedule;
	}

	public void addScheduleOfClasses(ScheduleOfClasses schedule) {
		Course course = schedule.getCourse();
		int courseNo = course.getCourseNo();
		String semester = schedule.getSemester();

		String sql = "insert into ScheduleOfClasses values('" + courseNo + "','" + semester + "')";
		Connection conn = DBUtil.getSqliteConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入课程计划异常：" + e.getMessage());
		}
	}

	public void deleteScheduleOfClasses(ScheduleOfClasses schedule) {
		Course course = schedule.getCourse();
		int courseNo = course.getCourseNo();
		String semester = schedule.getSemester();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from ScheduleOfClasses where courseNo='" + courseNo + "' and semester='" + semester + "'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除课程计划异常：" + e.getMessage());
		}
	}
}
