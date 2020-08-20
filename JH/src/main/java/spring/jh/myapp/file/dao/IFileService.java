package spring.jh.myapp.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.jh.myapp.file.model.FileVO;

public interface IFileService {

	void uploadFile(FileVO file);
	FileVO getFile(@Param("fileId") int fileId);
	void deleteFile(int fileId);
	List<FileVO> getFileList(@Param("directoryName") String directoryName);
	List<FileVO> getAllFileList();
	void updateDirectory(int[] fileIds, String directoryName);
	void updateFile(FileVO file);
}
