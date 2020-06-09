package spring.jh.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import spring.jh.myapp.hr.model.DeptVO;
import spring.jh.myapp.hr.model.EmpVO;

public interface IEmpRepository {

	int getEmpCount();
	int getEmpCount(int deptId);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empId);
	DeptVO getDeptInfo(int deptId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(int empId);
	void deleteJobHistory(int empId);
	void updateManager(int empId);
	List<Map<String,Object>> getAllDeptId();
	List<Map<String,Object>> getAllJobId();
	List<Map<String,Object>> getAllManagerId();
	List<EmpVO> getTopSalary();
}
