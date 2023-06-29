package com.smart.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.home.dao.BoardDAO;
import com.smart.home.dto.BoardDTO;
import com.smart.home.dto.PagingDTO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;

	@Override
	public int boardWriteOk(BoardDTO dto) {
		return dao.boardWriteOk(dto);
	}

	@Override
	public List<BoardDTO> boardList(PagingDTO pDTO) {
		return dao.boardList(pDTO);
	}

	@Override
	public int totalRecord() {
		return dao.totalRecord();
	}
}
