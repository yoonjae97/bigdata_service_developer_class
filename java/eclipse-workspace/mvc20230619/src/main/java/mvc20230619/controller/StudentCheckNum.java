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

@WebServlet("/student/checkNum.do") // num이 중복되었는지 확인(비동기식 html, json(o))
// ("checkNum" : true) : num이 사용중, false : num을 사용하지 않음
public class StudentCheckNum extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// --------------------------- 요청시작
		String numStr = req.getParameter("num");
		int num = 0;
		try {
			num = Integer.parseInt(numStr);
		} catch (NumberFormatException e) {
			resp.sendError(400);
			return;
		}
		// --------------------------- 요청처리
		StudentDto student = null;
		try {
			Connection conn = DBConnection.getConn();
			StudentDao dao = new StudentDaoImp(conn);
			student = dao.detail(num);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);
			return;
		}
		// --------------------------- 응답처리 시작
		// view forward
		// setAttribute("키값", 객체);
//		resp.setContentType("text/html;charset=utf-8"); 
		resp.setContentType("application/json;charset=utf-8");
		if (student != null) {
			resp.getWriter().print("{\"checkNum\":true}"); // json {} : obejct
			} else {
				resp.getWriter().print("{\"checkNum\":false}"); // json {} : obejct
		}

	}
}
