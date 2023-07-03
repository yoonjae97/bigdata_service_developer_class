<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function delChk() {
		
		// 확인(true), 취소(false)
		if(confirm("글을 삭제하시겠습니까?")) {
			location.href = "/home/board/boardDel?no=${dto.no}";
		}
	}
</script>
<main>
	<h1>글내용보기</h1>
	<div>
		<a href='/home/board/boardlist?nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>목록</a> 
		<!-- 목록 클릭시 검색어 저장해서 리스트로 이동 -->
	</div>
	<ul>
		<li>글번호 : ${dto.no}</li>
		<li>글쓴이 : ${dto.userid}</li>
		<li>조회수 : ${dto.hit}</li>
		<li>등록일 : ${dto.writedate}</li>
		<li>제목 : ${dto.subject}</li>
		<li>내용<br/>
			${dto.content}</li>
	</ul>
	<div>
	<!-- session의 로그인아이디(logId)와 현재 글의 글쓴이(userid)가 같으면 수정 삭제 표시 -->
		<c:if test="${logId == dto.userid}">
			<a href="/home/board/boardEdit?no=${dto.no}">수정</a>
			<a href="javascript:delChk()">삭제</a>
		</c:if>
	</div>
</main>