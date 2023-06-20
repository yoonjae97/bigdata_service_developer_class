package mvc20230619.test;

import java.sql.Connection;

import mvc20230619.dao.DBConnection;
import mvc20230619.dao.StudentDao;
import mvc20230619.dao.StudentDaoImp;

public class StudentDaoTest {
	public static void main(String[] args) {
		try {
			Connection conn = DBConnection.getConn();
			StudentDao dao = new StudentDaoImp(conn);
			System.out.println(dao.list());
			System.out.println(dao.detail(10000));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
