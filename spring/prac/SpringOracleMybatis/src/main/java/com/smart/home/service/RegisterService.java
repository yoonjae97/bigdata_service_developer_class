package com.smart.home.service;

import com.smart.home.dto.RegisterDTO;

public interface RegisterService {
	public int registerInsert(RegisterDTO dto);
	
	public RegisterDTO loginOk(String userid, String userpwd);

	public RegisterDTO findId(String namefind, String emailfind);

	public RegisterDTO idSearch(RegisterDTO dto);
}
