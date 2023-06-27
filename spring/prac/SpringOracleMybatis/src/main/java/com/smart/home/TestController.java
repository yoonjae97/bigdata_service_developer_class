package com.smart.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	// get��� ����
	// /test�� ������ �����ϸ� ȣ��Ǵ� �޼ҵ�
//	@RequestMapping(value="/test", method=RequestMethod.GET)
	@RequestMapping("/test")
	// view�� �������� model�� �����͸� �Է�
	public String test(int num, String name, Model model) {
		System.out.println("num"+(num+20));
		System.out.println("name"+name);
		
		// �������� Ŭ���̾�Ʈ (���������� ���� ������)
		model.addAttribute("name", name);
		model.addAttribute("tel", "010-9999-8888");
		
		return "home";
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public ModelAndView test2(HttpServletRequest req) {
		int no = Integer.parseInt(req.getParameter("no"));
		String id = req.getParameter("id");
		System.out.println(no+", "+id);
		
		// �����Ϳ� ������������ �����ϴ� ��ü
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("no", no);	
		mav.addObject("id", id);
		
		mav.setViewName("home");
		return mav;
	}
	
	// GET ��� ���� ���� ���� ������̼�
	@GetMapping("/test3")
	public ModelAndView test3(TestDTO dto) {
		System.out.println(dto.toString());
		dto.setNum(dto.getNum()+500);
		dto.setUsername(dto.getUsername());
		dto.setTel(dto.getTel());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		
		mav.setViewName("home");
		
		return mav;	
	}
	
	// Post ����� ����
	// @PostMapping("/test4")
	@RequestMapping(value="/test4", method=RequestMethod.POST)
	public String test4(@RequestParam("username") String username,
					 	@RequestParam("addr") String address) {
		// post����� �ѱ����۽� �ѱ�ó���� web.xml�� ���ڵ� �����±׸� �����Ͽ� �ذ��Ѵ�.
		System.out.println(username+", "+address);
		
		return "home";
	}
	
	@GetMapping("/test5/{page}")
	public String test5(@PathVariable("page") int page) {
		System.out.println(page+"");
		
		return "home";
	}
	
}
