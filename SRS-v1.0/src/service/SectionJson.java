package service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.SectionDao;
import dao.dataAccess;
import model.Section;
import model.Student;

public class SectionJson {
	public String getAllSectionsJSON(String courseNo) {
		JSONArray ja = new JSONArray();

		SectionDao sd=dataAccess.createSectionDao();
		List<Section> sections=sd.getSectionsByCourse(courseNo);
		
		for(Section s:sections){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", s.getSectionNo());
			jo.put("day", s.getDayOfWeek());
			jo.put("time",s.getTimeOfDay());
			jo.put("room", s.getRoom());
			jo.put("sCapacity", s.getSeatingCapacity());
			jo.put("professor", s.getInstructor().getName());
			jo.put("pssn", s.getInstructor().getSsn());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	public String getEnrolledStudents(int sectionNo){
		JSONArray ja = new JSONArray();
		
		SectionDao sd=dataAccess.createSectionDao();
		List<Student> enrolled=sd.getEnrolledStudents(sectionNo);
		
		for(Student s:enrolled){
			JSONObject jo = new JSONObject();
			jo.put("sssn", s.getSsn());
			jo.put("name", s.getName());
			jo.put("major", s.getMajor());
			jo.put("degree", s.getDegree());
			ja.put(jo);
		}
		return ja.toString();
	}
}
