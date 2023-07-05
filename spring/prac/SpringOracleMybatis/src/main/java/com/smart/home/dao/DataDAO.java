package com.smart.home.dao;

import java.util.List;

import com.smart.home.dto.DataDTO;
import com.smart.home.dto.DataFileDTO;

public interface DataDAO {
	// �ڷ�� ���
	public List<DataDTO> getDataList();
	// �ۼ���(1��)
	public DataDTO dataSelect(int no);
	// �۵��
	public int dataInsert(DataDTO dto);
	// ���ϸ� ���
	public int dataFileInsert(List<DataFileDTO> upFileList);
	// �ۼ���
	public int dataUpdate(DataDTO dto);
	// �ۻ���
	public int dataDelete(int no, String userid);
	// ��ȸ������
	public void hitCount(int no);
	// ÷�����ϸ�� ����
	public int dataFileDelete(int no);
	// ÷�����ϼ���
	public List<DataFileDTO> dataFileSelect(int no);
}
