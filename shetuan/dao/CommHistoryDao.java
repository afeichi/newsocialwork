package zkSocialNetworkProject.shetuan.dao;

import java.util.List;

import zkSocialNetworkProject.shetuan.domain.CommHistory;
import zkSocialNetworkProject.shetuan.domain.CommHistoryPic;


public interface CommHistoryDao {
	void addHistory(CommHistory commHistory);
	
	void addHistoryPic(CommHistoryPic commHistoryPic);
	
	List<CommHistory> getCommHistory(String communityId);
	
	List<String> getCommHistoryPic(String historyId);
	
	void deleteHistory(String historyId);
	
	void deleteHistoryPic(String historyId);

	CommHistory getCommHistoryDetail(String historyId);
}
