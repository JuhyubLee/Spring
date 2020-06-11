package spring.jh.myapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyJdbcTemplate extends JdbcTemplate{

	public MyJdbcTemplate(DataSource datasource) {
		super(datasource);
	}
	
	public <T> T queryForNullableObject(String sql, RowMapper<T> rowMapper,
			Object...args) throws DataAccessException{
		List<T> results = query(sql, rowMapper, args);
		if(results == null || results.isEmpty()) {
			return null;
		} else if (results.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1, results.size());
		} else {
			return results.iterator().next();
		}
	}
	
	public <T> T queryForNullableObject(String sql, Class<T> requiredType, Object...args)
	throws DataAccessException{
		return this.queryForNullableObject(sql, new RowMapper<T>() {
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T result = (T)rs.getObject(1);
		return result;
	}
		}, args);
	}
}
