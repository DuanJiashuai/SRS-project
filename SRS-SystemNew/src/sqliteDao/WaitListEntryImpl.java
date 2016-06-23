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
import dao.WaitListEntryDao;
import dao.dataAccess;
import model.Section;
import model.Student;
import model.WaitListEntry;
import util.DBUtil;

public class WaitListEntryImpl implements WaitListEntryDao {
	public List<WaitListEntry> getAllWaitListEntrysBySection(Section section){
		int sectionNo=section.getSectionNo();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from WaitListEntry where sectionNo="+sectionNo;
		List<WaitListEntry> WaitListEntryList = new ArrayList<WaitListEntry>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int waitListEntryId=rs.getInt("waitListEntryId");
				String Sssn=rs.getString("Sssn");
				
				StudentDao std=dataAccess.createStudentDao();
				Student student=std.getStudent(Sssn);
				
				WaitListEntry WaitListEntry=new WaitListEntry();
				WaitListEntry.setWaitListEntryId(waitListEntryId);
				WaitListEntry.setStudent(student);
				WaitListEntry.setSection(section);
				WaitListEntryList.add(WaitListEntry);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return WaitListEntryList;
	}
	
	public List<WaitListEntry> getAllWaitListEntrysByStudent(Student student){
		String Sssn=student.getSsn();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from WaitListEntry where Sssn="+Sssn;
		List<WaitListEntry> WaitListEntryList = new ArrayList<WaitListEntry>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int waitListEntryId=rs.getInt("waitListEntryId");
				int sectionNo=rs.getInt("sectionNo");
				
				SectionDao sd=dataAccess.createSectionDao();
				Section section=sd.getSection(sectionNo);
				
				WaitListEntry WaitListEntry=new WaitListEntry();
				WaitListEntry.setWaitListEntryId(waitListEntryId);
				WaitListEntry.setStudent(student);
				WaitListEntry.setSection(section);
				WaitListEntryList.add(WaitListEntry);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return WaitListEntryList;
	}
	
	public WaitListEntry getWaitListEntry(Student student,Section section){
		String Sssn=student.getSsn();
		int sectionNo=section.getSectionNo();
		String sql = "select * from WaitListEntry where Sssn='"+Sssn+"' and sectionNo="+sectionNo;
		Connection Conn = DBUtil.getSqliteConnection();
		WaitListEntry WaitListEntry=new WaitListEntry();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int waitListEntryId=rs.getInt("waitListEntryId");
				
				WaitListEntry.setWaitListEntryId(waitListEntryId);
				WaitListEntry.setStudent(student);
				WaitListEntry.setSection(section);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return WaitListEntry;
	}
	
	public void addWaitListEntry(WaitListEntry waitListEntry){
		int waitListEntryId=waitListEntry.getWaitListEntryId();
		String Sssn=waitListEntry.getStudent().getSsn();
		int sectionNo=waitListEntry.getSection().getSectionNo();
		
		String sql="insert into WaitListEntry values('"+waitListEntryId+"','"+Sssn+"','"+sectionNo+"')";
		Connection conn = DBUtil.getSqliteConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入等待队列异常："+e.getMessage());
		}
	}
	
	public void deleteWaitListEntry(WaitListEntry waitListEntry){
		int waitListEntryId=waitListEntry.getWaitListEntryId();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from WaitListEntry where waitListEntryId='"+waitListEntryId+"'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除等待队列异常："+e.getMessage());
		}
	}
}
