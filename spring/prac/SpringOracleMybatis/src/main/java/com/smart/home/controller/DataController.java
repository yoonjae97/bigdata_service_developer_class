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
	
	// �۸��
	@GetMapping("/dataList")
	public String dataList(Model model) {
		// db���ڵ� �����Ͽ� model�� ����
		
		return "data/dataList";
	}
	
	// �۵����
	@GetMapping("/dataWrite")
	public String dataWrite() {
		return "data/dataWrite";
	}
	
	// �۵�� DB���
	@PostMapping("/dataWriteOk")
	public ModelAndView dataWriteOk(HttpServletRequest request, DataDTO dto) {
		// ������ ���ε��� ��� /upload �����ּ�
		String path = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("path->" + path);
		
		// dto -> ����, �� ����, session���� �۾��� ���ؼ� dto�� ����
		// no -> ������, hit, writedate -> default ��
		dto.setUserid((String)request.getSession().getAttribute("logId"));
		
		// ------- ���Ͼ��ε�(�߿�) -----------------------  
		// 1. ���Ͼ��ε带 ���ؼ� request��ü�� multipartRequest��ü�� ����ȯ�Ѵ�.
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		
		// 2. MultipartFile ��ü�� ������
		List<MultipartFile> fileList = mr.getFiles("filename");
		System.out.println("fileList.size()- >"+ fileList.size());
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:dataList");
		return mav;
	}
	// 
}
