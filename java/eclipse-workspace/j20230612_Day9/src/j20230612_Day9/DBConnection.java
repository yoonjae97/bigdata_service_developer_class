package j20230612_Day9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String user = "user02";
    private String password = "1234";

    public Connection getConnection() {
        Connection conn = null;

        try {
            //driver 로딩
            Class.forName(driver);
            System.out.println("jdbc driver 로딩 성공");

            //DB와 연결
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("오라클 연결 성공");

        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver 로딩 실패");
        } catch (SQLException e) {
            System.out.println("오라클 연결 실패");
        }

        return conn;
    }
}