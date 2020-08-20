package com.coderby.myapp.file.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.file.model.FileVO;
import com.coderby.myapp.file.service.IFileService;
import com.coderby.myapp.member.model.MemberVO;

@Controller
@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MASTER')")
public class FileController {

	@Autowired
	IFileService fileService;

	@RequestMapping("/file")
	public ModelAndView fileHome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/file/index");
		return mav;
	}

	@GetMapping("/file/info")
	public void getFileInfo(int fileId, Model model) {
		model.addAttribute("file",fileService.getFile(fileId));
	}

	@GetMapping("/file/new")
	public String uploadFile(Model model) {
		model.addAttribute("dir","/");
		return "/file/form";
	}

	@PostMapping("/file/new")
	public String uploadFile(@RequestParam(value="dir", required=false, defaultValue="/") String dir,
			@RequestParam MultipartFile file, @AuthenticationPrincipal Object principal, RedirectAttributes redirectAttrs) {
		try {
			if(file!=null && !file.isEmpty()) {
				MemberVO member = (MemberVO)principal;
				FileVO newFile = new FileVO();
				newFile.setUserId(member.getUserId());
				newFile.setDirectoryName(dir);
				newFile.setFileName(file.getOriginalFilename());
				newFile.setFileSize(file.getSize());
				newFile.setFileContentType(file.getContentType());
				newFile.setFileData(file.getBytes());
				fileService.uploadFile(newFile);
			}
		}catch(IOException e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/file/list";
	}

	@RequestMapping("/file/list")
	public String getFileList(Model model) {
		model.addAttribute("fileList",fileService.getAllFileList());
		return "/file/list";
	}

	@RequestMapping("/file/list/{dir}")
	public String getFileListByDir(@PathVariable String dir, Model model) {
		model.addAttribute("fileList", fileService.getFileList("/"+dir));
		return "/file/list";
	}

	@GetMapping({"/img/{fileId}","/pds/{fileId}"})
	public ResponseEntity<byte[]> getImageFile(@PathVariable int fileId){
		FileVO file = fileService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		if(file != null) {
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
			headers.setContentDispositionFormData("attachment", file.getFileName());
			headers.setContentLength(file.getFileSize());
			return new ResponseEntity<byte[]>(file.getFileData(),headers,HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/file/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId, String userId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			
		}else {
				if(!(auth.getName().equals(userId))) {
					throw new RuntimeException("파일 삭제는 관리자 또는 업로더만 가능합니다.");
				}
		}
		fileService.deleteFile(fileId);
		return "redirect:/file/list";
	}

	@RequestMapping("/file/updateDir")
	public String updateDirectory(int[] fileIds, String[] userId, String directoryName) {
		System.out.println(Arrays.toString(fileIds));
		System.out.println(Arrays.toString(userId));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			
		}else {
			for(String fileUserId : userId) {
				if(!(auth.getName().equals(fileUserId))) {
					throw new RuntimeException("파일 수정은 관리자 또는 업로더만 가능합니다.");
				}
			}
		}
		fileService.updateDirectory(fileIds, directoryName);
		return "redirect:/file/list";
	}

	@ExceptionHandler(RuntimeException.class)
	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", ex);
		return "error/runtime";
	}

}
