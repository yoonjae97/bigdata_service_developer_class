package com.smart.home.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smart.home.dao.RegisterDAO;
import com.smart.home.dto.RegisterDTO;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Inject
	RegisterDAO dao; // mapper와 연결
	
	@Override
	public int registerInsert(RegisterDTO dto) {
		return dao.registerInsert(dto);
		
	}
//	controller -> service ->(상속)serviceimpl -> dao -> mapper(xml)
// -> dao -> serviceimpl -> service -> controller

	@Override
	public RegisterDTO loginOk(String userid, String userpwd) {
		return dao.loginOk(userid, userpwd);
	}

	@Override
	public RegisterDTO findId(String namefind, String emailfind) {
		return dao.findId(namefind, emailfind);
	}
}
