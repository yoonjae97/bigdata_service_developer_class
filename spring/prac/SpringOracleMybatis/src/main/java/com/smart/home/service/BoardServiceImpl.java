package com.smart.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.home.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;
}
