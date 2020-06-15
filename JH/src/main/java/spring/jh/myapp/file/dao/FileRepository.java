package spring.jh.myapp.file.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.jh.myapp.file.model.FileVO;

@Repository
public class FileRepository implements IFileRepository{

@Autowired
@Qualifier("jdbcTemplate")
JdbcTemplate jdbctemplate;

RowMapper<FileVO> fileMapper = new RowMapper<FileVO>() {
	@Override
	public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		FileVO file = new FileVO();
		file.setFileId(rs.getInt("file_Id"));
		file.setDirectoryName(rs.getString("directory_name"));
		file.setFileName(rs.getString("file_name"));
		file.setFileSize(rs.getLong("file_size"));
		file.setFileContentType(rs.getString("file_content_type"));
		file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
		return file;
	}
};

@Override
public int getMaxFileId() {
	String sql = "select nvl(max(file_id),0) from files";
	return jdbctemplate.queryForObject(sql, Integer.class);
}

@Override
public void uploadFile(FileVO file) {
	String sql = "insert into files (file_id, directory_name, file_name, "
			+ "file_size,"
			+ "file_content_type, file_upload_date, file_data) values(?,?,?,?,?,sysdate,?)";
	jdbctemplate.update(sql, file.getFileId(),file.getDirectoryName(),file.getFileName(),
			file.getFileSize(), file.getFileContentType(), file.getFileData());
}

@Override
public FileVO getFile(int fileId) {
	String sql = "select file_id, directory_name, file_name,"
			+ "file_size, file_content_type, file_upload_date, file_data "
			+ "from files where file_id=?";
	return jdbctemplate.queryForObject(sql, new RowMapper<FileVO>() {
		@Override
		public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			FileVO file = new FileVO();
			file.setFileId(rs.getInt("file_Id"));
			file.setDirectoryName(rs.getString("directory_name"));
			file.setFileName(rs.getString("file_name"));
			file.setFileSize(rs.getLong("file_size"));
			file.setFileContentType(rs.getString("file_content_type"));
			file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
			file.setFileData(rs.getBytes("file_data"));
			return file;
		}
	}, fileId);
}
}
