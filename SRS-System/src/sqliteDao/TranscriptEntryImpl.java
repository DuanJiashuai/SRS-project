package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ISection;
import dao.IStudent;
import dao.ITranscriptEntry;
import dao.dataAccess;
import model.Section;
import model.Student;
import model.TranscriptEntry;
import util.DBUtil;

public class TranscriptEntryImpl implements ITranscriptEntry{
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
				int creditsEarned = rs.getInt("creditsEarned");
				String gradeReceived = rs.getString("gradeReceived");

				IStudent is = dataAccess.createStudentDao();
				Student student = is.getStudent(Sssn);
				ISection is1 = dataAccess.createSectionDao();
				Section section = is1.getSection(sectionNo);

				TranscriptEntry transcriptEntry = new TranscriptEntry();
				transcriptEntry.setTransEntryNo(transcriptEntryNo);
				transcriptEntry.setStudent(student);
				transcriptEntry.setSection(section);
				transcriptEntry.setGradeReceived(gradeReceived);
				transcriptEntry.setCreditsEarned(creditsEarned);
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

	public TranscriptEntry getTranscriptEntry(int transcriptEntryNo) {
		String sql = "select * from TranscriptEntry where transEntryNo='" + transcriptEntryNo + "'";
		Connection Conn = DBUtil.getSqliteConnection();
		TranscriptEntry transcriptEntry = new TranscriptEntry();
		List<TranscriptEntry> transcriptEntryList = new ArrayList<TranscriptEntry>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String Sssn = rs.getString("Sssn");
				int sectionNo = rs.getInt("sectionNo");
				int creditsEarned = rs.getInt("creditsEarned");
				String gradeReceived = rs.getString("gradeReceived");

				IStudent is = dataAccess.createStudentDao();
				Student student = is.getStudent(Sssn);
				ISection is1 = dataAccess.createSectionDao();
				Section section = is1.getSection(sectionNo);

				transcriptEntry.setTransEntryNo(transcriptEntryNo);
				transcriptEntry.setStudent(student);
				transcriptEntry.setSection(section);
				transcriptEntry.setGradeReceived(gradeReceived);
				transcriptEntry.setCreditsEarned(creditsEarned);
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
		int transcriptEntryNo = transcriptEntry.getTransEntryNo();
		String Sssn = transcriptEntry.getStudent().getSsn();
		int sectionNo = transcriptEntry.getSection().getSectionNo();
		int creditsEarned = transcriptEntry.getCreditsEarned();
		String gradeReceived = transcriptEntry.getGradeReceived();

		String sql = "insert into TranscriptEntry values('" + transcriptEntryNo + "','" + Sssn + "','" + sectionNo
				+ "','" + gradeReceived + "','" + creditsEarned + "')";
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
