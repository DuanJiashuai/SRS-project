package dao;

public class dataAccess {
	private static String daoName = "sqliteDao";

	public static CourseDao createCourseDao() {
		CourseDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "CourseImpl").newInstance();
			result = (CourseDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static StudentDao createStudentDao() {
		StudentDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "StudentImpl").newInstance();
			result = (StudentDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static ProfessorDao createProfessorDao() {
		ProfessorDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "ProfessorImpl").newInstance();
			result = (ProfessorDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static ScheduleOfClassesDao createScheduleOfClassesDao() {
		ScheduleOfClassesDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "ScheduleOfClassesImpl").newInstance();
			result = (ScheduleOfClassesDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static SectionDao createSectionDao() {
		SectionDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "SectionImpl").newInstance();
			result = (SectionDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static TranscriptEntryDao createTranscriptEntryDao() {
		TranscriptEntryDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "TranscriptEntryImpl").newInstance();
			result = (TranscriptEntryDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static WaitListDao createWaitListDao() {
		WaitListDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "WaitListImpl").newInstance();
			result = (WaitListDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
