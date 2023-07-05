package com.smart.home.dao;

import java.util.List;

import com.smart.home.dto.DataDTO;
import com.smart.home.dto.DataFileDTO;

public interface DataDAO {
	// 자료실 목록
	public List<DataDTO> getDataList();
	// 글선택(1개)
	public DataDTO dataSelect(int no);
	// 글등록
	public int dataInsert(DataDTO dto);
	// 파일명 등록
	public int dataFileInsert(List<DataFileDTO> upFileList);
	// 글수정
	public int dataUpdate(DataDTO dto);
	// 글삭제
	public int dataDelete(int no, String userid);
	// 조회수증가
	public void hitCount(int no);
	// 첨부파일목록 삭제
	public int dataFileDelete(int no);
	// 첨부파일선택
	public List<DataFileDTO> dataFileSelect(int no);
}
