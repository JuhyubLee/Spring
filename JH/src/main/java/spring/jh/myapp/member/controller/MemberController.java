package spring.jh.myapp.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import spring.jh.myapp.file.dao.FileService;
import spring.jh.myapp.member.model.Pagination;
import spring.jh.myapp.member.model.MemberVO;
import spring.jh.myapp.member.service.IMemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	
	
	@RequestMapping("/insert/idCheck")
	@ResponseBody
	public boolean idCheck(String userId) {
		return memberService.checkId(userId);
	}
	
	// GET MEMBER INSERT
	@GetMapping("/insert")
	public void insert(Model model) {
		model.addAttribute("mem", new MemberVO());
		model.addAttribute("message", "insert");
	}
	
	// POST MEMBER INSERT
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

	// SELECT MEMBER VIEW
	@PreAuthorize("isAuthenticated() and #userId==principal.username or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@RequestMapping("/{userId}")
	public String view(String userId, Model model) {
		MemberVO mem = memberService.getMember(userId);
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			model.addAttribute("message", "user");
		}else {
			model.addAttribute("message", "admin");
		}
		model.addAttribute("mem", mem);
		return "member/view";

	}
	
	// MEMBER ENABLE UPDATE
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@RequestMapping("/updateEna")
	public String udpateEnable(Model model, String userId) {
		MemberVO mem = memberService.getMember(userId);
		if(mem.getEnabled() == 1) {			
			mem.setEnabled(0);
		} else {
			mem.setEnabled(1);
		}
		memberService.updateEnable(mem);
		return "redirect:/member/view?userId="+mem.getUserId();
	}
	
	
	// MEMBER Authority UPDATE
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MASTER')")
	@RequestMapping("/updateAu")
	public String updateAuthority(String userId,  String selectAu) {
		MemberVO mem = memberService.getMember(userId);
		mem.setAuth(selectAu);
		memberService.updateAuthority(mem);
		return "redirect:/member/view?userId="+mem.getUserId();
	}
	
	// GET MEMBER UPDATE
	@PreAuthorize("isAuthenticated() and #userId==principal.username or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping("/update")
	public String updateMem(String userId, Model model) {
		model.addAttribute("mem", memberService.getMember(userId));
		model.addAttribute("message", "update");
		return "member/insert";
	}
	
	// POST MEMBER UPDATE
	@PreAuthorize("isAuthenticated() and #userId==principal.username or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping("/update")
	public String updateMem(MemberVO mem, Authentication auth,
			RedirectAttributes redirectAttributes) {
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			
		}else {
			if(bpe.matches(mem.getPassword(), memberService.getPassword(mem.getUserId()))) {
				
			}else {
				throw new RuntimeException("비밀번호가 다릅니다.");
			}
		}
		memberService.updateMem(mem);
		redirectAttributes.addFlashAttribute("message","회원 수정 완료");
		return "redirect:/member/view?userId="+mem.getUserId();
	}
	
	
	// GET MEMBER DELETE
	@GetMapping("/delete")
	public String deleteMem(String password, Model model, String userId) {
		String dbpw = memberService.getPassword(userId);
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				
				|| authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
		}else {
			if(!bpe.matches(password, dbpw)) {
				model.addAttribute("message", "wrong");
				return "member/delete";
			}
		}
		
		model.addAttribute("message", "right");
		model.addAttribute("mem", memberService.getMember(userId));
		return "member/delete";
	}
	
	
	// POST MEMBER DELETE
	@PreAuthorize("isAuthenticated() and #userId == principal.username or hasRole('ROLE_ADMIN')")
	@PostMapping("/delete")
	public String deleteMem(String userId, Model model, HttpSession session) {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		
		memberService.deleteMem(userId);
		if(authentication.getPrincipal().equals(userId)) {			
			session.invalidate();
		}
		return "redirect:/member/list";
	}
	
	// VIEW MEMBER LIST
	@RequestMapping("/list")
	public void getAllMembers(@RequestParam(defaultValue="1")int page, MemberVO mem, Model model)throws Exception {
		int listCnt = memberService.getMemListCnt(mem);
		Pagination pagination = new Pagination(listCnt, page);
		mem.setSetStartIndex(pagination.getStartIndex());
		mem.setSetCntPerPage(pagination.getPageSize());
		List<MemberVO> memList = memberService.getMemList(page);
		model.addAttribute("memList", memList);
		model.addAttribute("listCnt", listCnt);
		model.addAttribute("pagination", pagination);
	}
}
