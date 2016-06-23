package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.SectionDao;
import dao.dataAccess;
import model.Professor;
import model.Section;
import util.DBUtil;

public class ProfessorImpl {
	public List<Professor> getAllProfessors() {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Professor";
		List<Professor> professorList = new ArrayList<Professor>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String Pssn = rs.getString("Pssn");
				String title = rs.getString("title");
				String department = rs.getString("department");
				String professorName = rs.getString("professorName");
				Professor professor = new Professor();
				professor.setSsn(Pssn);
				professor.setName(professorName);
				professor.setTitle(title);
				professor.setDepartment(department);
				professorList.add(professor);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return professorList;
	}

	public Professor getProfessor(String Pssn) {
		Professor professor = new Professor();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Professor where Pssn='" + Pssn + "'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String department = rs.getString("department");
				String professorName = rs.getString("professorName");
				professor.setSsn(Pssn);
				professor.setName(professorName);
				professor.setTitle(title);
				professor.setDepartment(department);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return professor;
	}

	public List<Section> getSectionTeached(Professor professor){
		String Pssn=professor.getSsn();
		String sql="select * from Professor_Section where Pssn='"+Pssn+"'";
		List<Section> sectionTeached=new ArrayList<Section>();
		Connection conn = DBUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo=rs.getInt("sectionNo");
				SectionDao sd=dataAccess.createSectionDao();
				Section section=sd.getSection(sectionNo);
				sectionTeached.add(section);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionTeached;
	}
	
	public void addProfessor(Professor professor) {
		String Pssn = professor.getSsn();
		String title = professor.getTitle();
		String department = professor.getDepartment();
		String professorName = professor.getName();

		String sql = "insert into Professor values('" + Pssn + "','" + title + "','" + department + "','"
				+ professorName + "')";
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

	public void deleteProfessor(Professor professor) {
		String ssn = professor.getSsn();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from Professor where Pssn='" + ssn + "'";
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
	
	public void teachSection(Professor professor,Section section){
		String Pssn=professor.getSsn();
		int sectionNo=section.getSectionNo();
		String Sql="insert into Professor_Section (Pssn,sectionNo) values('"+Pssn+"','"+sectionNo+"')";
		Connection conn = DBUtil.getSqliteConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(Sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入教师—班次异常：" + e.getMessage());
		}
	}
}
