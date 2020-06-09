package spring.jh.myapp.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

import spring.jh.myapp.hr.dao.EmpRepository;
import spring.jh.myapp.hr.dao.IEmpService;
import spring.jh.myapp.hr.model.EmpVO;
import spring.jh.myapp.util.EmpValidator;


@Controller
@RequestMapping("/hr")
public class EmpController {

	@Autowired
	IEmpService empService;
	
	@Autowired
	private EmpValidator empValidator;
	
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
	public void insertEmp(Model model) {
		model.addAttribute("emp", new EmpVO());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "insert");
	}
	
	@PostMapping("/insert")
	public String insertEmp(@ModelAttribute("emp") @Validated EmpVO emp, 
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("jobList", empService.getAllJobId());
			model.addAttribute("manList", empService.getAllManagerId());
			model.addAttribute("deptList", empService.getAllDeptId());
			model.addAttribute("message", "insert");
		}
		empService.insertEmp(emp);
		redirectAttributes.addFlashAttribute("message", "회원 저장 완료");
		return "redirect:/hr/list";
	}
	
	@GetMapping("/update")
	public String updateEmp(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "update");
		return "hr/insert";
	}
	
	@PostMapping("/update")
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException(HttpServletRequest request, Exception ex, Model model){
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", ex);
		return "error/runtime";
	}
	
	// DELETE METHOD
	@GetMapping("/delete")
	public String deleteEmp(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		return "hr/delete";
	}
	
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
	
	@RequestMapping("/json/{employeeId}")
	public @ResponseBody EmpVO getEmployees(@PathVariable int employeeId) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		return emp;
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(empValidator);
	}
	
	
	
	
	
}
