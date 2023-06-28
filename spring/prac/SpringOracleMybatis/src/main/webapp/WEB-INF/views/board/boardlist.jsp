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
	<div>
		<a href="/home/board/boardWrite">글쓰기</a>
	</div>
	<div>총 레코드 수 : 45개</div>
	<ul class="board_list">
		<li>&nbsp;</li>
		<li>no</li>
		<li>제목</li>
		<li>글쓴이</li>
		<li>등록일</li>
		<li>hit</li>
		
		<li><input type="checkbox" /></li>
		<li>100</li>
		<li><a href="">처음쓰는 글</a></li>
		<li>goguma</li>
		<li>06:28 14:36</li>
		<li>0</li>
		
		<li><input type="checkbox" /></li>
		<li>200</li>
		<li><a href="">처음쓰는 글</a></li>
		<li>goguma</li>
		<li>06:28 14:36</li>
		<li>0</li>
		
	</ul>
	<div class="page">
		<ul>
			<li><a href="#">prev</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">next</a></li>
		</ul>
	</div>
	<div class="search">
		<form action="#">
			<select name="searchKey">
				<option>제목</option>
				<option>글내용</option>
				<option>글쓴이</option>
			</select>
			<input type="text" name="searchWord" id="searchWord" />
			<input type="submit" value="Search" />
		</form>
	</div>
</main>