package co.kr.smhrd.service;

import co.kr.smhrd.dto.RegisterDTO;

public interface RegisterService {
	public int registerInsert(RegisterDTO dto);
	public RegisterDTO loginOk(String userid, String userpwd);
	public RegisterDTO findId(String username, String useremail);
}
