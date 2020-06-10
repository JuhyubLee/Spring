package spring.jh.myapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import spring.jh.myapp.member.model.MemberVO;

public class MemberRepository implements IMemberRepository{

	@Autowired
	MyJdbcTemplate jt;
	
	@Override
	public void insertMember(MemberVO mem) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		jt.update(sql, mem.getUserId(), mem.getName(), mem.getPassword(), mem.getEmail(),
				mem.getAddress(), mem.getEnabled());
	}
	
	@Override
	public void insertAuth(String userId) {
		String sql = "insert into authorities values(?,?)";
		jt.update(sql, userId, "ROLE_USER");
	}
	
	@Override
	public MemberVO getMember(String userId) {
		String sql = "select m.userid, name, password, email, address,"
				+ "enabled, authority from member m "
				+ "join authorities au "
				+ "on m.userid=au.userid "
				+ "where m.userid=?";
		return jt.query(sql, new ResultSetExtractor<MemberVO>() {
			@Override
			public MemberVO extractData(ResultSet rs) throws SQLException{
				if(rs.next()) {
					MemberVO mem = new MemberVO();
					mem.setUserId(rs.getString("userid"));
					mem.setName(rs.getString("name"));
					mem.setPassword(rs.getString("password"));
					mem.setEmail(rs.getString("email"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
					return mem;
				}else {
					return null;
				}
			}
		}, userId);
	}
	
	@Override
	public String getPassword(String userId) {
		String sql = "select password from member where userid=?";
		return jt.queryForNullableObject(sql, String.class,userId);
	}
}
