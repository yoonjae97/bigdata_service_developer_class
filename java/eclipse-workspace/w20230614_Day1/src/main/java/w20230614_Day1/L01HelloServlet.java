package w20230614_Day1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 패키지 : 
// 모듈 : 배포되거나 실행될 수 있는 가장 작은 단위
// 패키지 : 모듈들의 집합 (기능별), 클래스의 이름을 유일하게 만든다.

// java.lang.* : 자바 언어의 기본 문법에 필요한 것들 (랩퍼 클래스)
// java.util.* : 간혹 import해서 사용하는 것들

// 데이터
// 기본형 (원시형) : 수 (컴퓨터가 기본적(원시적)으로 연산 or 저장하는 대상 수 )byte, short ...
// 기본형은 디폴트 0, 자료형은 null
// 자료형 (class) : 여러개가 참조되는 자료들
//class System()


// HttpServlet : 동적 리소스, 서블릿
// 요청의 방식 : post(양식을 제출하는 용도), get(리소스를 찾거나 공유하는 용도)

@WebServlet("/hello.do") // do는 동적리소스라는 뜻 (동적리소스 생성시 무조건 톰캣을 재시작 해야한다.)
public class L01HelloServlet extends HttpServlet {
	int a = 0;
	String s = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpServletRequest req : 요청정보 (url, 클라이언트의 브라우저정보, os정보 등)
		// HttpServletResponse resp : 요청을 처리해서 응답할 내역 (문자열 -> html, json, css, js, text 등)
		
		
		// get 방식으로 오는 요청을 처리할 수 있다.
		// url 파라미터 ?a=10&b=20 : 동적 리소스가 어떻게 처리되었으면 하는지 알려주는 것 (요청)
		// url : 서버에 리소스를 요청하는 일
		// www.naver.com/ : 서버 ip (서버주소)
		// /book.do?id=124691 : 해당 서버에 있는 리소스의 주소 path (파라미터가 있는 것은 모두 동적 리소스)
		// ?id==124691&author=마크주크버그 : 쿼리스트링 queryString(동적리소스에 포함될 내용을 알려주는 것)
		
		String aStr = req.getParameter("a"); // 쿼리스트링에 포함된 a를 찾아서 반
		String bStr = req.getParameter("b");
		int a = (aStr!=null)?Integer.parseInt(aStr):0;
		int b = (bStr!=null)?Integer.parseInt(bStr):0;		
		
		resp.setContentType("text/html;charset=UTF-8");
		String htmlStr = "<h1>안녕 동적리소스 서블릿</h1>";
		htmlStr += "<h2>axb="+(a*b)+"</h2>"; 
		
		PrintWriter out = resp.getWriter();
		out.append(htmlStr);
		// resp.getWriter() 에 문자열을 담아놓으면 자동으로(함수 실행이 끝나면) 응답
		
		
		
		
	}
	
//	public static void main(String[] args) {
//		
//
//	}

}
