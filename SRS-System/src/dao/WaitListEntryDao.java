package dao;

import java.util.List;

import model.WaitListEntry;

public interface WaitListEntryDao {
	List<WaitListEntry> getAllWaitListEntrys();
	WaitListEntry getWaitListEntry(int WaitListEntryId);
	void addWaitListEntry(WaitListEntry WaitListEntry);
	void deleteWaitListEntry(WaitListEntry WaitListEntry);
}
