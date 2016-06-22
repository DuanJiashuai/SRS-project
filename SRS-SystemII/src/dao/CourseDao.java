package dao;

import java.util.List;

import model.Course;

public interface CourseDao {
	List<Course> getAllCourses();
	Course getCourse(int courseNo);
	void addCourse(Course course);
	void deleteCourse(Course course);
}
