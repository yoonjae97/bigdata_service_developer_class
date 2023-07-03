package com.smart.home.service;

import java.util.List;

import com.smart.home.dto.DataDTO;

public interface DataService {
	// �ڷ�� ���
	public List<DataDTO> getDataList();
	// �ۼ���(1��)
	public DataDTO dataSelect(int no);
	// �۵��
	public int dataInsert(DataDTO dto);
	// �ۼ���
	public int dataUpdate(DataDTO dto);
	// �ۻ���
	public int dataDelete(int no, String userid);
	// ��ȸ������
	public void hitCount(int no);
}
	