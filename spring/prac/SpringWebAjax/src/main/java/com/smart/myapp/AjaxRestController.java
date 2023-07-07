package com.smart.myapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. View ������ ������ �ִ� ��� ModelAndview ��ü�� ����� �������ش�.
// 2. View �������� ������ ����Ʈ���� ���(html, css)�� ����ϱ� ���ؼ���
// 	  ResponseEntity ��ü�� ���� ����Ѵ�.
// 3. Ajaxó�� �䰡 �ʿ���� ��� @ResponseBody�� ������� �ʾƵ� �ȴ�.

@RestController
public class AjaxRestController {
	// DTO
	@GetMapping("/ajaxObjectRest")
	public BoardDTO ajaxObjectRest() {
		BoardDTO dto = new BoardDTO();
		dto.setNo(200);
		dto.setUserid("gildong");
		dto.setSubject("Rest��Ʈ�ѷ� �׽�Ʈ��..");
		
		return dto;
	}
	// List
	@GetMapping("/ajaxListRest")
	public List<TestDTO> ajaxListRest() {
		
		List<TestDTO> list = new ArrayList<TestDTO>();
		list.add(new TestDTO(100, "ȫ�浿1", "010-1234-5678", "���ʱ�"));
		list.add(new TestDTO(200, "ȫ�浿2", "010-1234-5678", "���ʱ�"));
		
		return list;
	}
}
