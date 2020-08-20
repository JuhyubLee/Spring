package spring.jh.myapp.member.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.jh.myapp.hr.model.JSONVO;

@Controller
@RequestMapping("/json")
public class JSONController {
	
	@ResponseBody
	@RequestMapping(value="/ex",produces="application/json;charset=UTF-8")
	public String exam(JSONVO jsonVO, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		return "name : "+jsonVO.getName()+", age : "+jsonVO.getAge();
	}
}
