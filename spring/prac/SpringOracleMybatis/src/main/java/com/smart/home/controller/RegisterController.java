package com.smart.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
			System.out.println("ȸ�����Խ���" +e.getMessage());
		}
		
		
		ModelAndView mav = new ModelAndView();
		if (result>0){// 1: ���� : Ȩ����
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
}