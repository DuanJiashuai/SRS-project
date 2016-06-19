package dao;

import java.util.List;

import model.Student;

public interface IStudent {
	List<Student> getAllStudents();
	Student getStudent(String Sssn);
	void addStudent(Student student);
	void deleteStudent(Student student);
}
