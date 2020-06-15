package spring.jh.myapp.member.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.jh.myapp.member.model.MemberVO;
import spring.jh.myapp.member.service.IMemberService;

@Controller
public class LoginController {

	@Autowired
	IMemberService memberService;
	
	@GetMapping("/login")
	public void login(Model model) {
	}
	
	@PostMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("/loginCheck")
	public String loginCheck(Model model, HttpSession session) {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
				System.out.println(authentication.getPrincipal());
				System.out.println(authentication.getCredentials());
				System.out.println(authentication.getAuthorities());
				System.out.println(authentication.isAuthenticated());
		if(!(authentication.getDetails() instanceof MemberVO)) {
			model.addAttribute("message","아이디 또는 비밀번호가 다릅니다.");
			return "login";
		}else {
			MemberVO member = (MemberVO)authentication.getDetails();
			session.setAttribute("userId", member.getUserId());
			session.setAttribute("auth", member.getAuth());
			session.setAttribute("startTime", LocalDateTime.now());
			String url = "/";
			if(session.getAttribute("url")!=null) {
				url = (String)session.getAttribute("url");
			}
			return "redirect:"+url;
		}
	}
}
