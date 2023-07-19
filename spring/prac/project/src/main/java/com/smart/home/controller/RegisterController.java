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
	
	// ȸ������ ������ �̵�
	@GetMapping("/regForm")
	public String regForm() {
		return "register/registerForm";
	}
	
	// ȸ������ Ȯ��
	@PostMapping("registerOk")
	public ModelAndView regOk(RegisterDTO dto) {
		int result = 0;
		try {
			result = service.registerInsert(dto);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�����Խ���" + e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView();
		if(result > 0) {
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("register/registerResult");
		}
		return mav;
	}
	
	// �α��� ȭ������ �̵�
	@GetMapping("/login")
	public String login() {
		return "register/login";
	}
	
	// �α���
	@PostMapping("/loginOk")
	public ModelAndView loginOk(String MemberId, String MemberPwd) {
		
		ModelAndView mav = new ModelAndView();
		try {
			RegisterDTO dto = service.loginOk(MemberId, MemberPwd);
			mav.addObject("logId", dto.getMemberId());
			mav.addObject("logName", dto.getMemberName());
			mav.addObject("logStatus", "Y");
			mav.setViewName("�����������ּ�");			
		} catch(Exception e) {
			e.printStackTrace();
			mav.setViewName("redirect:login");
		}		
		return mav;
		
	}
	
	// �α׾ƿ�
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("�����������ּ�");
		return mav;
	}
	
	// ���̵� ã�� ȭ������ �̵�
	@GetMapping("/findId")
	public String findIdForm() {
		return "register/findIdForm";
	}
	
	// ���̵� ã�Ƽ� ���̵� ��ȯ
	@PostMapping("/idSearchOk")
	@ResponseBody
	public String findId(String MemberName, String MemberEmail) {
		String memberId = null;
		String result = null;
		try {
			memberId = service.findId(MemberName, MemberEmail);
			if (memberId == null) {
				result = "�ش� ������ �����Ͻ� ���̵� �������� �ʽ��ϴ�.";
			} else {
				result = memberId;
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	// ���̵� �ߺ� üũ
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
	
	// ��й�ȣ ã�� ������ �̵�
	@GetMapping("/��й�ȣã��������")
	public String findPwdForm() {
		return "��й�ȣã��������";
	}
	
	// ��й�ȣ ã��
	@PostMapping("/��й�ȣã��")
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
		String title = "��й�ȣ ã�� �̸��� �Դϴ�.";
		String content =  "Ȩ�������� �湮���ּż� �����մϴ�." +
                "<br><br>" + 
                "ȸ������ ��й�ȣ�� " + pwd + "�Դϴ�.";
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

