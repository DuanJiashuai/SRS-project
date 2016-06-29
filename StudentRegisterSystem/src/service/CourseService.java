package service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CourseDao;
import dao.dataAccess;
import model.Course;

public class CourseService {
	public String getAllCoursesJSON() {
		JSONArray ja = new JSONArray();

		CourseDao cd = dataAccess.createCourseDao();
		List<Course> courses = cd.getAllCourses();

		for (Course c : courses) {
			JSONObject jo = new JSONObject();
			jo.put("courseNo", c.getCourseNo());
			jo.put("courseName", c.getCourseName());
			jo.put("credits", c.getCredits()+" points");
			ja.put(jo);
		}
		return ja.toString();
	}

	public String getCourseJSON(String courseNo) {
		JSONObject jo = new JSONObject();

		CourseDao cd = dataAccess.createCourseDao();
		Course c = cd.getCourse(courseNo);
		
		jo.put("courseNo", c.getCourseNo());
		jo.put("courseName", c.getCourseName());
		jo.put("credits", c.getCredits());
		jo.put("preCourseNo", cd.getPrerequisites(c).get(0).getCourseNo());
		return jo.toString();
	}
	
	public void addCourse(String courseNo,String courseName,double credits,String preCourseNo){
		Course c=new Course(courseNo,courseName,credits);
		CourseDao cd=dataAccess.createCourseDao();
		cd.addCourse(c,preCourseNo);
	}
	
	public void deleteCourse(String courseNo){
		CourseDao pd=dataAccess.createCourseDao();
		pd.deleteCourse(courseNo);
	}
	
	public void updateCourse(Course course,String preCourseNo){
		CourseDao cd=dataAccess.createCourseDao();
		cd.updateCourse(course);
		cd.updatePrerequisite(course, preCourseNo);
	}
}
