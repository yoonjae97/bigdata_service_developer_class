package com.smart.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.home.dto.DataDTO;

//controller���� service ȣ���ϴµ�
// @Service�� �־�� impl���� ã�ƿ´�?
@Service 
public class DataServiceImpl implements DataService{

	@Override
	public List<DataDTO> getDataList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataDTO dataSelect(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int dataInsert(DataDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dataUpdate(DataDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dataDelete(int no, String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void hitCount(int no) {
		// TODO Auto-generated method stub
		
	}

}
