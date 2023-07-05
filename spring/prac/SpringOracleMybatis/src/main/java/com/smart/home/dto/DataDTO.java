package com.smart.home.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok��
// ������̼� @Data�� ǥ���ϸ� @setter, @getter, @toString, @equals��
// ������� �ʾƵ� �ȴ�.

@Data
@NoArgsConstructor  // �⺻ ������
@AllArgsConstructor  // ��� ���� ���� ������
public class DataDTO {
	private int no;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
	// ������ ���ϸ�
	private List<String> delFile;
	
		
}
