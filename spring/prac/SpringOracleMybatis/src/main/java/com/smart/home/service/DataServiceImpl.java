package com.smart.home.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smart.home.dao.DataDAO;
import com.smart.home.dto.DataDTO;
import com.smart.home.dto.DataFileDTO;

//controller에서 service 호출하는데
// @Service가 있어야 impl까지 찾아온다?
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dataDelete(int no, String userid) {
		return dao.dataDelete(no, userid);
	}

	@Override
	public void hitCount(int no) {
		// TODO Auto-generated method stub
		
	}

}
