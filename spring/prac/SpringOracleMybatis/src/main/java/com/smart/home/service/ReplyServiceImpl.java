package com.smart.home.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smart.home.dao.ReplyDAO;
import com.smart.home.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	ReplyDAO dao;

	@Override
	public int replyInsert(ReplyDTO dto) {
		return dao.replyInsert(dto);
	}

	@Override
	public List<ReplyDTO> replySelect(int no) {
		return dao.replySelect(no);
	}
}
