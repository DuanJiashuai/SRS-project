package dao;

public class dataAccess {
	private static String daoName = "sqliteDao";

	public static ICourse createCourseDao() {
		ICourse result = null;
		try {
			Object o = Class.forName(daoName + "." + "CourseImpl").newInstance();
			result = (ICourse)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static IStudent createStudentDao() {
		IStudent result = null;
		try {
			Object o = Class.forName(daoName + "." + "StudentImpl").newInstance();
			result = (IStudent)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static IProfessor createProfessorDao() {
		IProfessor result = null;
		try {
			Object o = Class.forName(daoName + "." + "ProfessorImpl").newInstance();
			result = (IProfessor)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static IRoom createRoomDao() {
		IRoom result = null;
		try {
			Object o = Class.forName(daoName + "." + "RoomImpl").newInstance();
			result = (IRoom)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static IScheduleOfClasses createScheduleOfClassesDao() {
		IScheduleOfClasses result = null;
		try {
			Object o = Class.forName(daoName + "." + "ScheduleOfClassesImpl").newInstance();
			result = (IScheduleOfClasses)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static ISection createSectionDao() {
		ISection result = null;
		try {
			Object o = Class.forName(daoName + "." + "SectionImpl").newInstance();
			result = (ISection)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static ITranscriptEntry createTranscriptEntryDao() {
		ITranscriptEntry result = null;
		try {
			Object o = Class.forName(daoName + "." + "TranscriptEntryImpl").newInstance();
			result = (ITranscriptEntry)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static IWaitList createWaitListDao() {
		IWaitList result = null;
		try {
			Object o = Class.forName(daoName + "." + "WaitListImpl").newInstance();
			result = (IWaitList)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
