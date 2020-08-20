package com.coderby.myapp.member.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderby.myapp.member.model.MemberVO;
import com.coderby.myapp.member.service.IMemberService;

@Controller
public class LoginController {

	@Autowired
	IMemberService memberService;

	@RequestMapping("/loginCheck")
	public String loginCheck(Model model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		session.removeAttribute("message");
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return "redirect:/";
		}else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))){
			return "redirect:/";
		}else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
			session.setAttribute("startTime", LocalDateTime.now());
			String url = "/";
			if(session.getAttribute("url")!=null) {
				url = (String)session.getAttribute("url");
			}
			return "redirect:/"+url;
		}else {
			model.addAttribute("message","로그인 되지 않았습니다.");
			return "/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@Autowired
	private FilterChainProxy filterChainProxy;

	@RequestMapping("/filterChain")
	public @ResponseBody Map<Integer, String> getSecurityFilterChainProxy(){
		return this.getSecurityFilterChainProxyPrint();
	}

	public Map<Integer, String> getSecurityFilterChainProxyPrint(){
		Map<Integer, String> filterChains= new HashMap<Integer, String>();
		int i = 1;
		for(SecurityFilterChain secfc :  this.filterChainProxy.getFilterChains()){
			for(Filter filter : secfc.getFilters()){
				filterChains.put(i++, filter.getClass().getName());
			}
		}
		return filterChains;
	}

}
