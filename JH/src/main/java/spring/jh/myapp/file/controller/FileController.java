package spring.jh.myapp.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.jh.myapp.file.dao.IFileService;
import spring.jh.myapp.file.model.FileVO;

@Controller
public class FileController {

	@Autowired
	IFileService fileService;
	
	@RequestMapping("/file")
	public ModelAndView fileHome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/file/index");
		return mav;
	}
	
	@GetMapping("/file/new")
	public String uploadFile(Model model) {
		model.addAttribute("dir","/");
		return "/file/form";
	}
	
	@PostMapping("/file/new")
	public String uploadFile(@RequestParam(value="dir", required=false,
	defaultValue="/") String dir, @RequestParam MultipartFile file,
			RedirectAttributes redirectAttrs) {
		try {
			if(file!=null && !file.isEmpty()) {
				FileVO newFile = new FileVO();
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
	
	@GetMapping("/file/info")
	public void getFileInfo(int fileId, Model model) {
		model.addAttribute("file", fileService.getFile(fileId));
	}
}
