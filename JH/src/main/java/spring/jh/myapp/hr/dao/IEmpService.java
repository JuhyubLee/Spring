package spring.jh.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.jh.myapp.hr.model.DeptVO;
import spring.jh.myapp.hr.model.EmpVO;
import spring.jh.myapp.hr.model.JobVO;

public interface IEmpService {
	
	int getEmpCount();
	int getEmpCount(int deptId);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empId);
	DeptVO getDeptInfo(int deptId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(int empId);
	void deleteJobHistory(int empId);
	void updateManager(@Param("empId") int empId);
	List<Map<String,Object>> getAllDeptId();
	List<Map<String,Object>> getAllManagerId();
	List<EmpVO> getTopSalary();
	List<JobVO> getAllJobId();
	
}
