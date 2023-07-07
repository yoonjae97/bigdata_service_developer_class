package co.kr.smhrd.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import co.kr.smhrd.dao.RegisterDAO;
import co.kr.smhrd.dto.RegisterDTO;

@Service
public class RegisterServiceImpl implements RegisterService{
	@Inject
	RegisterDAO dao;
	
	@Override
	public int registerInsert(RegisterDTO dto) {
		return dao.registerInsert(dto);
	}

	@Override
	public RegisterDTO loginOk(String userid, String userpwd) {
		return dao.loginOk(userid, userpwd);
	}

	@Override
	public RegisterDTO findId(String username, String useremail) {
		return dao.findId(username, useremail);
	}

	@Override
	public int dupChk(String id) {
		return dao.dupChk(id);
	}
	
	
}
