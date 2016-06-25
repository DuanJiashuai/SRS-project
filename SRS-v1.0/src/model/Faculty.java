package model;

import java.util.HashMap;

public class Faculty {
	private HashMap<String, Professor> professors;

	public Faculty() {
		professors = new HashMap<String, Professor>();
	}

	public void display() {
		// Iterate through the HashMap and display all entries.

		for (Professor p : professors.values()) {
			p.display();
			System.out.println();
		}
	}

	public void addProfessor(Professor p) {
		professors.put(p.getSsn(), p);
	}

	public Professor findProfessor(String ssn) {
		return (Professor) professors.get(ssn);
	}

	public boolean isEmpty() {
		if (professors.size() == 0)
			return true;
		else
			return false;
	}
}
