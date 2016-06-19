package dao;

import java.util.List;

import model.ScheduleOfClasses;
import model.Student;

public interface IScheduleOfClasses {
	List<ScheduleOfClasses> getAllScheduleOfClassess(Student student);
	ScheduleOfClasses getScheduleOfClasses(String semester,Student student);
	void addScheduleOfClasses(ScheduleOfClasses schedule);
	void deleteScheduleOfClasses(ScheduleOfClasses schedule);
}
