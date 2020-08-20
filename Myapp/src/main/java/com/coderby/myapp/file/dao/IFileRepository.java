package com.coderby.myapp.file.dao;

import java.util.HashMap;
import java.util.List;

import com.coderby.myapp.file.model.FileVO;

public interface IFileRepository {

	int getMaxFileId();
	void uploadFile(FileVO file);
	
	List<FileVO> getFileList(String directoryName);
	List<FileVO> getAllFileList();
	List<FileVO> getImageList(String directoryName);
	
	FileVO getFile(int fileId);
	
	String getDirectoryName(int fileId);
	void updateDirectory(HashMap<String, Object> map);
	void deleteFile(int fileId);
	void updateFile(FileVO file);
	
}
