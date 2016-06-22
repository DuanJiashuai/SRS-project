package dao;

import java.util.List;

import model.Professor;

public interface ProfessorDao {
	List<Professor> getAllProfessors();
	Professor getProfessor(String Pssn);
	void addProfessor(Professor professor);
	void deleteProfessor(Professor professor);
}
