package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.SectionDao;
import dao.StudentDao;
import dao.TranscriptDao;
import dao.dataAccess;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import util.DBUtil;

public class TranscriptEntryImpl {
	public List<TranscriptEntry> getAllTranscriptEntrys() {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from TranscriptEntry";
		List<TranscriptEntry> transcriptEntryList = new ArrayList<TranscriptEntry>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int transcriptEntryNo = rs.getInt("transEntryNo");
				String Sssn = rs.getString("Sssn");
				int sectionNo = rs.getInt("sectionNo");
				String gradeReceived = rs.getString("gradeReceived");

				StudentDao is = dataAccess.createStudentDao();
				Student student = is.getStudent(Sssn);
				SectionDao is1 = dataAccess.createSectionDao();
				Section section = is1.getSection(sectionNo);

				TranscriptEntry transcriptEntry = new TranscriptEntry();
				transcriptEntry.setTransEntryNo(transcriptEntryNo);
				transcriptEntry.setStudent(student);
				transcriptEntry.setSection(section);
				transcriptEntry.setGrade(gradeReceived);
				transcriptEntryList.add(transcriptEntry);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transcriptEntryList;
	}

	public TranscriptEntry getTranscriptEntry(int transEntryNo) {
		String sql = "select * from TranscriptEntry where transEntryNo='" + transEntryNo + "'";
		Connection Conn = DBUtil.getSqliteConnection();
		TranscriptEntry transcriptEntry = new TranscriptEntry();
		List<TranscriptEntry> transcriptEntryList = new ArrayList<TranscriptEntry>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String Sssn=rs.getString("Sssn");
				int sectionNo = rs.getInt("sectionNo");
				String gradeReceived = rs.getString("gradeReceived");
				
				StudentDao is = dataAccess.createStudentDao();
				Student student = is.getStudent(Sssn);
				TranscriptDao td=dataAccess.createTranscriptDao();
				Transcript transcript=td.getTranscriptByStudent(student);
				SectionDao is1 = dataAccess.createSectionDao();
				Section section = is1.getSection(sectionNo);
				
				transcriptEntry.setTransEntryNo(transEntryNo);
				transcriptEntry.setStudent(student);
				transcriptEntry.setSection(section);
				transcriptEntry.setGrade(gradeReceived);
				transcriptEntry.setTranscript(transcript);
				transcriptEntryList.add(transcriptEntry);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transcriptEntry;
	}

	public void addTranscriptEntry(TranscriptEntry transcriptEntry) {
		String Sssn = transcriptEntry.getStudent().getSsn();
		int sectionNo = transcriptEntry.getSection().getSectionNo();
		String gradeReceived = transcriptEntry.getGrade();

		String sql = "insert into TranscriptEntry (Sssn,sectionNo,gradeReceived) values('" + Sssn + "','" + sectionNo+ "','" + gradeReceived + "')";
		Connection conn = DBUtil.getSqliteConnection();
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

	public void deleteTranscriptEntry(TranscriptEntry transcriptEntry) {
		int transcriptEntryNo = transcriptEntry.getTransEntryNo();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from TranscriptEntry where transEntryNo='" + transcriptEntryNo + "'";
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
