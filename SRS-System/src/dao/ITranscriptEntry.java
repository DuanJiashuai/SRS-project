package dao;

import java.util.List;

import model.TranscriptEntry;

public interface ITranscriptEntry {
	List<TranscriptEntry> getAllTranscriptEntrys();
	TranscriptEntry getTranscriptEntry(int transcriptEntryNo);
	void addTranscriptEntry(TranscriptEntry transcriptEntry);
	void deleteTranscriptEntry(TranscriptEntry transcriptEntry);
}
