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

import com.smart.home.dto.MemberDTO;
import com.smart.home.service.MemberService;

@Controller
@RequestMapping("/yj")
public class MemberController {

	@Autowired
	MemberService service;

	@Autowired
	private JavaMailSender mailSender;

	// ȸ������ ������ �̵� -> �Ϸ�
	@GetMapping("/MemberRegForm")
	public String MemberRegForm() {
		return "yj/MemberRegForm";
	}

	// ȸ������ Ȯ�� -> �Ϸ�
	@PostMapping("MemberRegOk")
	public ModelAndView MemberRegOk(MemberDTO dto) {
		int result = 0;
		System.out.println(dto);
		try {
			result = service.MemberInsert(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�����Խ���" + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) {
			mav.setViewName("home");
		} else {
			mav.setViewName("yj/MemberResult");
		}
		return mav;
	}

	// �α��� ȭ������ �̵� -> �Ϸ�
	@GetMapping("/login")
	public String login() {
		return "yj/login";
	}

	// �α��� -> �Ϸ�
	@PostMapping("/loginOk")
	public ModelAndView loginOk(String memberId, String memberPwd, HttpSession session) {
		System.out.println(memberId + memberPwd + "hi");
		ModelAndView mav = new ModelAndView();
		try {
			MemberDTO dto = service.loginOk(memberId, memberPwd);
			session.setAttribute("logId", dto.getMemberId());
			session.setAttribute("logName", dto.getMemberName());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("home");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("redirect:login");
		}
		return mav;

	}

	// �α׾ƿ� -> �Ϸ�
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}

	// ���̵� ã�� ȭ������ �̵� -> �Ϸ�
	@GetMapping("/findIdForm")
	public String findIdForm() {
		return "yj/findIdForm";
	}

	// ���̵� ã�Ƽ� ���̵� ��ȯ -> �Ϸ�
	@PostMapping("/idSearchOk")
	public ModelAndView findId(String memberName, String memberEmail) {
		String memberId = null;
		ModelAndView mav = new ModelAndView();

		try {
			memberId = service.findId(memberName, memberEmail);
			System.out.println(memberId);
			mav.addObject("MemberId", memberId);
			mav.setViewName("yj/returnMemberId");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("yj/returnMemberId");
			return mav;
		}
	}

	// ���̵� �ߺ� üũ -> �Ϸ�
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ��й�ȣ ã�� ������ �̵� -> �Ϸ�
	@GetMapping("/findPwdForm")
	public String findPwdForm() {
		return "yj/findPwd";
	}

	// ��й�ȣ ã�� -> �Ϸ�
	@PostMapping("/findPwd")
	@ResponseBody
	public int findPwd(String memberId, String memberEmail) {
		String pwd = null;
		int result = 0;
		try {
			pwd = service.findPwd(memberId, memberEmail);
			if (pwd == null) {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String setFrom = "winterer601@naver.com";
		String toMail = memberEmail;
		String title = "��й�ȣ ã�� �̸��� �Դϴ�.";
		String content = "Ȩ�������� �湮���ּż� �����մϴ�." + "<br><br>" + "ȸ������ ��й�ȣ�� " + pwd + "�Դϴ�.";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
			result = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ȸ������ ������ �̵�
	@PostMapping("/memberUpdateForm")
	public ModelAndView memeberUpdateForm(String logId) {
		ModelAndView mav = new ModelAndView();
		MemberDTO dto = null;
		try {
			dto = service.getMember(logId);
			mav.addObject("dto", dto);
			mav.setViewName("yj/memberUpdateForm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dto.toString());
		return mav;
	}

	// ȸ������ Ȯ�� -> �Ϸ�
	@PostMapping("MemberUpdateOk")
	public ModelAndView MemberUpdateOk(MemberDTO dto) {
		int result = 0;
		System.out.println(dto);
		try {
			result = service.memberUpdate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������" + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) {
			mav.setViewName("home");
		} else {
			mav.setViewName("yj/MemberResult");
		}
		return mav;
	}

}
