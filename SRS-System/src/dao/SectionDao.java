package dao;

import java.util.HashMap;
import java.util.List;

import model.Section;
import model.Student;
import model.TranscriptEntry;

public interface SectionDao {
	List<Section> getAllSections();
	Section getSection(int sectionNo);
	List<Student> getEnrolledStudents(Section section);
	HashMap<Student, TranscriptEntry> getAssignedGrades(Section section);
	void addSection(Section section);
	void deleteSection(Section section);
}
