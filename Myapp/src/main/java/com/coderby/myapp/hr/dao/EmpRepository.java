package com.coderby.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.hr.model.EmpDetailVO;
import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.hr.model.JobVO;
import com.coderby.myapp.util.PropertyEnc;

@Repository
public class EmpRepository implements IEmpRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PropertyEnc propertyEnc;
	
	private class EmpMapper implements RowMapper<EmpVO>{
		public EmpVO mapRow(ResultSet rs, int count) throws SQLException {
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString(4));
			emp.setPhoneNumber(rs.getString(5));
			emp.setHireDate(rs.getDate(6));
			emp.setJobId(rs.getString(7));
			emp.setSalary(rs.getDouble(8));
			emp.setCommissionPct(rs.getDouble(9));
			emp.setManagerId(rs.getInt(10));
			emp.setDepartmentId(rs.getInt(11));
			return emp;
		}
	}
	
	@Override
	public int getEmpCount() {
		String sql = "select count(*) from employees";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int getEmpCount(int deptId) {
		String sql = "select count(*) from employees where department_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
	}

	@Override
	public List<EmpVO> getEmpList() {
		String sql = "select * from employees";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public EmpVO getEmpInfo(int empId) {
		String sql = "select emp.employee_id, first_name, last_name,"
				+ "email, phone_number, hire_date, emp.job_id,"
				+ "job_title, salary, commission_pct, emp.manager_id,"
				+ "manager_name, emp.department_id, department_name from employees emp "
				+ "left join jobs job "
				+ "on emp.job_id=job.job_id "
				+ "left join departments dept "
				+ "on emp.department_id=dept.department_id "
				+ "left join "
				+ "(select employee_id, first_name||' '||last_name as manager_name "
				+ "from employees where employee_id in"
				+ "(select distinct manager_id from employees)) man "
				+ "on emp.manager_id=man.employee_id "
				+ "where emp.employee_id=?";
		return jdbcTemplate.queryForObject(sql, new EmpMapper() {
			public EmpDetailVO mapRow(ResultSet rs, int count) throws SQLException {
				EmpDetailVO emp = new EmpDetailVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString(4));
				emp.setPhoneNumber(rs.getString(5));
				emp.setHireDate(rs.getDate(6));
				emp.setJobId(rs.getString(7));
				emp.setJobTitle(rs.getString(8));
				emp.setSalary(rs.getDouble(9));
				emp.setCommissionPct(rs.getDouble(10));
				emp.setManagerId(rs.getInt(11));
				emp.setManagerName(rs.getString(12));
				emp.setDepartmentId(rs.getInt(13));
				emp.setDepartmentName(rs.getString(14));
				return emp;
			}
		}, empId);
	}

	@Override
	public void updateEmp(EmpVO emp) {
		String sql = "update employees set first_name=?, last_name=?,"
				+ "email=?, phone_number=?, hire_date=?, job_id=?,"
				+ "salary=?, commission_pct=?, manager_id=?,"
				+ "department_id=? where employee_id=?";
		jdbcTemplate.update(sql,emp.getFirstName(),
				emp.getLastName(),emp.getEmail(),emp.getPhoneNumber(),
				emp.getHireDate(),emp.getJobId(),emp.getSalary(),
				emp.getCommissionPct(),emp.getManagerId(),emp.getDepartmentId(),
				emp.getEmployeeId());
	}

	@Override
	public void insertEmp(EmpVO emp) {
		String sql = "insert into employees "
				+ "values(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql,emp.getEmployeeId(),
				emp.getFirstName(),emp.getLastName(),emp.getEmail(),
				emp.getPhoneNumber(),emp.getJobId(),emp.getSalary(),
				emp.getCommissionPct(),emp.getManagerId(),emp.getDepartmentId()
				);
	}

	@Override
	public void deleteEmp(int empId) {
		String sql = "delete from employees where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}

	@Override
	public void deleteJobHistory(int empId) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		String sql = "select department_id as departmentId,"
				+ "department_name as departmentName from departments";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<JobVO> getAllJobId() {
		String sql = "select job_id as jobId, job_title as jobTitle "
				+ "from jobs";
		return jdbcTemplate.query(sql, new RowMapper<JobVO>() {
			@Override
			public JobVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobVO job = new JobVO();
				job.setJobId(rs.getString(1));
				job.setJobTitle(rs.getString(2));
				return job;
			}
		});
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		String sql = "select employee_id as managerId, "
				+ "first_name||' '||last_name as managerName "
				+ "from employees "
				+ "where employee_id in "
				+ "(select distinct manager_id from employees)";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public void updateManagers(int empId) {
		String sql = "update (select * from employees where manager_id=?) set manager_id=null";
		jdbcTemplate.update(sql,empId);
		sql = "update (select * from departments where manager_id=?) set manager_id=null";
		jdbcTemplate.update(sql,empId);
	}

	@Override
	public List<EmpVO> getEmpListByName(String name) {
		String sql = "select * from employees where first_name like '%"+name+"%'";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public List<EmpVO> getEmpByMaxSalary() {
		String sql = "select * from employees where "
				+ "(department_id, salary) in (select department_id, max(salary) from "
				+ "employees group by department_id)";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public Map<String, Integer> getUpdateCount(int empId) {
		String sql = "select (select count(*) from employees where manager_id=?) as empCount,"
				+ "(select count(*) from departments where manager_id=?) as deptCount from dual";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Map<String,Integer>>(){
			@Override
			public Map<String, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String,Integer> count = new HashMap<String,Integer>();
				count.put("empCount", rs.getInt(1));
				count.put("deptCount", rs.getInt(2));
				return count;
			}
		} ,empId, empId);
	}

}
