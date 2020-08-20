package spring.jh.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.jh.myapp.hr.model.DeptVO;
import spring.jh.myapp.hr.model.EmpVO;
import spring.jh.myapp.hr.model.JobVO;

@Repository
public class EmpRepository implements IEmpRepository {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	private class EmpMapper implements RowMapper<EmpVO>{
		public EmpVO mapRow(ResultSet rs, int count) throws SQLException{
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
			emp.setJobTitle(rs.getString("job_title"));

			return emp;
		}
	}
	
	// GET EMP COUNT
	@Override
	public int getEmpCount() {
		String sql = "select count(*) from employees";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	// GET EMP COUNT (Departments)
	@Override
	public int getEmpCount(int deptId) {
		String sql = "select count(*) from employees where department_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
	}
	
	// GET EMP LIST
	@Override
	public List<EmpVO> getEmpList(){
		String sql = "select * from employees emp join jobs jb on emp.job_id = jb.job_id order by 1 ASC";
		return jdbcTemplate.query(sql, new EmpMapper());
	}
	
	// GET EMP INFO
	@Override
	public EmpVO getEmpInfo(int empId) {
		String sql = "select * from employees emp join jobs jb on emp.job_id = jb.job_id where employee_id=?";
		return jdbcTemplate.queryForObject(sql, new EmpMapper(), empId);
	}
	
	// GET Department INFO
	public DeptVO getDeptInfo(int deptId) {
		String sql = "select * from departments where department_id=?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<DeptVO>(){

			@Override
			public DeptVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
				return dept;
			}
		}, deptId);
	}
	
	// GET TOP SALARY
	@Override
	public List<EmpVO> getTopSalary() {
		String sql = "select * from employees e join jobs jb on e.job_id = jb.job_id where"
				+ " (e.salary, e.department_id) in (select max(salary), "
				+ "department_id from employees group by department_id) order by 1 ASC";
		return jdbcTemplate.query(sql, new EmpMapper());
	}
	
	// INSERT EMP
	@Override
	public void insertEmp(EmpVO emp) {
		String sql = "insert into employees values(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql, emp.getEmployeeId(), emp.getFirstName(),
				emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(),
				emp.getJobId(), emp.getSalary(), emp.getCommissionPct(),
				emp.getManagerId(), emp.getDepartmentId());
	}
	
	  // UPDATE EMP
	  @Override 
	  public void updateEmp(EmpVO emp) { String sql =
	  "update employees set first_name=?, last_name=?," +
	  "email=?, phone_number=?, hire_date=?, job_id=?," +
	  "salary=?, commission_Pct=?, manager_id=?," +
	  "department_id=? where employee_id=?"; 
	  jdbcTemplate.update(sql, emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(),
	  emp.getHireDate(), emp.getJobId(), emp.getSalary(), emp.getCommissionPct(),
	  emp.getManagerId(), emp.getDepartmentId(), emp.getEmployeeId()); }
	 
	// DELETE EMP
	@Override
	public void deleteEmp(int empId) {
		String sql = "delete from employees where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}
	
	// DELETE JOB History
	@Override
	public void deleteJobHistory(int empId) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}
	
	// UPDATE MANAGER (NULL)
	@Override
	public void updateManager(int empId) {
		String sql = "update employees e set e.manager_id=null "
				+ "where manager_id=(select employee_id from employees where employee_id=?)";
		jdbcTemplate.update(sql, empId);
	}
	
	// GET ALL Department ID
	@Override
	public List<Map<String, Object>> getAllDeptId(){
		String sql = "select department_id as departmentId,"
				+ " department_name as departmentName from departments";
		return jdbcTemplate.queryForList(sql);
	}
	
	// GET ALL Manager ID
	@Override
	public List<Map<String, Object>> getAllManagerId() {
		String sql = "select employee_id as managerId,"
				+ "first_name||' '||last_name as managerName "
				+ "from employees where employee_id in "
				+ "(select distinct manager_id from employees)";
		return jdbcTemplate.queryForList(sql);
	}

	// GET ALL Job ID
	@Override
	public List<JobVO> getAllJobId(){
		String sql = "select job_id as jobId, job_title as jobTitle "
				+ "from jobs";
		return jdbcTemplate.query(sql, new RowMapper<JobVO>(){
			@Override
			public JobVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobVO job = new JobVO();
				job.setJobId(rs.getString(1));
				job.setJobTitle(rs.getString(2));
				return job;
			}
		});
	}


	
}
