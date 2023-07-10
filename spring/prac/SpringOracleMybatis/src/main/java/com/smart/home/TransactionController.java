package com.smart.home;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.dto.BoardDTO;
import com.smart.home.service.TransactionService;

@Controller
public class TransactionController {
	 
	@Autowired
	TransactionService service;
	
	@GetMapping("/transaction")
	@Transactional(rollbackFor= {RuntimeException.class, SQLException.class})
	public ModelAndView transactionTest(HttpSession session) {
		String userid = (String)session.getAttribute("logId");
		
		BoardDTO dto = new BoardDTO();
		dto.setSubject("Ʈ����� �׽�Ʈ1");
		dto.setContent("<p>Ʈ����� �׽�Ʈ ��...1</p>");
		dto.setUserid(userid);
		dto.setIp("123.123.123.123");
		
		BoardDTO dto2 = new BoardDTO();
		dto2.setSubject("Ʈ����� �׽�Ʈ2");
		dto2.setContent("<p>Ʈ����� �׽�Ʈ ��...2</p>");
		dto2.setUserid(userid);
		dto2.setIp("123.123.123.124");
		//dto2.setIp("123.123.123.124.123.123.123.124.123.123.123.124");
		
		try {
			int a = service.transactionInsert(dto);
			int b = service.transactionInsert(dto2);
		} catch(Exception e) {
			e.printStackTrace();
			// ���ܰ� �߻��ϸ� insert, update, delete���� ����� ��� rollback ó���ϱ�
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/boardlist");
		return mav;
	}
}
