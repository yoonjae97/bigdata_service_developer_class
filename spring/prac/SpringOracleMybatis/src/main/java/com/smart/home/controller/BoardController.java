package com.smart.home.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public ModelAndView boardList(PagingDTO pDTO) {
		
		// �� ���ڵ� �� ����		
		pDTO.setTotalRecord(service.totalRecord(pDTO));
		
		// �ش������� ���ڵ� ����
		List<BoardDTO> list = service.boardList(pDTO);
		
		// ModelAndView
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list); // �Ʒ� jsp���Ͽ��� ����� �� �ִ� ������ ��
		mav.addObject("pDTO", pDTO); // ����������, �˻���, �˻�Ű
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
	// �۾��� DB���
	@PostMapping("/boardWriteOk")
	public ResponseEntity<String> boardWriteOk(BoardDTO dto, HttpServletRequest request) {
		// HttpServletRequest -> request, HttpSession
		// HttpSession -> Session��
		
		
		// no, hit, writedate -> ����Ŭ���� ó��
		// userid -> ����
		
		dto.setUserid((String)request.getSession().getAttribute("logId")); // object ��ü�� ��ȯ�Ǳ� ������ String���� Ÿ��ĳ����
		// ip - request ��ü�� ����
		dto.setIp(request.getRemoteAddr());
		
		int result = 0;
		try {
			result = service.boardWriteOk(dto);
		} catch(Exception e) {
			System.out.println("�Խ��� �۵�� ���ܹ߻�..."+ e.getMessage());
		}
		// ��ϰ���� ���� ��ũ��Ʈ �����ϱ�
		String tag = "<script>";
		if (result>0) { // ���� -> �Խ��Ǹ��
			tag += "location.href='/home/board/boardlist';";
		} else { // ���� -> �� ��� ��
			tag += "alert('�� ����� �����Ͽ����ϴ�.');";
			tag += "history.back();";
		}
		tag += "</script>";
		
		// ����Ʈ�� �鿣�忡�� ����
		//		ResponseEntity ��ü�� ����Ʈ �������� �ۼ��� �� �ִ�.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
	}
	
	// �۳��� ����
	@GetMapping("/boardView")
	public ModelAndView boardView(int no, PagingDTO pDTO) { // pDTP�� nowpage�� ����ϱ� �����ε� �� ���캼��
		
		// ��ȸ�� ����
		service.hitCount(no);
		// ���ڵ� ����
		BoardDTO dto = service.getBoard(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("pDTO", pDTO); // �̹� �˻�Ű���� ���� ����?
		mav.setViewName("board/boardView");
		
		return mav;
	}
	// �� �����ϱ�
	@GetMapping("/boardEdit")
	public ModelAndView boardEdit(int no) { 
		// �������������� �������� �ʰ� ���� �������� ���ư���
		// �������������� ��ϵ��ư��� �������� �˻��� ���� ��� �õ�
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getBoard(no));
		mav.setViewName("board/boardEdit");
		return mav;
		
	}
	
	@PostMapping("/boardEditOk") // no, subject, content
	public ModelAndView boardEditOk(BoardDTO dto, HttpSession session) {
		dto.setUserid((String)session.getAttribute("logId"));
		
		int result = service.boardEdit(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getNo());
		if (result>0) { // �ۼ��� ���� -> �۳��뺸��
			mav.setViewName("redirect:boardView");
		} else {// �ۼ��� ���� -> ����������
			mav.setViewName("redirect:boardEdit");
		}
		return mav;
	}
	// �ۻ���
	@GetMapping("/boardDel")
	public ModelAndView boardDel(int no, HttpSession session) {
		int result = service.boardDel(no, (String)session.getAttribute("logId"));
		
		ModelAndView mav = new ModelAndView();
		if(result>0) { // ���� ���� -> �۸��
			mav.setViewName("redirect:boardlist");
		} else { // ���� ���� -> �۳���
			mav.addObject("no", no);
			mav.setViewName("redirect:boardView");
		}
		return mav;
		
	}
}
