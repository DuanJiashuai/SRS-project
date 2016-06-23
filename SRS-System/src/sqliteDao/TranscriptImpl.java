package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.SectionDao;
import dao.TranscriptDao;
import dao.dataAccess;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import util.DBUtil;

public class TranscriptImpl implements TranscriptDao {
	public Transcript getTranscriptByStudent(Student student) {
		String Sssn=student.getSsn();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from TranscriptEntry where Sssn='"+Sssn+"'";
		Transcript t=new Transcript();
		t.setStudentOwner(student);
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TranscriptEntry te=new TranscriptEntry();
				
				int transEntryNo=rs.getInt("transEntryNo");
				int sectionNo=rs.getInt("sectionNo");
				SectionDao sd=dataAccess.createSectionDao();
				Section section=sd.getSection(sectionNo);
				String gradeReceived=rs.getString("gradeReceived");
				te.setTransEntryNo(transEntryNo);
				te.setStudent(student);
				te.setSection(section);
				te.setGrade(gradeReceived);
				te.setTranscript(t);
				
				t.addTranscriptEntry(te);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
