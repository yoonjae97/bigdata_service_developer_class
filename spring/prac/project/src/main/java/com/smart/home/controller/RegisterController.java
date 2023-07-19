package com.smart.home.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

	@Autowired
	RegisterService service;
	
	@Autowired
	private JavaMailSender mailSender;
	
	// 회원가입 폼으로 이동
	@GetMapping("/regForm")
	public String regForm() {
		return "register/registerForm";
	}
	
	// 회원가입 확인
	@PostMapping("registerOk")
	public ModelAndView regOk(RegisterDTO dto) {
		int result = 0;
		try {
			result = service.registerInsert(dto);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원가입실패" + e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView();
		if(result > 0) {
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("register/registerResult");
		}
		return mav;
	}
	
	// 로그인 화면으로 이동
	@GetMapping("/login")
	public String login() {
		return "register/login";
	}
	
	// 로그인
	@PostMapping("/loginOk")
	public ModelAndView loginOk(String MemberId, String MemberPwd) {
		
		ModelAndView mav = new ModelAndView();
		try {
			RegisterDTO dto = service.loginOk(MemberId, MemberPwd);
			mav.addObject("logId", dto.getMemberId());
			mav.addObject("logName", dto.getMemberName());
			mav.addObject("logStatus", "Y");
			mav.setViewName("메인페이지주소");			
		} catch(Exception e) {
			e.printStackTrace();
			mav.setViewName("redirect:login");
		}		
		return mav;
		
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("메인페이지주소");
		return mav;
	}
	
	// 아이디 찾기 화면으로 이동
	@GetMapping("/findId")
	public String findIdForm() {
		return "register/findIdForm";
	}
	
	// 아이디 찾아서 아이디만 반환
	@PostMapping("/idSearchOk")
	@ResponseBody
	public String findId(String MemberName, String MemberEmail) {
		String memberId = null;
		String result = null;
		try {
			memberId = service.findId(MemberName, MemberEmail);
			if (memberId == null) {
				result = "해당 정보로 가입하신 아이디가 존재하지 않습니다.";
			} else {
				result = memberId;
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	// 아이디 중복 체크
	@GetMapping("/dupChk")
	@ResponseBody
	public Integer dupChk(String id) {
		Integer result = 0;
		String dupId = null;
		
		try {
			dupId = service.dupChk(id);
			if(dupId==null) {
				result = 0;
			} else {
				result = 1;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 비밀번호 찾기 페이지 이동
	@GetMapping("/비밀번호찾기페이지")
	public String findPwdForm() {
		return "비밀번호찾기페이지";
	}
	
	// 비밀번호 찾기
	@PostMapping("/비밀번호찾기")
	@ResponseBody
	public int findPwd(String MemberId, String MemberEmail) {
		String pwd = null;
		int result = 0;
		try {
			pwd = service.findPwd(MemberId, MemberEmail);
			if (pwd == null) {
				return result;
			}} catch(Exception e ) {
				e.printStackTrace();
			}
		
		String setFrom = "yoonjae9432@gmail.com";
		String toMail = MemberEmail;
		String title = "비밀번호 찾기 이메일 입니다.";
		String content =  "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" + 
                "회원님의 비밀번호는 " + pwd + "입니다.";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
			result = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
		
		
		
		
		
}

