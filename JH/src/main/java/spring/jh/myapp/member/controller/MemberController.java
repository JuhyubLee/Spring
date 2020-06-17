package spring.jh.myapp.member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
		model.addAttribute("memmessage", "insert");
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute("mem")  @Valid MemberVO member, Model model,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
		model.addAttribute("memmessage", "insert");
		return "member/insert";
		}
		member.setPassword(bpe.encode(member.getPassword()));
		member.setEnabled(1);
		memberService.insertMember(member);
		redirectAttributes.addFlashAttribute("memmessage","회원 가입 완료");
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
		model.addAttribute("memmessage", "update");
		return "member/insert";
	}
	
	@PostMapping("/update")
	public String updateMem(MemberVO mem, Model model,
			RedirectAttributes redirectAttributes) {
		mem.setPassword(bpe.encode(mem.getPassword()));
		mem.setEnabled(1);
		memberService.updateMem(mem);
		redirectAttributes.addFlashAttribute("memmessage","회원 수정 완료");
		return "redirect:/member/view?userId="+mem.getUserId();
	}
	
	@GetMapping("/delete")
	public String deleteMem(String userId, Model model) {
		model.addAttribute("mem", memberService.getMember(userId));
		return "member/delete";
	}
	
	@PostMapping("/delete")
	public String deleteMem(Model model, String userId) {
		memberService.deleteMem(userId);
		return "redirect:/hr/login";
	}

}
