 package co.kr.smhrd.controller;

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
}