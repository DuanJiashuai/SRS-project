package dao;

import java.util.List;

import model.WaitList;

public interface IWaitList {
	List<WaitList> getAllWaitLists();
	WaitList getWaitList(int waitListId);
	void addWaitList(WaitList waitList);
	void deleteWaitList(WaitList waitList);
}
