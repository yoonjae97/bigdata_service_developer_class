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

@WebServlet("/student/remove.do")
public class StudentRemove extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = 0;
		try {
			num = Integer.parseInt(req.getParameter("num"));
		} catch (NumberFormatException e) {
			resp.sendError(400, "num이 있어야 삭제합니다.");
			return;
		}
		
		int delete = 0;
		try {
			Connection conn = DBConnection.getConn();
			StudentDao studentDao = new StudentDaoImp(conn);
			delete = studentDao.delete(num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (delete > 0) {
			resp.sendRedirect("./list.do");
		} else {
			resp.sendRedirect("./modify.do?num="+num);
		}
	}
}
