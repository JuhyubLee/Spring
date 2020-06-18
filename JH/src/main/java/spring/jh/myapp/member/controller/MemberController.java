package spring.jh.myapp.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.jh.myapp.member.model.MemberVO;
import spring.jh.myapp.member.service.IMemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	
	
	@GetMapping("/insert")
	public void insert(Model model) {
		model.addAttribute("mem", new MemberVO());
		model.addAttribute("message", "insert");
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute("mem")  @Valid MemberVO member, Model model,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
		model.addAttribute("message", "insert");
		return "member/insert";
		}
		member.setPassword(bpe.encode(member.getPassword()));
		member.setEnabled(1);
		memberService.insertMember(member);
		redirectAttributes.addFlashAttribute("message","회원 가입 완료");
		return "redirect:/login";
	}

	@RequestMapping("/{userId}")
	public String view(String userId, Model model) {
		MemberVO mem = memberService.getMember(userId);
		model.addAttribute("mem", mem);
		return "member/view";

	}
	
	@GetMapping("/update")
	public String updateMem(String userId, Model model) {
		model.addAttribute("mem", memberService.getMember(userId));
		model.addAttribute("message", "update");
		return "member/insert";
	}
	
	@PostMapping("/update")
	public String updateMem(MemberVO mem, Model model,
			RedirectAttributes redirectAttributes) {
		mem.setPassword(bpe.encode(mem.getPassword()));
		mem.setEnabled(1);
		memberService.updateMem(mem);
		redirectAttributes.addFlashAttribute("message","회원 수정 완료");
		return "redirect:/member/view?userId="+mem.getUserId();
	}
	
	@GetMapping("/delete")
	public String deleteMem(String password, Model model, String userId) {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		String dbpw = memberService.getPassword(authentication.getName());
		if(!bpe.matches(password, dbpw)) {
			model.addAttribute("message", "wrong");
			return "member/delete";
		}
		model.addAttribute("message", "right");
		return "member/delete";
	}
	
	@PostMapping("/delete")
	public String deleteMem(String userId, Model model, HttpSession session) {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		memberService.deleteMem(authentication.getName());
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/list")
	public void getAllMembers(Model model) {
		List<MemberVO> memList = memberService.getMemList();
		model.addAttribute("memList", memList);
	}
	

}
