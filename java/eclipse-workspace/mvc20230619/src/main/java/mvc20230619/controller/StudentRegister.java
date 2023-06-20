package mvc20230619.controller;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc20230619.dao.DBConnection;
import mvc20230619.dao.StudentDao;
import mvc20230619.dao.StudentDaoImp;
import mvc20230619.dto.StudentDto;

@WebServlet("/student/register.do")
public class StudentRegister extends HttpServlet{
	// view만 연결하는 동적페이지
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/templates/student/insert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 학생 등록 양식 처리
		StudentDto student = new StudentDto();
		int register = 0;

		
		try {
			student.setNum(Integer.parseInt(req.getParameter("num")));
			student.setName(req.getParameter("name"));
			student.setPhone(req.getParameter("phone"));
			student.setAddress(req.getParameter("address"));
			student.setBirthday(req.getParameter("birthday"));
			
			Connection conn = DBConnection.getConn();
			StudentDao studentDao = new StudentDaoImp(conn);
			register = studentDao.insert(student);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (register > 0) {
			resp.sendRedirect("./list.do");
		} else {
			resp.sendRedirect("./register.do");
		}
	}
}
