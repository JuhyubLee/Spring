package spring.jh.myapp.file.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jh.myapp.file.model.FileVO;

@Service
public class FileService implements IFileService {

	@Autowired
	IFileRepository fileRepository;
	
	@Override
	public void uploadFile(FileVO file) {
		file.setFileId(fileRepository.getMaxFileId()+1);
		fileRepository.uploadFile(file);
		
	}

}
