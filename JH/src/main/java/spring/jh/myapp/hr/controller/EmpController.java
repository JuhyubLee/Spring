package spring.jh.myapp.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.jh.myapp.hr.dao.EmpRepository;
import spring.jh.myapp.hr.dao.IEmpService;
import spring.jh.myapp.hr.model.EmpVO;


@Controller
@RequestMapping("/hr")
public class EmpController {

	@Autowired
	IEmpService empService;
	
	@RequestMapping("/count")
	public String empCount(@RequestParam(value="deptId",
	required=false, defaultValue="0") int deptId, Model model) {
		if(deptId==0) {
			model.addAttribute("count", empService.getEmpCount());
		} else {
			model.addAttribute("count", empService.getEmpCount(deptId));
		}
		return "hr/count";
	}
	
	@RequestMapping("/list")
	public void getAllEmployees(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
	
	}
	
	
	@RequestMapping("/top")
	public void getTopSalary(Model model) {
		List<EmpVO> empTop = empService.getTopSalary();
		model.addAttribute("empTop", empTop);
	}
	
	@RequestMapping("/{employeeId}")
	public String getEmployees(@PathVariable int employeeId, Model model) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp",emp);
		return "hr/view";
	}
	
	@GetMapping("/insert")
	public String insertEmp(Model model) {
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "insert");
		return "hr/insertform";
	}
	
	@PostMapping("/insert")
	public String insertEmp(EmpVO emp, Model model) {
		empService.insertEmp(emp);
		return "redirect:/hr/list";
	}
	
	@GetMapping("/update")
	public String updateEmp(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "update");
		return "hr/insertform";
	}
	
	@PostMapping("/update")
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}
	

	
	
	
}
