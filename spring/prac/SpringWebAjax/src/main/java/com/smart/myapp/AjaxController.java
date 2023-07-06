package com.smart.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	
	// @requestmapping / @getmapping / @postmapping
	@RequestMapping("/ajaxStart")
	public String ajaxStart() {
		return "ajax/ajaxView";
	}
	
	// ��ȯ�� String�� ��츸 ���ڵ��� ����Ͽ��� �Ѵ�
	@RequestMapping(value="/ajaxString", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
	@ResponseBody
	public String ajaxString(String name, int age) {
		System.out.println(name+""+age);
		return "ajax�� �������� ���� ���� ("+name+", "+(age+10)+")";
	}
	
	@GetMapping("/ajaxObject")
	@ResponseBody
	public TestDTO ajaxObject(TestDTO dto) {
		System.out.println(dto.toString());
		
		dto.setNum(1234);
		dto.setTel("010-5555-6666");
		
		return dto;
	}
	
	@GetMapping("/ajaxList")
	@ResponseBody
	public List<TestDTO> ajaxList(String productName, int price) {
		System.out.println(productName + " " + price);
		
		List<TestDTO> list = new ArrayList<TestDTO>();
		list.add(new TestDTO(100, "ȫ�浿1", "010-1234-5678", "���ʱ�"));
		list.add(new TestDTO(200, "ȫ�浿2", "010-1234-5678", "���ʱ�"));
		list.add(new TestDTO(300, "ȫ�浿3", "010-1234-5678", "���ʱ�"));
		list.add(new TestDTO(400, "ȫ�浿4", "010-1234-5678", "���ʱ�"));
		
		
		return list;
	}
	// �Խ��Ǹ��, �ѷ��ڵ��, ��������
	@GetMapping("/ajaxMap")
	@ResponseBody
	public HashMap<String, Object> ajaxMap() {
		// �Խ��Ǹ��
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		boardList.add(new  BoardDTO(95, "���۽� ������"));
		boardList.add(new  BoardDTO(93, "��ճ�"));
		boardList.add(new  BoardDTO(89, "�ƴѰ�"));
		boardList.add(new  BoardDTO(71, "�翬����"));
		// �ѷ��ڵ��
		int totalRecord = 150;
		// ����������
		int page = 6;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", boardList);
		map.put("total", totalRecord);
		map.put("page", page);
		
		return map;
	/*
	 *  javascript
	 *  var data = {
	 *  	name :'gildong',
	 *  	eamil: {
	   		first:'a.nate.com',
	   		second:'b.naver.com'
	   	}
	 *  }
	 *  
	 *  	java������ json �����Ͱ� ��ü ����
	 * 		String data = "{
	 *  				\"no\":\"1\",
	 *  				\"name\":\"hong\"
	 *  			}"
	 */
	}
	
	@GetMapping("/ajaxJson")
	@ResponseBody
	public String ajaxJson() {
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "gildong");
		jsonData.put("age", 25);
		
		JSONObject emailJson = new JSONObject();
		emailJson.put("first", "a.nate.com");
		emailJson.put("second", "b.naver.com");
		
		jsonData.put("email", emailJson);
		
		System.out.println(jsonData.toString());
		
		return jsonData.toString();
	}
}
