package com.smart.home.dao;

import java.util.List;

import com.smart.home.dto.BoardDTO;
import com.smart.home.dto.PagingDTO;

public interface BoardDAO {
	// 글등록 
	public int boardWriteOk(BoardDTO dto);
	// 글목록선택 (paging, search)
	public List<BoardDTO> boardList(PagingDTO pDTO);
	// 총 레코드 수
	public int totalRecord();
}
