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
