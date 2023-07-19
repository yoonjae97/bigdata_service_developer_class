package com.smart.home.dao;

import com.smart.home.dto.RegisterDTO;

public interface RegisterDAO {
	public int registerInsert(RegisterDTO dto);

	public RegisterDTO loginOk(String MemberId, String MemberPwd);

	public String findId(String memberName, String memberEmail);

	public String dupChk(String memberId);
	
	public String findPwd(String memberId, String memberEmail);
}
