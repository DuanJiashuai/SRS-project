package mysqlDal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ICourse;
import dao.dataAccess;
import model.Course;
import model.ScheduleOfClasses;
import model.Student;
import util.DBUtil;

public class ScheduleOfClassesImpl {
	public List<ScheduleOfClasses> getAllScheduleOfClassess(Student student) {
		Connection Conn = DBUtil.getMySqlConnection();
		String Sssn = student.getSsn();
		String sql = "select distinct semester from ScheduleOfClasses where Sssn='" + Sssn + "'";
		List<ScheduleOfClasses> scheduleList = new ArrayList<ScheduleOfClasses>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String semester = rs.getString("semester");
				String sql1 = "select CourseNo from ScheduleOfClasses where Sssn='" + Sssn + "' and semester='"
						+ semester + "'";
				ScheduleOfClasses schedule = new ScheduleOfClasses();
				try {
					PreparedStatement pstmt1 = Conn.prepareStatement(sql1);
					ResultSet rs1 = pstmt1.executeQuery();
					Collection<Course> courses = null;
					while (rs1.next()) {
						int courseNo = rs1.getInt("CourseNo");
						ICourse ic = dataAccess.createCourseDao();
						Course course = ic.getCourse(courseNo);
						courses.add(course);
					}
					schedule.setCourses(courses);
					rs1.close();
					pstmt1.close();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				schedule.setStudent(student);
				schedule.setSemester(semester);
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

	public ScheduleOfClasses getScheduleOfClasses(String semester, Student student) {
		String Sssn = student.getSsn();
		String sql = "select * from ScheduleOfClasses where Sssn='" + Sssn + "' and semester='" + semester + "'";
		Connection Conn = DBUtil.getMySqlConnection();
		ScheduleOfClasses schedule = new ScheduleOfClasses();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Collection<Course> courses = null;
			while (rs.next()) {
				int courseNo = rs.getInt("CourseNo");
				ICourse ic = dataAccess.createCourseDao();
				Course course = ic.getCourse(courseNo);
				courses.add(course);
			}
			schedule.setCourses(courses);
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		schedule.setStudent(student);
		schedule.setSemester(semester);
		return schedule;
	}

	public void addScheduleOfClasses(ScheduleOfClasses schedule) {
		Collection<Course> courses = schedule.getCourses();
		String semester = schedule.getSemester();
		String Sssn = schedule.getStudent().getSsn();
		for (Course course : courses) {
			int courseNo = course.getCourseNo();
			String sql = "insert into ScheduleOfClasses values('" + courseNo + "','" + semester + "','" + Sssn + "')";
			Connection conn = DBUtil.getMySqlConnection();
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
	}

	public void deleteScheduleOfClasses(ScheduleOfClasses schedule) {
		String Sssn = schedule.getStudent().getSsn();
		String semester = schedule.getSemester();
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "delete from ScheduleOfClasses where Sssn='" + Sssn + "' and semester='" + semester + "'";
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
