package com.naman.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naman.domain.WebCode;
import com.naman.persistence.WebCodeRepository;
import com.naman.vo.PageMaker;
import com.naman.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/codes/")
@Log
public class WebCodeController {

	@Autowired
	private WebCodeRepository repo;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
		
		Pageable page = vo.makePageable(0, "codeno");
		
		Page<WebCode> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);
		
		log.info("page: " + page);
		log.info("result: " + result);
		
		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
		
		model.addAttribute("result", new PageMaker(result));
		
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo")WebCode vo) {
		log.info("register get");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")WebCode vo, RedirectAttributes rttr) {
		
		log.info("register post");
		log.info("" + vo);
		
		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/codes/list";
	}
	
	@GetMapping("/view")
	public void view(Integer codeno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		
		log.info("codeno: " + codeno);
		
		repo.findById(codeno).ifPresent(code -> model.addAttribute("vo", code));
		
	}
	
	@GetMapping("/modify")
	public void modify(Integer codeno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		
		log.info("MODIFY codeno: " + codeno);
		
		repo.findById(codeno).ifPresent(code -> model.addAttribute("vo", code));
	}
	
	@PostMapping("/delete")
	public String delete(Integer codeno, PageVO vo, RedirectAttributes rttr) {
		
		log.info("DELETE codeno: " + codeno);
		
		repo.deleteById(codeno);
		
		rttr.addFlashAttribute("msg", "success");
		
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/codes/list";
		
	}
	
	@PostMapping("/modify")
	public String modifyPost(WebCode code, PageVO vo, RedirectAttributes rttr) {
		
		log.info("Modify WebCode: " + code);
		
		repo.findById(code.getCodeno()).ifPresent( origin -> {
			
			origin.setCode1(code.getCode1());
			origin.setCode2(code.getCode2());
			origin.setName(code.getName());
			origin.setDescript(code.getDescript());
			
			repo.save(origin);
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("codeno", origin.getCodeno());
			});
		
			//페이징과 검색했던 결과로 이동하는 경우 
			rttr.addAttribute("page", vo.getPage());
			rttr.addAttribute("size", vo.getSize());
			rttr.addAttribute("type", vo.getType());
			rttr.addAttribute("keyword", vo.getKeyword());

			return "redirect:/codes/view";
			
		
	}
	

	@RequestMapping("/getCode")
	@ResponseBody
	public Map getCode1(String code1, Model model) {
		
		String code2 = "000";
		
		log.info("code1: " + code1);
		
		if(!StringUtils.isEmpty(repo.findByCode2Max(code1)) && !StringUtils.isEmpty(repo.findByCode2Max(code1))) {
			code2 = repo.findByCode2Max(code1);
		};
		
		log.info("result: " + repo.findByCode2Max(code1));
		
		Map result = new HashMap<>();
		result.put("code2", code2);
		
		return result;
	}
	
}
