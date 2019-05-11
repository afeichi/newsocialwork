package zkSocialNetworkProject.shetuan.service;

import java.util.List;

import zkSocialNetworkProject.shetuan.domain.CommHistory;

public interface CommHistoryService {
	void addHistory(CommHistory commHistory);
	
	List<CommHistory> getCommHistory(String communityId);
	
	void deleteHistory(String historyId);
	
	CommHistory getCommHistoryDetail(String historyId);
}
