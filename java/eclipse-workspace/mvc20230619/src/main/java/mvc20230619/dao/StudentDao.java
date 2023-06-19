package mvc20230619.dao;

import java.util.List;

import mvc20230619.dto.StudentDto;

public interface StudentDao {
	// db 제공하는 서비스는 거기서 거기더라 추상화 할 수 있다.
	// DQL List detail
	// DML, update, delete, insert
	//** db 접속 오류를 동반
	List<StudentDto> list() throws Exception;
	StudentDto detail(int num) throws Exception;
	int delete(int num) throws Exception;
	int update(StudentDto student) throws Exception;
	int insert(StudentDto student) throws Exception;
}
