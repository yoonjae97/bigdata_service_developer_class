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
@RequestMapping("/register")
public class MemberController {

	@Autowired
	MemberService service;

	@Autowired
	private JavaMailSender mailSender;

	// ȸ������ ������ �̵� -> �Ϸ�
	@GetMapping("/registerJoin")
	public String MemberRegForm() {
		return "register/registerJoin";
	}

	// ȸ������ Ȯ�� -> �Ϸ�
	@PostMapping("registerJoinOk")
	@ResponseBody
	public String MemberRegOk(MemberDTO dto) {
		int result = 0;
		ModelAndView mav = new ModelAndView();
		dto.setMemberAddr(dto.getZipcode(), dto.getZipcodeSub(), dto.getStreetAdr(), dto.getDetailAdr());
		System.out.println(dto);
		if(dto.getMemberGender()==null) {
			dto.setMemberGender("");
		}
		if(dto.getMemberTel()==null) {
			dto.setMemberTel("");
		}
		if(dto.getMemberAddr()==null) {
			dto.setMemberAddr("", "", "", "");
		}
		if(dto.getMemberBirth()==null) {
			dto.setMemberBirth("");
		}
		
		try {
			result = service.MemberInsert(dto);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�����Խ���" + e.getMessage());		}	
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	// �α��� ȭ������ �̵� -> �Ϸ�
	@GetMapping("/login")
	public String login() {
		return "register/LoginPage";
	}

	// �α��� -> �Ϸ�
	@PostMapping("/loginOk")
	@ResponseBody
	public String loginOk(String memberId, String memberPwd, HttpSession session) {
		System.out.println(memberId + memberPwd + "hi");
	
		try {
			MemberDTO dto = service.loginOk(memberId, memberPwd);
			session.setAttribute("logId", dto.getMemberId());
			session.setAttribute("logName", dto.getMemberName());
			session.setAttribute("logStatus", "Y");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

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
		return "register/FindID";
	}

	// ���̵� ã�Ƽ� ���̵� ��ȯ -> �Ϸ�
	@PostMapping("/findId")
	public ModelAndView findId(String memberName, String memberEmail) {
		String memberId = null;
		ModelAndView mav = new ModelAndView();

		try {
			memberId = service.findId(memberName, memberEmail);
			System.out.println(memberId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (memberId == null | memberId == "") {
			mav.addObject("message", "�Է��Ͻ� �̸� Ȥ�� �̸����� ��ġ���� �ʽ��ϴ�.");
			mav.setViewName("register/FindID");
		} else {
			mav.addObject("MemberId", memberId);
			mav.setViewName("register/FindIdForm");
		}

		return mav;
	}

	// ���̵� �ߺ� üũ -> �Ϸ�
	@GetMapping("/doubleChk")
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
	@GetMapping("/pwSearch")
	public String findPwdForm() {
		return "register/pwSearch";
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


}
