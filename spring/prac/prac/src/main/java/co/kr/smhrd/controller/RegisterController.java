package co.kr.smhrd.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		System.out.println(dto);
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

		
		ModelAndView mav = new ModelAndView();
		try {
			RegisterDTO dto = service.loginOk(userid, userpwd);
			session.setAttribute("logId", dto.getUserid());
			session.setAttribute("logName", dto.getUsername());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		} catch (Exception e){
			e.printStackTrace();
			mav.addObject("msg", "fail");
			mav.setViewName("redirect:login");
		}

		return mav;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@GetMapping("/findIdForm")
	public String findIdForm() {
		return "register/findIdForm";
	}
	
	@PostMapping("/findId")
	public ModelAndView findId(String username, String useremail) {
		ModelAndView mav = new ModelAndView();
		try {
			RegisterDTO dto = service.findId(username, useremail);
			
			mav.addObject("userId", dto.getUserid());
			mav.setViewName("register/returnUserId");
			return mav;
		} catch (Exception e){
			mav.setViewName("register/returnUserId");
			return mav;
		}
	
	}
	
	@GetMapping("/dupChk")
	@ResponseBody
	public Integer dupChk(String id) {
		Integer result = 0;
		String dupId = null;
		try {
			dupId = service.dupChk(id);
			if (dupId == null) {
				result = 0;
			
			} else {
				result = 1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(dupId + "hi" +result);
		return result;
	}
	
	@Autowired
	private JavaMailSender mailSender;
	
	@PostMapping("/emailChk")
	@ResponseBody
	public String emailChk(String email) {
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		
		
		// �̸��� ������
		String setFrom = "yoonjae9432@gmail.com";
		String toMail = email;
		String title = "ȸ������ ���� �̸��� �Դϴ�.";
		String content =  "Ȩ�������� �湮���ּż� �����մϴ�." +
                "<br><br>" + 
                "���� ��ȣ�� " + checkNum + "�Դϴ�." + 
                "<br>" + 
                "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���.";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Integer.toString(checkNum);
	}

	
}
	