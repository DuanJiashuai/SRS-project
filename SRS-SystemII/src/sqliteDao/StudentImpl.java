package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import util.DBUtil;

public class StudentImpl {
	public List<Student> getAllStudents() {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Student";
		List<Student> studentList = new ArrayList<Student>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String Sssn = rs.getString("Sssn");
				String major = rs.getString("major");
				String degree = rs.getString("degree");
				String studentName = rs.getString("studentName");
				Student student = new Student();
				student.setSsn(Sssn);
				student.setName(studentName);
				student.setMajor(major);
				student.setDegree(degree);
				studentList.add(student);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	public Student getStudent(String Sssn) {
		Student student = new Student();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Student";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String major = rs.getString("major");
				String degree = rs.getString("degree");
				String studentName = rs.getString("studentName");
				student.setSsn(Sssn);
				student.setName(studentName);
				student.setMajor(major);
				student.setDegree(degree);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public void addStudent(Student student) {
		String Sssn = student.getSsn();
		String major = student.getMajor();
		String degree = student.getDegree();
		String studentName = student.getName();

		String sql = "insert into Student values('" + Sssn + "','" + studentName + "','" + major + "','" + degree
				+ "')";
		Connection conn = DBUtil.getSqliteConnection();
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

	public void deleteStudent(Student student) {
		String Sssn = student.getSsn();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from Student where Sssn='" + Sssn + "'";
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
