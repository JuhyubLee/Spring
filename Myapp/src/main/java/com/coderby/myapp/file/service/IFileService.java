package com.coderby.myapp.file.service;

import java.util.List;

import com.coderby.myapp.file.model.FileVO;

public interface IFileService {

	void uploadFile(FileVO file);
	
	List<FileVO> getFileList(String directoryName);
	List<FileVO> getAllFileList();
	List<FileVO> getImageList(String directoryName);
	
	FileVO getFile(int fileId);
	
	String getDirectoryName(int fileId);
	void updateDirectory(int[] fileIds, String directoryName);
	void deleteFile(int fileId);
	void updateFile(FileVO file);
	
}
