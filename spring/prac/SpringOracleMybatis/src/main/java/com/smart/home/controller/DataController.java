package com.smart.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.dto.DataDTO;
import com.smart.home.service.DataService;

@Controller
@RequestMapping("/data")
public class DataController {
	@Autowired
	DataService service;
	
	// 글목록
	@GetMapping("/dataList")
	public String dataList(Model model) {
		// db레코드 선택하여 model에 세팅
		
		return "data/dataList";
	}
	
	// 글등록폼
	@GetMapping("/dataWrite")
	public String dataWrite() {
		return "data/dataWrite";
	}
	
	// 글등록 DB기록
	@PostMapping("/dataWriteOk")
	public ModelAndView dataWriteOk(HttpServletRequest request, DataDTO dto) {
		// 파일을 업로드할 경로 /upload 실제주소
		String path = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("path->" + path);
		
		// dto -> 제목, 글 내용, session에서 글쓴이 구해서 dto에 설정
		// no -> 시퀀스, hit, writedate -> default 값
		dto.setUserid((String)request.getSession().getAttribute("logId"));
		
		// ------- 파일업로드(중요) -----------------------  
		// 1. 파일업로드를 위해서 request객체를 multipartRequest객체로 형변환한다.
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		
		// 2. MultipartFile 객체를 얻어오기
		List<MultipartFile> fileList = mr.getFiles("filename");
		System.out.println("fileList.size()- >"+ fileList.size());
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:dataList");
		return mav;
	}
	// 
}
