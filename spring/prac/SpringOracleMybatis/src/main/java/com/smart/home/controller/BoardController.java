package com.smart.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.service.BoardService;

// @Controller : �𵨺並 ����
//			: ModelAnvView / Model(��) String(view)

// @RestController : Model�� ���ϵȴ�. 
//				   : Model+viewPage => ModelAndView�� ����


@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService service;
	
	@GetMapping("/boardlist")
	public ModelAndView boardList() {
		// �ش������� ���ڵ� ����
		// ModelAndView
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardlist");
		return mav;
	}
	
	// �۾��� ������ �̵�
	@GetMapping("/boardWrite")
	public ModelAndView boardWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardWrite");
		return mav;
	}
}
