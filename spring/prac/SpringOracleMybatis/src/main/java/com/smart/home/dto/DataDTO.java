package com.smart.home.dto;

import lombok.Data;

// lombok은
// 어노테이션 @Data를 표기하면 @setter, @getter, @toString, @equals을
// 기술하지 않아도 된다.

@Data
public class DataDTO {
	private int no;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
		
}
