package com.smart.home.service;

import java.util.List;
import com.smart.home.dto.BoardDTO;
import com.smart.home.dto.PagingDTO;

public interface BoardService {
	public int boardWriteOk(BoardDTO dto);
	public List<BoardDTO> boardList(PagingDTO pDTO);
	public int totalRecord();
}
