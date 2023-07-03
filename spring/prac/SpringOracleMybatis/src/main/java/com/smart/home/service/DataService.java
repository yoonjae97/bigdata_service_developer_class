package com.smart.home.service;

import java.util.List;

import com.smart.home.dto.DataDTO;

public interface DataService {
	// 자료실 목록
	public List<DataDTO> getDataList();
	// 글선택(1개)
	public DataDTO dataSelect(int no);
	// 글등록
	public int dataInsert(DataDTO dto);
	// 글수정
	public int dataUpdate(DataDTO dto);
	// 글삭제
	public int dataDelete(int no, String userid);
	// 조회수증가
	public void hitCount(int no);
}
	