package model;

public abstract class Person {

	private String name;
	private String ssn;
	// ----------------
	// Constructor(s).
	// ----------------

	public Person(String name, String ssn) {
		this.setName(name);
		this.setSsn(ssn);
	}

	public Person() {
	}
	// ------------------
	// Accessor methods.
	// ------------------

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setSsn(String s) {
		ssn = s;
	}

	public String getSsn() {
		return ssn;
	}

	// -----------------------------
	// Miscellaneous other methods.
	// -----------------------------

	// We'll let each subclass determine how it wishes to be
	// represented as a String value.

	@Override
	public abstract String toString();

	public void display() {
		System.out.println("Person Information:");
		System.out.println("\tName:  " + this.getName());
		System.out.println("\tSoc. Security No.:  " + this.getSsn());
	}
}
