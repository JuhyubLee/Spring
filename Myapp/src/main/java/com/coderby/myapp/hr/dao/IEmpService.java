package com.coderby.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.hr.model.JobVO;

public interface IEmpService {

	int getEmpCount();
	int getEmpCount(int deptId);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(int empId);
	void deleteJobHistory(int empId);
	void updateManagers(int empId);
	List<EmpVO> getEmpListByName(String name);
	List<Map<String,Object>> getAllDeptId();
	List<JobVO> getAllJobId();
	List<Map<String,Object>> getAllManagerId();
	List<EmpVO> getEmpByMaxSalary();
	Map<String, Integer> getUpdateCount(int empId);
}
