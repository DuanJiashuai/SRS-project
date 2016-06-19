package dao;

import java.util.List;

import model.ScheduleOfClasses;

public interface IScheduleOfClasses {
	List<ScheduleOfClasses> getAllScheduleOfClassess();
	ScheduleOfClasses getScheduleOfClassesBySemester(String semester);
	ScheduleOfClasses getScheduleOfClassesByCourseNo(int courseNo);
	void addScheduleOfClasses(ScheduleOfClasses schedule);
	void deleteScheduleOfClasses(ScheduleOfClasses schedule);
}
