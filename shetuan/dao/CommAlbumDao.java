package zkSocialNetworkProject.shetuan.dao;

import java.util.List;

import zkSocialNetworkProject.shetuan.domain.CommAlbum;
import zkSocialNetworkProject.shetuan.domain.CommAlbumPic;

public interface CommAlbumDao {
	//添加当日新的相片集以及描述
	void addAlbum(CommAlbum commAlbum);
	
	//添加当日相片集中的照片
	void addAlbumPic(CommAlbumPic cap);
	
	//得到相册列表
	List<CommAlbum> getCommAlbum(String uid);//uid指社团id
	
	//得到相册列表对应的所有照片路径
	List<String> getCommAlbumPic(String cid);
	
	//删除某日相片集(整栏删除)
	void deleteAlbum(String cid);
	
	//删除某日相片集相应的照片(整栏删除)
	void deleteAlbumPic(String cid);
	
	//删除单独一张照片(主键问题优化)
	void deleteOnePhoto(String cphotoPath);
}
