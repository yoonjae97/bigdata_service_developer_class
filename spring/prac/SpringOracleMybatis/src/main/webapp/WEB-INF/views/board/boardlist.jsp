<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.board_list, .page>ul{overflow:auto;}
	.board_list>li {
		float:left;
		height:40px;
		line_height:40px;
		border-bottom:1px solid #ddd;
		width:10%;
	}
	
	.board_list>li:nth-child(6n+3) {
		width:50%;
		/* 말줄임표시 */
		white-space:nowrap;/* 줄바꾸지 않기 */
		overflow:hidden;/* 넘친 값 숨기기 */
		text-overflow:ellipsis;/* ... 표시하기 */
	}	

	.page li {
		float:left;
		width:40px;
		height:40px;
		text-align:center;
	}
	.search{
		text-align:center;
	}
</style>
<main>
	<h1>게시판 목록</h1>
<%-- 	<c:if test="${logId!=null}">
		<div>
			<a href="/home/board/boardWrite">글쓰기</a>
		</div>
	</c:if> --%>
		<div>
			<a href="/home/board/boardWrite">글쓰기</a>
		</div>
	<div>총 레코드 수 : ${pDTO.totalRecord }개</div>
	<ul class="board_list">
		<li>&nbsp;</li>
		<li>no</li>
		<li>제목</li>
		<li>글쓴이</li>
		<li>등록일</li>
		<li>hit</li>
		<!--       변수       데이터(리스트)->modelandview에 설정한 변수 -->
		<c:forEach var="dto" items="${list }">
			<li><input type="checkbox" /></li>
			<li>${dto.no }</li>
			<li><a href='/home/board/boardView?no=${dto.no }&nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>${dto.subject}</a></li>
			<li>${dto.userid }</li>
			<li>${dto.writedate }</li>
			<li>${dto.hit }</li>
		</c:forEach>
	</ul>
	<div class="page">
		<ul>
			<!-- 이전페이지 -->
			
			<c:if test="${pDTO.nowPage==1 }">
				<li><a href='/home/board/boardlist?nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>prev</a></li>
			</c:if>
			<c:if test="${pDTO.nowPage>1 }">
				<li><a href='/home/board/boardlist?nowPage=${pDTO.nowPage-1}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>prev</a></li>
			</c:if>
			<!-- 페이지번호 변수     시작값                        끝값(end는 작거나 같다여서 -1                        증가값(default가 1이라 생략가능)-->
			<c:forEach var="p" begin="${pDTO.startPageNum }" end="${pDTO.startPageNum+pDTO.onePageNumCount -1 }" step="1">
				<c:if test="${p<=pDTO.totalPage}"><!-- totaPage보다 큰 페이지 번호는 출력하지 않음 -->
					<c:if test="${p==pDTO.nowPage }">	
						<li style="background:yellow"><a href='/home/board/boardlist?nowPage=${p}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>${p}</a></li>			
					</c:if>	
					<c:if test="${p!=pDTO.nowPage }">
						<li><a href='/home/board/boardlist?nowPage=${p}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>${p}</a></li>
					</c:if>
				</c:if>
			</c:forEach>
			
			<!-- 다음 페이지 -->
			<c:if test="${pDTO.nowPage>=pDTO.totalPage}">
				<li><a href='/home/board/boardlist?nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>next</a></li>
			</c:if>
			<c:if test="${pDTO.nowPage<pDTO.totalPage}">
				<li><a href='/home/board/boardlist?nowPage=${pDTO.nowPage+1}<c:if test="${pDTO.searchWord!=null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>next</a></li>
			</c:if>
		
		</ul>
	</div>
	<div class="search">
		<form action="/home/board/boardlist">
			<select name="searchKey">
				<option value="subject">제목</option>
				<option value="content">글내용</option>
				<option value="userid">글쓴이</option>
			</select>
			<input type="text" name="searchWord" id="searchWord" />
			<input type="submit" value="Search" />
		</form>
	</div>
</main>