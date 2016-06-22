package dao;

import java.util.List;

import model.WaitList;

public interface WaitListDao {
	List<WaitList> getAllWaitLists();
	WaitList getWaitList(int waitListId);
	void addWaitList(WaitList waitList);
	void deleteWaitList(WaitList waitList);
}
