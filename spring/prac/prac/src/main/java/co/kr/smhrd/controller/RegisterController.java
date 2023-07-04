package co.kr.smhrd.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.kr.smhrd.dto.RegisterDTO;
import co.kr.smhrd.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	RegisterService service;

	@GetMapping("/registerForm")
	public String regForm() {
		return "register/registerForm";
	}

	@PostMapping("/registerOk")
	public ModelAndView registerOk(RegisterDTO dto) {
		int result = 0;
		try {
			dto.setUserAddress(dto.getZipcode(), dto.getStreetAdr(), dto.getDetailAdr());
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
		System.out.println(dto.toString());
		return mav;

	}

	@GetMapping("/login")
	public String login() {
		return "register/login";
	}

	@PostMapping("/loginOk")
	public ModelAndView loginOk(String userid, String userpwd, HttpSession session) {

		RegisterDTO dto = service.loginOk(userid, userpwd);
		System.out.println(dto.toString());
		ModelAndView mav = new ModelAndView();
		if (dto != null) { // �α��� ����
			session.setAttribute("logId", dto.getUserid());
			session.setAttribute("logName", dto.getUsername());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("redirect:login");
		}
		return mav;
	}@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
}