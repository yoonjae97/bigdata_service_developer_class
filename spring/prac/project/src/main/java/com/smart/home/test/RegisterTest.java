package com.smart.home.test;

import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.smart.home.dao.RegisterDAO;
import com.smart.home.dto.RegisterDTO;

public class RegisterTest {
	
	@Mock
	private RegisterDAO dao;

	@Test
	@Ignore
	public void registerInsert() {
		MockitoAnnotations.openMocks(this);
		
		RegisterDTO dto = new RegisterDTO();
		dto.setMemberId("test");
		dto.setMemberPwd("1234");
		dto.setMemberAddr("¼­¿ï");
		dto.setMemberBirth("20010101");
		dto.setMemberEmail("test@gmail.com");
		dto.setMemberGender("F");
		dto.setMemberTel("01011112222");
		dto.setMemberName("test");
		
		dao.registerInsert(dto);
		
		verify(dao).registerInsert(dto);
	}
	
	@Test
	@Ignore
	public void loginOk() {
		MockitoAnnotations.openMocks(this);
		
		RegisterDTO dto = new RegisterDTO();
		
		dto.setMemberId("test");
		dto.setMemberPwd("1234");
		
		dao.loginOk("test", "1234");
		
		verify(dao).loginOk("test", "1234");
	}
	
	@Test
	@Ignore
	public void findId() {
		MockitoAnnotations.openMocks(this);
		
		dao.findId("test", "test@gmail.com");
		
		verify(dao).findId("test", "test@gmail.com");
	}
	
	@Test
	public void dupChk() {
		MockitoAnnotations.openMocks(this);
		
		dao.dupChk("test");
		
		verify(dao).dupChk("test");
	}
}
