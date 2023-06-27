package com.smart.home.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smart.home.dao.RegisterDAO;
import com.smart.home.dto.RegisterDTO;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Inject
	RegisterDAO dao; // mapper�� ����
	
	@Override
	public int registerInsert(RegisterDTO dto) {
		System.out.println(dto.toString());
		return dao.registerInsert(dto);
		
	}
//	controller -> service ->(���)serviceimpl -> dao -> mapper(xml)
// -> dao -> serviceimpl -> service -> controller
}
