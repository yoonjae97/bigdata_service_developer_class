package com.smart.home.dto;

import lombok.Data;

// lombok��
// ������̼� @Data�� ǥ���ϸ� @setter, @getter, @toString, @equals��
// ������� �ʾƵ� �ȴ�.

@Data
public class DataDTO {
	private int no;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
		
}
