package com.coderby.myapp.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.hr.dao.IEmpService;
import com.coderby.myapp.hr.model.EmpDetailVO;
import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.util.EmpValidator;

@Controller
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
	@RequestMapping("/hr/count")
	public String empCount(@RequestParam(value="deptId", required=false, defaultValue="0")
	int deptId, Model model) {
		if(deptId==0) {
			model.addAttribute("count",empService.getEmpCount());
		}else {
			model.addAttribute("count",empService.getEmpCount(deptId));
		}
		return "hr/count";
	}
	
	@RequestMapping(value="/hr/list")
	public void getAllEmployees(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList",empList);
	}

	@RequestMapping(value="/hr/{employeeId}")
	public String getEmployees(@PathVariable int employeeId, Model model) {
		EmpDetailVO emp = (EmpDetailVO)empService.getEmpInfo(employeeId);
		model.addAttribute("emp",emp);
		return "hr/view";
	}

	@GetMapping(value="/hr/insert")
	public String insertEmp(Model model) {
		model.addAttribute("emp", new EmpVO());
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message","insert");
		return "hr/insert";
	}

	@PostMapping(value="/hr/insert")
	public String insertEmp(@ModelAttribute("emp") @Valid EmpVO emp, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("jobList",empService.getAllJobId());
			model.addAttribute("manList",empService.getAllManagerId());
			model.addAttribute("deptList",empService.getAllDeptId());
			model.addAttribute("message","insert");
			return "hr/insert";
		}
			empService.insertEmp(emp);
			redirectAttributes.addFlashAttribute("message", "회원 저장 완료");
		return "redirect:/hr/list";
	}

	@RequestMapping(value="/hr/update")
	public String updateEmp(int empId, Model model) {
		model.addAttribute("emp",empService.getEmpInfo(empId));
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message","update");
		return "hr/insert";
	}

	@PostMapping(value="/hr/update")
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}

	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping(value="/hr/delete")
	public String deleteEmp(int empId, Model model) {
		model.addAttribute("emp",empService.getEmpInfo(empId));
		model.addAttribute("count",empService.getUpdateCount(empId));
		return "hr/delete";
	}
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping(value="/hr/delete")
	public String deleteEmp(Model model, int empId) {
		empService.deleteEmp(empId);
		return "redirect:/hr/list";
	}
	
//	@ExceptionHandler(RuntimeException.class)
//	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
//		model.addAttribute("url", request.getRequestURI());
//		model.addAttribute("exception", ex);
//		return "error/runtime";
//	}
	
	@RequestMapping("/hr/index")
	public String getMain() {
		return "hr/index";
	}
	
	@GetMapping(value="/hr/getMaxSalary")
	public String getMaxSalaryByDept(Model model) {
		model.addAttribute("empList",empService.getEmpByMaxSalary());
		return "hr/list";
	}
	
	@RequestMapping(value="/hr/json/list")
	public @ResponseBody List<EmpVO> getAllEmployees() {
		List<EmpVO> empList = empService.getEmpList();
		return empList;
	}

	@RequestMapping(value="/hr/json/{employeeId}")
	public @ResponseBody EmpVO getEmployees(@PathVariable int employeeId) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		return emp;
	}
	
}
















