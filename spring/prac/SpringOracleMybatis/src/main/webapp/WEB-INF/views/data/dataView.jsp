<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<h1>자료실 글내용보기</h1>
	<ul>
		<li>번호 : ${dto.no}</li>
		<li>글쓴이 : ${dto.userid}</li>
		<li>조회수 : ${dto.hit}</li>
		<li>등록일 : ${dto.writedate }</li>
		<li>제목 : ${dto.subject }</li>
		<li>글내용<br /> ${dto.content }
		</li>
		<li>첨부파일 : <c:forEach var="fDTO" items="${fileList}">
				<a href="<%=request.getContextPath()%>/upload/${fDTO.filename}"
					download>${fDTO.filename }</a>
			</c:forEach>

		</li>
	</ul>
	<hr />
	<!-- 본인이 쓴 글일때 -->
	<c:if test="${logId==dto.userid }">
		<div>
			<a href="/home/data/dataEdit?no=${dto.no}">수정</a> 삭제
		</div>
	</c:if>
</main>