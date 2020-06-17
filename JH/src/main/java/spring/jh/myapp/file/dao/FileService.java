package spring.jh.myapp.file.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jh.myapp.file.model.FileVO;

@Service
public class FileService implements IFileService {

	@Autowired
	IFileRepository fileRepository;
	
	// FILE UPLOAD
	@Override
	public void uploadFile(FileVO file) {
		file.setFileId(fileRepository.getMaxFileId()+1);
		fileRepository.uploadFile(file);
	}
	
	// FILE GET
	@Override
	public FileVO getFile(int fileId) {
		return fileRepository.getFile(fileId);
	}
	
	// FILE DELETE
	@Override
	public void deleteFile(int fileId) {
		fileRepository.deleteFile(fileId);
	}
	
	// FILE LIST
	@Override
	public List<FileVO> getFileList(String directoryName) {
		return fileRepository.getFileList(directoryName);
	}
	
	// FILE ALL LIST
	@Override
	public List<FileVO> getAllFileList() {
		return fileRepository.getAllFileList();
	}
	
	// FILE UPDATE
	@Override
	public void updateDirectory(int[] fileIds, String directoryName) {
		for(int fileId : fileIds) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("fileId", fileId);
			map.put("directoryName", directoryName);
			fileRepository.updateDirectory(map);
		}
	}
	
	// FILE UPDATE
	@Override
	public void updateFile(FileVO file) {
		fileRepository.updateFile(file);
	}

}
