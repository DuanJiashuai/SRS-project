package service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CourseDao;
import dao.dataAccess;
import model.Course;

public class CourseJson {
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
		return jo.toString();
	}
}
