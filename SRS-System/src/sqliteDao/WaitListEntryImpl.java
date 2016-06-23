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
	public List<WaitListEntry> getAllWaitListEntrys(){
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from WaitListEntry";
		List<WaitListEntry> WaitListEntryList = new ArrayList<WaitListEntry>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int WaitListEntryId=rs.getInt("WaitListEntryId");
				String Sssn=rs.getString("Sssn");
				int sectionNo=rs.getInt("sectionNo");
				
				StudentDao is=dataAccess.createStudentDao();
				Student student=is.getStudent(Sssn);
				SectionDao is1=dataAccess.createSectionDao();
				Section section=is1.getSection(sectionNo);
				
				WaitListEntry WaitListEntry=new WaitListEntry();
				WaitListEntry.setWaitListEntryId(WaitListEntryId);
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
	
	public WaitListEntry getWaitListEntry(int WaitListEntryId){
		String sql = "select * from WaitListEntry where WaitListEntryId='"+WaitListEntryId+"'";
		Connection Conn = DBUtil.getSqliteConnection();
		WaitListEntry WaitListEntry=new WaitListEntry();
		List<WaitListEntry> WaitListEntryList = new ArrayList<WaitListEntry>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String Sssn=rs.getString("Sssn");
				int sectionNo=rs.getInt("sectionNo");
				
				StudentDao is=dataAccess.createStudentDao();
				Student student=is.getStudent(Sssn);
				SectionDao is1=dataAccess.createSectionDao();
				Section section=is1.getSection(sectionNo);
				
				WaitListEntry.setWaitListEntryId(WaitListEntryId);
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
		return WaitListEntry;
	}
	
	public void addWaitListEntry(WaitListEntry WaitListEntry){
		int WaitListEntryId=WaitListEntry.getWaitListEntryId();
		String Sssn=WaitListEntry.getStudent().getSsn();
		int sectionNo=WaitListEntry.getSection().getSectionNo();
		
		String sql="insert into WaitListEntry values('"+WaitListEntryId+"','"+Sssn+"','"+sectionNo+"','"+sectionNo+"')";
		Connection conn = DBUtil.getSqliteConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入课程异常："+e.getMessage());
		}
	}
	
	public void deleteWaitListEntry(WaitListEntry WaitListEntry){
		int WaitListEntryId=WaitListEntry.getWaitListEntryId();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from WaitListEntry where WaitListEntryId='"+WaitListEntryId+"'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除课程异常："+e.getMessage());
		}
	}
}
