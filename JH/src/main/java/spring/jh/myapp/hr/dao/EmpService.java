package spring.jh.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.jh.myapp.hr.model.DeptVO;
import spring.jh.myapp.hr.model.EmpVO;
import spring.jh.myapp.hr.model.JobVO;

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
public List<EmpVO> getEmpList(){
	return empRepository.getEmpList();
}

@Override
public EmpVO getEmpInfo(int empId) {
	return empRepository.getEmpInfo(empId);
}

@Override
public DeptVO getDeptInfo(int deptId) {
	return empRepository.getDeptInfo(deptId);
}

@Override
public List<EmpVO> getTopSalary() {
	return empRepository.getTopSalary();
}

@Override
@Transactional("txManager")
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
	empRepository.updateManager(empId);
	empRepository.deleteEmp(empId);
}

@Override
public void deleteJobHistory(int empId) {
	empRepository.deleteJobHistory(empId);
}
@Override
public void updateManager(int empId) {
	empRepository.updateManager(empId);
}

@Override
public List<Map<String, Object>> getAllDeptId(){
	return empRepository.getAllDeptId();
}

@Override
public List<Map<String, Object>> getAllManagerId(){
	return empRepository.getAllManagerId();
}

@Override
public List<JobVO> getAllJobId(){
	return empRepository.getAllJobId();
}

}
