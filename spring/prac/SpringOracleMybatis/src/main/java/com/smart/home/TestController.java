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
	// get방식 접속
	// /test로 서버에 접속하면 호출되는 메소드
//	@RequestMapping(value="/test", method=RequestMethod.GET)
	@RequestMapping("/test")
	// view로 보낼때는 model에 데이터를 입력
	public String test(int num, String name, Model model) {
		System.out.println("num"+(num+20));
		System.out.println("name"+name);
		
		// 서버에서 클라이언트 (뷰페이지로 정보 보내기)
		model.addAttribute("name", name);
		model.addAttribute("tel", "010-9999-8888");
		
		return "home";
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public ModelAndView test2(HttpServletRequest req) {
		int no = Integer.parseInt(req.getParameter("no"));
		String id = req.getParameter("id");
		System.out.println(no+", "+id);
		
		// 데이터와 뷰페이지명을 관리하는 객체
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("no", no);	
		mav.addObject("id", id);
		
		mav.setViewName("home");
		return mav;
	}
	
	// GET 방식 접속 전용 매핑 어노테이션
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
	
	// Post 방식의 매핑
	// @PostMapping("/test4")
	@RequestMapping(value="/test4", method=RequestMethod.POST)
	public String test4(@RequestParam("username") String username,
					 	@RequestParam("addr") String address) {
		// post방식의 한글전송시 한글처리는 web.xml에 인코딩 필터태그를 설정하여 해결한다.
		System.out.println(username+", "+address);
		
		return "home";
	}
	
	@GetMapping("/test5/{page}")
	public String test5(@PathVariable("page") int page) {
		System.out.println(page+"");
		
		return "home";
	}
	
}
