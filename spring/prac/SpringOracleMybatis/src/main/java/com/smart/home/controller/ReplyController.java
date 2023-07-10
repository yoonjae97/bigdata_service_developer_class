package com.smart.home.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smart.home.dto.ReplyDTO;
import com.smart.home.service.ReplyService;

@RestController
public class ReplyController {
	@Autowired
	ReplyService service;
	// ��۵��
	
	@PostMapping("/reply/replyWrite")
	public String replyWrite(ReplyDTO dto, HttpSession session) {
		// ���� �۾��� ���ϱ�
		dto.setUserid((String)session.getAttribute("logId"));
		int result = service.replyInsert(dto);
		return result+"";
		
		
	}
	
	// ��� ���
	@GetMapping("/reply/replyList")
	@ResponseBody
	public List<ReplyDTO> replyList(int no) { // ���۱۹�ȣ
		return service.replySelect(no);

	}
	
	// ��� ����(DB:update)
	@PostMapping("/reply/replyEditOk")
	@ResponseBody
	public int replyEditOk(ReplyDTO dto) {
		return service.replyUpdate(dto);
		
	}
	
	// ��ۻ���
	@GetMapping("/reply/replyDel")
	@ResponseBody
	public String replyDel(int re_no) {
		return String.valueOf(service.replyDel(re_no));
	}
}
