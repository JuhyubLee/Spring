package spring.jh.myapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.jh.myapp.member.model.MemberVO;

@Repository
public class MemberRepository implements IMemberRepository{

	@Autowired
	MyJdbcTemplate jt;
	
	
	@Override
	public boolean checkId(String userId) {
		String sql = "select count(*) from member where userid=?";
		Integer a = Integer.parseInt(String.valueOf(jt.queryForNullableObject(sql, Integer.class, userId)));
		return a == 0 ? true : false;
	}
	// INSERT MEMBER
	@Override
	public void insertMember(MemberVO mem) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		jt.update(sql, mem.getUserId(), mem.getName(), mem.getPassword(), mem.getEmail(), 
				mem.getAddress(), mem.getEnabled());
	}
	
	// INSERT AUTHORITY
	@Override
	public void insertAuth(String userId) {
		String sql = "insert into authorities values(?,?)";
		jt.update(sql, userId, "ROLE_USER");
	}
	
	// UPDATE MEMBER
	@Override
	public void updateMem(MemberVO mem) {
		String sql = "update member set name=?, "
				+ "password=?, email=?, address=? where userid=?";
		jt.update(sql, mem.getName(), mem.getPassword(), mem.getEmail(),
				mem.getAddress(), mem.getUserId());
	}
	
	// UPDATE AUTHORITY
	@Override
	public void updateAuthority(MemberVO mem) {
		String sql = "update authorities set authority=? where userid=(select userid from member where userid=?)";
		jt.update(sql, mem.getAuth(), mem.getUserId());
	}
	
	// UPDATE ENABLE
	@Override
	public void updateEnable(MemberVO mem) {
		String sql = "update member set enabled=? where userid=?";
		jt.update(sql, mem.getEnabled(), mem.getUserId());
	}
	
	// GET MEMBER
	@Override
	public MemberVO getMember(String userId) {
		String sql = "select m.userid, name, password, email, address,"
				+ "enabled, au.authority from member m "
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
	
	// GET PASSWORD
	@Override
	public String getPassword(String userId) {
		String sql = "select password from member where userid=?";
		return jt.queryForNullableObject(sql, String.class,userId);
	}
	
	// DELETE MEMBER
	@Override
	public void deleteMem(String userId) {
		String sql = "delete from member where userid=?";
		jt.update(sql, userId);
	}
	
	// DELETE AUTHORITY
	@Override
	public void deleteAu(String userId) {
		String sql = "delete from authorities where userid=?";
		jt.update(sql, userId);
	}
	
	// DELETE FILE
	@Override
	public void deleteFile(String userId) {
		String sql = "delete from files where userid=?";
		jt.update(sql, userId);
	}
	
	// UPDATE FILE (NULL)
	@Override
	public void updateFile(String userId) {
		String sql = "update files f set f.userid=null where userid=(select userid from member where userid=?)";
		jt.update(sql, userId);
	}
	
	// GET MEMBER LIST
	@Override
	public List<MemberVO> getMemList(int page){
		int start = (page-1)*10+1;
		int end = start+9;
		String sql = "select rnum, userid, name, email, address, enabled, authority " + 
						"from" + 
						"(select rownum rnum, userid, name, email, address, enabled, authority " + 
						"from" + 
						"(select m.userid, name, email, address, enabled, authority " + 
						"from member m "+ 
						"join authorities a "+ 
						"on m.userid=a.userid "+ 
						"order by m.userid)) "+ 
						"where rnum between ? and ?";
		return jt.query(sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				MemberVO mem = new MemberVO();
				mem.setUserId(rs.getString("userid"));
				mem.setName(rs.getString("name"));
				mem.setEmail(rs.getString("email"));
				mem.setAddress(rs.getString("address"));
				mem.setEnabled(rs.getInt("enabled"));
				mem.setAuth(rs.getString("authority"));
				return mem;
			}
		}, start, end);
	}
	
	// GET MEMBER LIST Count
	@Override
	public int getMemListCnt(MemberVO mem) {
		String sql = "select count(*) from member";
		return jt.queryForObject(sql, Integer.class);
	}
}
