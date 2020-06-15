package spring.jh.myapp.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.jh.myapp.hr.dao.IEmpService;
import spring.jh.myapp.hr.model.EmpVO;

@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_USER') or hasAnyRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/hr")
public class EmpController {

	@Autowired
	IEmpService empService;
	
	// INDEX
	@RequestMapping("/index")
	public void index(Model model) {
	}
	
	// COUNT
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
	
	// LIST
	@RequestMapping("/list")
	public void getAllEmployees(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
	}
	
	// TOP SALARY
	@RequestMapping("/top")
	public void getTopSalary(Model model) {
		List<EmpVO> empTop = empService.getTopSalary();
		model.addAttribute("empTop", empTop);
	}
	
	// VIEW
	@RequestMapping("/{employeeId}")
	public String getEmployees(@PathVariable int employeeId, Model model) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp",emp);
		return "hr/view";
	}
	
	// GET INSERT
	@GetMapping("/insert")
	public void insertEmp(Model model) {
		model.addAttribute("emp", new EmpVO());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "insert");
	}
	
	// POST INSERT
	@PostMapping("/insert")
	public String insertEmp(@ModelAttribute("emp") @Valid EmpVO emp, 
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("jobList", empService.getAllJobId());
			model.addAttribute("manList", empService.getAllManagerId());
			model.addAttribute("deptList", empService.getAllDeptId());
			model.addAttribute("message", "insert");
			return "hr/insert";
		}
		empService.insertEmp(emp);
		redirectAttributes.addFlashAttribute("message", "회원 저장 완료");
		return "redirect:/hr/list";
	}
	
	// GET UPDATE
	@GetMapping("/update")
	public String updateEmp(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "update");
		return "hr/insert";
	}
	
	// POST UPDATE
	@PostMapping("/update")
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}
	// ERROR
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException(HttpServletRequest request, Exception ex, Model model){
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", ex);
		return "error/runtime";
	}
	
	// DELETE METHOD
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete")
	public String deleteEmp(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		return "hr/delete";
	}
	
	// AUTHORIZE
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete")
	public String deleteEmp(Model model, int empId) {
		empService.deleteEmp(empId);
		return "redirect:/hr/list";
	}
	
	// JSON
	@RequestMapping("/json/list")
	public @ResponseBody List<EmpVO> getAllEmployees(){
		List<EmpVO> empList = empService.getEmpList();
		return empList;
	}
	
	// JSON
	@RequestMapping("/json/{employeeId}")
	public @ResponseBody EmpVO getEmployees(@PathVariable int employeeId) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		return emp;
	}
	


	
	
	
	
	
}
