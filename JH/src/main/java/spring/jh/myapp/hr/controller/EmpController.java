package spring.jh.myapp.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.jh.myapp.hr.dao.EmpRepository;
import spring.jh.myapp.hr.dao.IEmpService;
import spring.jh.myapp.hr.model.EmpVO;


@Controller
public class EmpController {

	@Autowired
	IEmpService empService;
	
	@GetMapping("/hr/juhyub")
	public String getUser(Model model) {
		model.addAttribute("user", empService.getEmpInfo(102));
		return "home";
	}
	
	
}
