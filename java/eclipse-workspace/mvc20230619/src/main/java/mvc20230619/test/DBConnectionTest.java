package mvc20230619.test;

import mvc20230619.dao.DBConnection;

public class DBConnectionTest {
	// 항상 모듈을 구현하면 동작하는지 테스트를 진행하고 다음 모듈을 만든다. TDD Test Driven Development
	public static void main(String[] args) throws Exception {
		System.out.println(DBConnection.getConn());
	}

}
