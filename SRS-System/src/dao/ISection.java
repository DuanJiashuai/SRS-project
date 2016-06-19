package dao;

import java.util.List;

import model.Section;

public interface ISection {
	List<Section> getAllSections();
	Section getSection(int sectionNo);
	void addSection(Section section);
	void deleteSection(Section section);
}
