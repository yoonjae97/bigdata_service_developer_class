package com.smart.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.dto.RegisterDTO;
import com.smart.home.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	// Service Interface를 객체로 생성해주는 방법
	// @Inject
	@Autowired // 객체를 생성하여 DI(의존성주입)해준다.
	RegisterService service;

	@GetMapping("/regForm")
	public String regForm() {
		return "register/registerForm";
	}

	@PostMapping("/registerOk")
	public ModelAndView regOk(RegisterDTO dto) {
		// 데이터 request
		// db insert
		int result = 0;
		try {
			result = service.registerInsert(dto);
		} catch (Exception e) {
			System.out.println("회원가입실패" + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// 1: 성공 : 홈으로
			// 컨트롤러 매핑에서 다른 매핑 주소로 이동
			mav.setViewName("redirect:/");
			// homecontroller의 / 매핑 메소드 실행
			// jsp로 가는것 아님
		} else { // 0: 실패 : 회원가입으로
			mav.setViewName("register/registerResult");
			// jsp로 보냄
		}
		return mav;
	}

	// 로그인 폼
	@GetMapping("/login")
	public String login() {
		return "register/login";
	}

	// 로그인 DB조회가 목적 - id, name 가져올 것
	@PostMapping("/loginOk")
	public ModelAndView loginOk(String userid, String userpwd, HttpSession session) { // loginform의 name과 같게 해야 자동으로
																						// request된다.

		// dto 일치하는 정보가 있으면 id, name
		// 일치하는 정보가 없으면 null
		RegisterDTO dto = service.loginOk(userid, userpwd);

		ModelAndView mav = new ModelAndView();
		if (dto != null) { // 성공
			// 세션에 아이디, 이름, 로그인 상태 기록
			session.setAttribute("logId", dto.getUserid());
			session.setAttribute("logName", dto.getUsername());
			session.setAttribute("logStatus", "Y");

			mav.setViewName("redirect:/");
		} else { // 실패
			// 로그인 폼으로 이동하기
			mav.setViewName("redirect:login");
		}
		return mav;
	}

	// 로그아웃 : 세션 객체 제거 -> 세션 영역에 보관된 변수가 지워지고 새로운 세션이 할당됨
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@GetMapping("regIdFindPage")
	public String regIdFindPage() {
		return "register/regIdFindPage";
	}
	
	
	@PostMapping("/regIdFind")
	@ResponseBody
	public Map<String, String> regIdFind(String namefind, String emailfind) {
	    String id = findId(namefind, emailfind);

	    Map<String, String> response = new HashMap<String, String>();
	    response.put("id", id);

	    return response;
	}

	
	  private String findId(String namefind, String emailfind) {
	  
	  RegisterDTO dto = service.findId(namefind, emailfind);
	  System.out.println(dto.toString()); String id = dto.getUserid(); 
	  return id;
	  
	  }
	 
}
