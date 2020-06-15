package spring.jh.myapp.file.dao;

import spring.jh.myapp.file.model.FileVO;

public interface IFileRepository {

	int getMaxFileId();
	void uploadFile(FileVO file);
	FileVO getFile(int fileId);
	

}
