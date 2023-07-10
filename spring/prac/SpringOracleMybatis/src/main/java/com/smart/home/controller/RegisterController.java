package com.smart.home.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
	// Service Interface�� ��ü�� �������ִ� ���
	// @Inject
	@Autowired // ��ü�� �����Ͽ� DI(����������)���ش�.
	RegisterService service;

	@Autowired
	JavaMailSenderImpl mailSender;
	
	@GetMapping("/regForm")
	public String regForm() {
		return "register/registerForm";
	}

	@PostMapping("/registerOk")
	public ModelAndView regOk(RegisterDTO dto) {
		// ������ request
		// db insert
		int result = 0;
		try {
			result = service.registerInsert(dto);
		} catch (Exception e) {
			System.out.println("ȸ�����Խ���" + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// 1: ���� : Ȩ����
			// ��Ʈ�ѷ� ���ο��� �ٸ� ���� �ּҷ� �̵�
			mav.setViewName("redirect:/");
			// homecontroller�� / ���� �޼ҵ� ����
			// jsp�� ���°� �ƴ�
		} else { // 0: ���� : ȸ����������
			mav.setViewName("register/registerResult");
			// jsp�� ����
		}
		return mav;
	}

	// �α��� ��
	@GetMapping("/login")
	public String login() {
		return "register/login";
	}

	// �α��� DB��ȸ�� ���� - id, name ������ ��
	@PostMapping("/loginOk")
	public ModelAndView loginOk(String userid, String userpwd, HttpSession session) { // loginform�� name�� ���� �ؾ� �ڵ�����
																						// request�ȴ�.

		// dto ��ġ�ϴ� ������ ������ id, name
		// ��ġ�ϴ� ������ ������ null
		RegisterDTO dto = service.loginOk(userid, userpwd);

		ModelAndView mav = new ModelAndView();
		if (dto != null) { // ����
			// ���ǿ� ���̵�, �̸�, �α��� ���� ���
			session.setAttribute("logId", dto.getUserid());
			session.setAttribute("logName", dto.getUsername());
			session.setAttribute("logStatus", "Y");

			mav.setViewName("redirect:/");
		} else { // ����
			// �α��� ������ �̵��ϱ�
			mav.setViewName("redirect:login");
		}
		return mav;
	}

	// �α׾ƿ� : ���� ��ü ���� -> ���� ������ ������ ������ �������� ���ο� ������ �Ҵ��
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	// ���̵� ã��(��)
	@GetMapping("/idSearch")
	public String idSearchForm() {
		return "register/idSearch";
	}
	
	@PostMapping("/idSearchOk")
	@ResponseBody
	public String idSearchOk(RegisterDTO dto) {
		System.out.println(dto.toString());
		// �̸�, ����ó�� ��ġ�ϴ� ���̵�� �̸��� ã��
		RegisterDTO resultDTO = service.idSearch(dto);
		String resultTxt = "N";
		System.out.println(resultDTO.toString());
		if (resultDTO != null) { // ��ġ�ϴ� ���� ������
			// �̸��� ������
			
			String subject = "���̵� ã�� ���";
			String content = "<div style='background:pink; "
					+ "border:1px solid #ddd; padding:50px; text-align:center;'>";
			content += "�˻��� ���̵�� :" + resultDTO.getUserid();
			content += "</div>";
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				messageHelper.setFrom("winterer601@naver.com");
				messageHelper.setTo("mistyone19@naver.com");
				messageHelper.setSubject(subject);
				messageHelper.setText("text/html;charset=UTF-8", content);
				mailSender.send(message);
				resultTxt = "Y";
			} catch (Exception e) {
				e.printStackTrace();
			}			
		} else {
			resultTxt = "N";
		}
		return resultTxt;
	}
	 
}
