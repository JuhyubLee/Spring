package com.coderby.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.hr.model.JobVO;

@Service
public class EmpService implements IEmpService{

	@Autowired
	IEmpRepository empRepository;
	
	@Override
	public int getEmpCount() {
		return empRepository.getEmpCount();
	}

	@Override
	public int getEmpCount(int deptId) {
		return empRepository.getEmpCount(deptId);
	}

	@Override
	public List<EmpVO> getEmpList() {
		return empRepository.getEmpList();
	}

	@Override
	public EmpVO getEmpInfo(int empId) {
		return empRepository.getEmpInfo(empId);
	}

	@Override
	@Transactional(value="txManager")
	public void updateEmp(EmpVO emp) {
		empRepository.deleteJobHistory(emp.getEmployeeId());
		empRepository.updateEmp(emp);
	}

	@Override
	public void insertEmp(EmpVO emp) {
		 empRepository.insertEmp(emp);
	}

	@Override
	@Transactional("txManager")
	public void deleteEmp(int empId) {
		empRepository.deleteJobHistory(empId);
		empRepository.updateManagers(empId);
		empRepository.deleteEmp(empId);
	}

	@Override
	public void deleteJobHistory(int empId) {
		empRepository.deleteJobHistory(empId);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		return empRepository.getAllDeptId();
	}

	@Override
	public List<JobVO> getAllJobId() {
		return empRepository.getAllJobId();
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		return empRepository.getAllManagerId();
	}

	@Override
	public void updateManagers(int empId) {
		empRepository.updateManagers(empId);
	}

	@Override
	public List<EmpVO> getEmpListByName(String name) {
		return empRepository.getEmpListByName(name);
	}

	@Override
	public List<EmpVO> getEmpByMaxSalary() {
		return empRepository.getEmpByMaxSalary();
	}

	@Override
	public Map<String, Integer> getUpdateCount(int empId) {
		return empRepository.getUpdateCount(empId);
	}

	
}
