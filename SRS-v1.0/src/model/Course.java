package model;

import java.util.ArrayList;
import java.util.Collection;

public class Course {
	private String courseNo;
	private String courseName;
	private double credits;
	
	private ArrayList<Section> offeredAsSection;
	private ArrayList<Course> prerequisites;
	
	public Course(String cNo, String cName, double credits) {
		setCourseNo(cNo);
		setCourseName(cName);
		setCredits(credits);
		offeredAsSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
	}

	public Course() {}
	
	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	// -----------------------------
	// Miscellaneous other methods.
	// -----------------------------
	
	public void addSection(Section s) {
		offeredAsSection.add(s);
	}
	
	public void display() {
		System.out.println("Course Information:");
		System.out.println("\tCourse No.:  " + getCourseNo());
		System.out.println("\tCourse Name:  " + getCourseName());
		System.out.println("\tCredits:  " + getCredits());
		System.out.println("\tPrerequisite Courses:");

		for (Course c : prerequisites) {
			System.out.println("\t\t" + c.toString());
		}

		// Note use of print vs. println in next line of code.

		System.out.print("\tOffered As Section(s):  ");
		for (Section s : offeredAsSection) {
			System.out.print(s.getSectionNo() + " ");
		}

		// Finish with a blank line.

		System.out.println();
	}

	@Override
	public String toString() {
		return getCourseNo() + ":" + getCourseName();
	}

	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}

	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0)
			return true;
		else
			return false;
	}

	public Collection<Course> getPrerequisites() {
		return prerequisites;
	}

	public Section scheduleSection(String day, String time, String room, int capacity) {
		Section s = new Section();
		s.setDayOfWeek(day);
		s.setTimeOfDay(time);
		s.setRepresentedCourse(this);
		s.setRoom(room);
		s.setSectionNo(offeredAsSection.size() + 1);
		// –¥»ÎofferedAsSection÷–
		addSection(s);
		return s;
	}
}
