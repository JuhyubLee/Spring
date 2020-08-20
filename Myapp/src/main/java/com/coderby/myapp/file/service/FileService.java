package com.coderby.myapp.file.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderby.myapp.file.dao.IFileRepository;
import com.coderby.myapp.file.model.FileVO;
@Service
public class FileService implements IFileService {

	@Autowired
	IFileRepository fileRepository;

	@Override
	public void uploadFile(FileVO file) {
		file.setFileId(fileRepository.getMaxFileId()+1);
		fileRepository.uploadFile(file);
	}

	@Override
	public List<FileVO> getFileList(String directoryName) {
		return fileRepository.getFileList(directoryName);
	}

	@Override
	public List<FileVO> getAllFileList() {
		return fileRepository.getAllFileList();
	}

	@Override
	public List<FileVO> getImageList(String directoryName) {
		return fileRepository.getImageList(directoryName);
	}

	@Override
	public FileVO getFile(int fileId) {
		return fileRepository.getFile(fileId);
	}

	@Override
	public String getDirectoryName(int fileId) {
		return fileRepository.getDirectoryName(fileId);
	}

	@Override
	public void deleteFile(int fileId) {
		fileRepository.deleteFile(fileId);
	}
	
	@Override
	public void updateDirectory(int[] fileIds, String directoryName) {
		for(int fileId : fileIds) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("fileId", fileId);
			map.put("directoryName", directoryName);
			fileRepository.updateDirectory(map);
		}
	}

	@Override
	public void updateFile(FileVO file) {
		fileRepository.updateFile(file);
	}

}
