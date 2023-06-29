package com.smart.home.dao;

import java.util.List;

import com.smart.home.dto.BoardDTO;
import com.smart.home.dto.PagingDTO;

public interface BoardDAO {
	// �۵�� 
	public int boardWriteOk(BoardDTO dto);
	// �۸�ϼ��� (paging, search)
	public List<BoardDTO> boardList(PagingDTO pDTO);
	// �� ���ڵ� ��
	public int totalRecord();
}
