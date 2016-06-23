package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import dao.SectionDao;
import dao.dataAccess;
import model.ScheduleOfClasses;
import model.Section;
import util.DBUtil;

public class ScheduleOfClassesImpl {
	public ScheduleOfClasses getScheduleOfClassess(String semester) {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from ScheduleOfClasses where semester='" + semester + "'";
		ScheduleOfClasses schedule=new ScheduleOfClasses();
		schedule.setSemester(semester);
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo=rs.getInt("sectionNo");
				SectionDao sd=dataAccess.createSectionDao();
				Section section=sd.getSection(sectionNo);
				schedule.addSection(section);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedule;
	}

	public List<Section> getAllSectionsOffered(ScheduleOfClasses schedule){
		List<Section> sectionsList=new ArrayList<Section>();
		HashMap<String, Section> sectionsOffered=schedule.getSectionsOffered();
		Iterator<Entry<String, Section>> iter = sectionsOffered.entrySet().iterator();
		while(iter.hasNext()) {
		    Entry<String, Section> entry = (Entry<String, Section>)iter.next();
		    /*String fullSectionNo = entry.getKey();*/
		    Section section = entry.getValue();
		    sectionsList.add(section);
		}
		return sectionsList;
	}
	
	public void addScheduleOfClasses(ScheduleOfClasses schedule) {
		String semester = schedule.getSemester();
		HashMap<String, Section> sections=schedule.getSectionsOffered();
		Iterator<Entry<String, Section>> iter = sections.entrySet().iterator();
		while(iter.hasNext()) {
		    Entry<String, Section> entry = (Entry<String, Section>)iter.next();
		    /*String fullSectionNo = entry.getKey();*/
		    Section section = entry.getValue();
		    int sectionNo=section.getSectionNo();
		    String courseNo=section.getRepresentedCourse().getCourseNo();
		    String sql = "insert into ScheduleOfClasses values('" + semester + "','" + sectionNo + "','" + courseNo + "')";
		    Connection conn = DBUtil.getSqliteConnection();
		    try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("插入课程计划异常：" + e.getMessage());
			}
		}
	}

	public void deleteScheduleOfClasses(ScheduleOfClasses schedule) {
		String semester = schedule.getSemester();
		HashMap<String, Section> sections=schedule.getSectionsOffered();
		Iterator<Entry<String, Section>> iter = sections.entrySet().iterator();
		while(iter.hasNext()) {
		    Entry<String, Section> entry = (Entry<String, Section>)iter.next();
		    /*String fullSectionNo = entry.getKey();*/
		    Section section = entry.getValue();
		    int sectionNo=section.getSectionNo();
		    String courseNo=section.getRepresentedCourse().getCourseNo();
		    String sql = "delete from ScheduleOfClasses where semester='"+semester+"' and sectionNo="+sectionNo+" and courseNo='"+courseNo+"'";
		    Connection conn = DBUtil.getSqliteConnection();
		    try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("删除课程计划异常：" + e.getMessage());
			}
		}
	}
}
