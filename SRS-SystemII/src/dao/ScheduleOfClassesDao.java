package dao;

import java.util.List;

import model.ScheduleOfClasses;
import model.Student;

public interface ScheduleOfClassesDao {
	List<ScheduleOfClasses> getAllScheduleOfClassess(Student student);
	ScheduleOfClasses getScheduleOfClasses(String semester,Student student);
	void addScheduleOfClasses(ScheduleOfClasses schedule);
	void deleteScheduleOfClasses(ScheduleOfClasses schedule);
}
