package com.coderby.myapp.file.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.file.model.FileVO;

@Repository
public class FileRepository implements IFileRepository {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbctemplate;
	
	RowMapper<FileVO> fileMapper = new RowMapper<FileVO>() {
		@Override
		public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			FileVO file = new FileVO();
			file.setFileId(rs.getInt("file_Id"));
			file.setUserId(rs.getString("userid"));
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
		String sql = "insert into files (file_id, userid, directory_name, file_name, file_size,"
				+ "file_content_type, file_upload_date, file_data) values(?,?,?,?,?,?,sysdate,?)";
		jdbctemplate.update(sql,file.getFileId(), file.getUserId(), file.getDirectoryName(),file.getFileName(),
				file.getFileSize(),file.getFileContentType(),file.getFileData());
	}

	@Override
	public List<FileVO> getFileList(String directoryName) {
		String sql = "select file_id, userid, directory_name,file_name,"
				+ "file_size,file_content_type,file_upload_date "
				+ "from files "
				+ "where directory_name=? "
				+ "order by file_upload_date desc";
		return jdbctemplate.query(sql, fileMapper,directoryName);
	}

	@Override
	public List<FileVO> getAllFileList() {
		String sql = "select file_id, userid, directory_name, file_name,"
				+ "file_size, file_content_type, file_upload_date "
				+ "from files "
				+ "order by file_upload_date desc";
		return jdbctemplate.query(sql, fileMapper);
	}

	@Override
	public List<FileVO> getImageList(String directoryName) {
		String sql = "select file_id, userid, directory_name, file_name,"
				+ "file_size, file_content_type, file_upload_date, file_data "
				+ "from files "
				+ "where directory_name=? "
				+ "order by file_upload_date desc";
		return jdbctemplate.query(sql, new RowMapper<FileVO>() {

			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileVO file = new FileVO();
				file.setFileId(rs.getInt("file_Id"));
				file.setUserId(rs.getString("userid"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				file.setFileData(rs.getBytes("file_data"));
				return file;
			}
		
		}, directoryName);
	}

	@Override
	public FileVO getFile(int fileId) {
		String sql = "select file_id, userid,directory_name, file_name,"
				+ "file_size, file_content_type, file_upload_date, file_data "
				+ "from files "
				+ "where file_id=?";
		return jdbctemplate.queryForObject(sql, new RowMapper<FileVO>() {
			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileVO file = new FileVO();
				file.setUserId(rs.getString("userid"));
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

	@Override
	public String getDirectoryName(int fileId) {
		String sql = "select directory_name from files where file_id=?";
		return jdbctemplate.queryForObject(sql, String.class, fileId);
	}

	@Override
	public void deleteFile(int fileId) {
		String sql = "delete from files where file_id=?";
		jdbctemplate.update(sql, fileId);
	}
	
	@Override
	public void updateDirectory(HashMap<String, Object> map) {
		String sql = "update files set directory_name=? where file_id=?";
		jdbctemplate.update(sql, map.get("directoryName"), map.get("fileId"));
	}

	@Override
	public void updateFile(FileVO file) {
		String sql = "update files set directory_name=?, file_name=?, file_size=?, "
				+ "file_content_type=?, file_data=? where file_id=?";
		jdbctemplate.update(sql,file.getDirectoryName(),file.getFileName(),
				file.getFileSize(), file.getFileContentType(), file.getFileData(),
				file.getFileId());
	}

}
