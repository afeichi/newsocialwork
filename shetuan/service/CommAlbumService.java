package zkSocialNetworkProject.shetuan.service;

import java.util.List;

import zkSocialNetworkProject.shetuan.domain.CommAlbum;

public interface CommAlbumService {
	
//	// 添加当日新的相片集以及描述
//	void addCommAlbum(CommAlbum commAlbum);
	
	// 显示相片列表
	List<CommAlbum> getCommAlbum(String uid);
	
	// 删除某日相片集(整栏删除)
	void deleteCommAlbum(String cid);
	
	// 删除单独一张照片
	void deleteOnePhoto(String cphotoPath);

	void addCommAlbum(CommAlbum commAlbum);
}
