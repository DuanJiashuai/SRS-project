package mysqlDal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ISection;
import dao.IStudent;
import dao.dataAccess;
import model.Section;
import model.Student;
import model.WaitList;
import util.DBUtil;

public class WaitListImpl {
	public List<WaitList> getAllWaitLists(){
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from WaitList";
		List<WaitList> waitListList = new ArrayList<WaitList>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int waitListId=rs.getInt("waitListId");
				String Sssn=rs.getString("Sssn");
				int sectionNo=rs.getInt("sectionNo");
				
				IStudent is=dataAccess.createStudentDao();
				Student student=is.getStudent(Sssn);
				ISection is1=dataAccess.createSectionDao();
				Section section=is1.getSection(sectionNo);
				
				WaitList waitList=new WaitList();
				waitList.setWaitListId(waitListId);
				waitList.setStudent(student);
				waitList.setSection(section);
				waitListList.add(waitList);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return waitListList;
	}
	
	public WaitList getWaitList(int waitListId){
		String sql = "select * from WaitList where waitListId='"+waitListId+"'";
		Connection Conn = DBUtil.getMySqlConnection();
		WaitList waitList=new WaitList();
		List<WaitList> waitListList = new ArrayList<WaitList>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String Sssn=rs.getString("Sssn");
				int sectionNo=rs.getInt("sectionNo");
				
				IStudent is=dataAccess.createStudentDao();
				Student student=is.getStudent(Sssn);
				ISection is1=dataAccess.createSectionDao();
				Section section=is1.getSection(sectionNo);
				
				waitList.setWaitListId(waitListId);
				waitList.setStudent(student);
				waitList.setSection(section);
				waitListList.add(waitList);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return waitList;
	}
	
	public void addWaitList(WaitList waitList){
		int waitListId=waitList.getWaitListId();
		String Sssn=waitList.getStudent().getSsn();
		int sectionNo=waitList.getSection().getSectionNo();
		
		String sql="insert into WaitList values('"+waitListId+"','"+Sssn+"','"+sectionNo+"','"+sectionNo+"')";
		Connection conn = DBUtil.getMySqlConnection();
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
	
	public void deleteWaitList(WaitList waitList){
		int waitListId=waitList.getWaitListId();
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "delete from WaitList where waitListId='"+waitListId+"'";
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
