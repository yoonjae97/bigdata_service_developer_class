package com.smart.home.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smart.home.dao.DataDAO;
import com.smart.home.dto.DataDTO;
import com.smart.home.dto.DataFileDTO;

//controller���� service ȣ���ϴµ�
// @Service�� �־�� impl���� ã�ƿ´�?
@Service 
public class DataServiceImpl implements DataService{

	@Inject
	DataDAO dao;
	
	@Override
	public List<DataDTO> getDataList() {
		return dao.getDataList();
	}

	@Override
	public DataDTO dataSelect(int no) {
		return dao.dataSelect(no);
	}

	@Override
	public int dataInsert(DataDTO dto) {
		return dao.dataInsert(dto);
	}
	
	@Override
	public int dataFileInsert(List<DataFileDTO> upFileList) {
		return dao.dataFileInsert(upFileList);
	}

	@Override
	public int dataFileDelete(int no) {
		return dao.dataFileDelete(no);
	}
	
	@Override
	public int dataUpdate(DataDTO dto) {
		return dao.dataUpdate(dto);
	}

	@Override
	public int dataDelete(int no, String userid) {
		return dao.dataDelete(no, userid);
	}

	@Override
	public void hitCount(int no) {
		dao.hitCount(no);
		
	}

	@Override
	public List<DataFileDTO> dataFileSelect(int no) {
		return dao.dataFileSelect(no);
	}

}
