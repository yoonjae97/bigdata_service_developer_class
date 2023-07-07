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
	// 댓글등록
	
	@PostMapping("/reply/replyWrite")
	public String replyWrite(ReplyDTO dto, HttpSession session) {
		// 세션 글쓴이 구하기
		dto.setUserid((String)session.getAttribute("logId"));
		int result = service.replyInsert(dto);
		return result+"";
		
		
	}
	
	// 댓글 목록
	@GetMapping("/reply/replyList")
	@ResponseBody
	public List<ReplyDTO> replyList(int no) { // 원글글번호
		return service.replySelect(no);

	}
}
