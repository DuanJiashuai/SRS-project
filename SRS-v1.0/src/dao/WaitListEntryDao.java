package dao;

import java.util.List;

import model.Section;
import model.Student;
import model.WaitListEntry;

public interface WaitListEntryDao {
	List<WaitListEntry> getAllWaitListEntrysBySection(Section section);
	List<WaitListEntry> getAllWaitListEntrysByStudent(Student student);
	WaitListEntry getWaitListEntry(Student student,Section section);
	void addWaitListEntry(WaitListEntry waitListEntry);
	void deleteWaitListEntry(WaitListEntry waitListEntry);
}
