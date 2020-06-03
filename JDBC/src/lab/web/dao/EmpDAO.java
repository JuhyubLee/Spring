package lab.web.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.vo.EmpVO;

public class EmpDAO {

	static {
		try {
			DriverManager.
			registerDriver
			(new oracle.jdbc.OracleDriver());
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		}
	}
	
	private Connection getConnection() {
		DataSource ds = null;
		Connection con = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)
			ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void closeConnection(Connection con) {
		if(con!=null) {
			try {con.close();}
			catch(SQLException e) {}
		}
	}
	
	public EmpVO selectEmployee(int empId) {
		Connection con = null;
		EmpVO emp = new EmpVO();
		try {
			con = getConnection();
			String sql = "select * from employees "
					+ "where employee_id=?";
			PreparedStatement stmt = 
					con.prepareStatement(sql);
			stmt.setInt(1, empId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble(9));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectEmployee메서드 에러발생"
					+ "-콘솔확인");
		}finally {
			closeConnection(con);
		}
		return emp;
	}
	
}


































