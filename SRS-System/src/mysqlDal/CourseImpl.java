package mysqlDal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import util.DBUtil;

public class CourseImpl {
	public List<Course> getAllCourses() {
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Course";
		List<Course> courseList = new ArrayList<Course>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int courseNo = rs.getInt("courseNo");
				String courseName = rs.getString("courseName");
				int credits = rs.getInt("credits");
				Course course = new Course();
				course.setCourseNo(courseNo);
				course.setCourseName(courseName);
				course.setCredits(credits);
				courseList.add(course);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	public Course getCourse(int courseNo) {
		String sql = "select * from Course where courseNo='" + courseNo + "'";
		Connection Conn = DBUtil.getMySqlConnection();
		Course course = new Course();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				course.setCourseNo(courseNo);
				course.setCourseName(rs.getString("courseName"));
				course.setCredits(rs.getInt("credits"));
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	public void addCourse(Course course) {
		int courseNo = course.getCourseNo();
		String courseName = course.getCourseName();
		int credits = course.getCredits();

		String sql = "insert into Course values('" + courseNo + "','" + courseName + "','" + credits + "')";
		Connection conn = DBUtil.getMySqlConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入课程异常：" + e.getMessage());
		}
	}

	public void deleteCourse(Course course) {
		int courseNo = course.getCourseNo();
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "delete from Course where courseNo='" + courseNo + "'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除课程异常：" + e.getMessage());
		}
	}
}
