package com.smart.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.home.dao.RegisterDAO;
import com.smart.home.dto.RegisterDTO;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterDAO dao;
	
	@Override
	public int registerInsert(RegisterDTO dto) {
		return dao.registerInsert(dto);
	}

	@Override
	public RegisterDTO loginOk(String MemberId, String MemberPwd) {
		return dao.loginOk(MemberId, MemberPwd);
	}
	
	@Override
	public String dupChk(String id) {
		return dao.dupChk(id);
	}



	@Override
	public String findId(String memberName, String memberEmail) {
		return dao.findPwd(memberName, memberEmail);
	}

	@Override
	public String findPwd(String memberId, String memberEmail) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


