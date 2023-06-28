package com.smart.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.service.BoardService;

// @Controller : 모델뷰를 리턴
//			: ModelAnvView / Model(모델) String(view)

// @RestController : Model이 리턴된다. 
//				   : Model+viewPage => ModelAndView로 리턴


@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService service;
	
	@GetMapping("/boardlist")
	public ModelAndView boardList() {
		// 해당페이지 레코드 선택
		// ModelAndView
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardlist");
		return mav;
	}
	
	// 글쓰기 폼으로 이동
	@GetMapping("/boardWrite")
	public ModelAndView boardWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardWrite");
		return mav;
	}
}
