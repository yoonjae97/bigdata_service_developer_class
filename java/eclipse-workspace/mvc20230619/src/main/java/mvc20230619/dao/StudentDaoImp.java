package mvc20230619.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc20230619.dto.StudentDto;

public class StudentDaoImp implements StudentDao{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public StudentDaoImp(Connection conn) {
		this.conn = conn;
	}	
	
	@Override
	public List<StudentDto> list() throws Exception {
		List<StudentDto> list = null;
		String sql = "SELECT * FROM STUDENT";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		// 오류없이 실행되었다는 것은 db 문제가 없다는 뜻 (== new ArrayList<>())
		list = new ArrayList<>();
		while(rs.next()) {
			StudentDto dto = new StudentDto();
			dto.setNum(rs.getInt("num"));
			dto.setName(rs.getString("name"));
			dto.setPhone(rs.getString("phone"));
			dto.setAddress(rs.getString("address"));
			dto.setBirthday(rs.getString("birthday"));
			list.add(dto);
		}
		
		return list;
	}
	
	@Override
	public StudentDto detail(int num) throws Exception {
		StudentDto detail = null;
		String sql = "SELECT * FROM STUDENT WHERE num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			detail = new StudentDto();
			detail.setNum(rs.getInt("num"));
			detail.setName(rs.getString("name"));
			detail.setPhone(rs.getString("phone"));
			detail.setAddress(rs.getString("address"));
			detail.setBirthday(rs.getString("birthday"));
			
		}
	 return detail;	
}	
	@Override
	public int delete(int num) {
		int delete = 0;
		return delete;
	}
	
	@Override
	public int update(StudentDto student) throws Exception {
		int update = 0;
		return update;
	}
	
	@Override
	public int insert(StudentDto student) {
		int insert = 0;
		return insert;
	}

}