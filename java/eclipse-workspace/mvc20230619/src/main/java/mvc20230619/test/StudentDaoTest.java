package mvc20230619.test;

import java.sql.Connection;

import mvc20230619.dao.DBConnection;
import mvc20230619.dao.StudentDao;
import mvc20230619.dao.StudentDaoImp;
import mvc20230619.dto.StudentDto;

public class StudentDaoTest {
	public static void main(String[] args) {
		try {
			Connection conn = DBConnection.getConn();
			StudentDao dao = new StudentDaoImp(conn);
//			System.out.println(dao.list());
//			System.out.println(dao.detail(10000));
//			System.out.println(dao.delete(10000));
			StudentDto dto = new StudentDto();
			dto.setNum(10000);
			dto.setName("이나라");
			dto.setPhone("010-4231-8575");
			dto.setAddress("서울시");
			dto.setBirthday("20/01/01");
			System.out.println(dao.update(dto));
			System.out.println(dao.list());
			
			// Junit 단위테스트(기능별테스트)			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
