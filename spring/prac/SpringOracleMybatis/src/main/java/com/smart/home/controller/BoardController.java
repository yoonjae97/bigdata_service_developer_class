package com.smart.home.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.dto.BoardDTO;
import com.smart.home.dto.PagingDTO;
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
	public ModelAndView boardList(PagingDTO pDTO) {
		
		// 총 레코드 수 선택		
		pDTO.setTotalRecord(service.totalRecord());
		
		// 해당페이지 레코드 선택
		List<BoardDTO> list = service.boardList(pDTO);
		
		// ModelAndView
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list); // 아래 jsp파일에서 사용할 수 있는 변수가 됨
		mav.addObject("pDTO", pDTO);
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
	// 글쓰기 DB기록
	@PostMapping("/boardWriteOk")
	public ResponseEntity<String> boardWriteOk(BoardDTO dto, HttpServletRequest request) {
		// HttpServletRequest -> request, HttpSession
		// HttpSession -> Session만
		
		
		// no, hit, writedate -> 오라클에서 처리
		// userid -> 세션
		
		dto.setUserid((String)request.getSession().getAttribute("logId")); // object 객체로 반환되기 때문에 String으로 타입캐스팅
		// ip - request 객체에 있음
		dto.setIp(request.getRemoteAddr());
		
		int result = 0;
		try {
			result = service.boardWriteOk(dto);
		} catch(Exception e) {
			System.out.println("게시판 글등록 예외발생..."+ e.getMessage());
		}
		// 등록결과에 따른 스크립트 생성하기
		String tag = "<script>";
		if (result>0) { // 성공 -> 게시판목록
			tag += "location.href='/home/board/boardlist';";
		} else { // 실패 -> 글 등록 폼
			tag += "alert('글 등록이 실패하였습니다.');";
			tag += "history.back();";
		}
		tag += "</script>";
		
		// 프론트언어를 백엔드에서 구현
		//		ResponseEntity 객체는 프론트 페이지를 작성할 수 있다.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
		
		
	}
	
	
}
