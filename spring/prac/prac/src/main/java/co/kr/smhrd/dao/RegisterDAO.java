package co.kr.smhrd.dao;

import co.kr.smhrd.dto.RegisterDTO;

public interface RegisterDAO {
	public int registerInsert(RegisterDTO dto);
	public RegisterDTO loginOk(String userid, String userpwd);
	public RegisterDTO findId(String username, String useremail);
	public String dupChk(String id);
}
