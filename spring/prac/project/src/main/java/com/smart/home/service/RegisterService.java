package com.smart.home.service;

import com.smart.home.dto.RegisterDTO;

public interface RegisterService {
	public int registerInsert(RegisterDTO dto);
	public RegisterDTO loginOk(String MemberId, String MemberPwd);
	public String findId(String memberName, String memberEmail);
	public String dupChk(String id);
	public String findPwd(String memberId, String memberEmail);
}
