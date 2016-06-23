package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.StudentDao;
import dao.StudentWithPasswordDao;
import dao.dataAccess;
import model.Student;
import model.StudentWithPassword;
import util.DBUtil;

public class StudentWithPasswordImpl implements StudentWithPasswordDao {
	public StudentWithPassword getStudentWithPassword(String Sssn) {
		StudentWithPassword sp = new StudentWithPassword();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Student";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String pwd=rs.getString("password");
				StudentDao sd=dataAccess.createStudentDao();
				Student student=sd.getStudent(Sssn);
				sp=new StudentWithPassword(student,pwd);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
	}
}
