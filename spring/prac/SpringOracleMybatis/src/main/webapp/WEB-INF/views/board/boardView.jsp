<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function delChk() {
		
		// 확인(true), 취소(false)
		if(confirm("글을 삭제하시겠습니까?")) {
			location.href = "/home/board/boardDel?no=${dto.no}";
		}
	}
	$(function(){
		// 댓글 목록 가져오기
		function replyAllList() {
			$.ajax({
				url:'/home/reply/replyList',
				data:{
					no:${dto.no} // 원글 글번호
				},
				success:function(replyResult){
					$("#replyList").html(""); // 원래있는 목록 지우기
					console.log(replyResult);
					$(replyResult).each(function(i, coment){
						var tag = "<li><div>";
						tag += "<b>"+coment.userid+"("+coment.writedate + ")</b>";
						// 수정 삭제버튼
						// 로그인 한 사람이 쓴 댓글일 때
						if(coment.userid=='${logId}'){ // 'goguma' == goguma 비교할 값들의 형식이 다름
							tag += "<input type='button' value='Edit'/>";
							tag += "<input type='button' value='Del' title='"+coment.re_no+"'/>";
							tag += "<p>"+coment.coment+"</p></div>"; // 댓글 내용
							// 댓글 수정폼
							tag += "<div style='display:none;'>";
							tag += "<form>";
							tag += "<textarea style='width:400px;' name='coment'>";
							// 글내용수정, 댓글번호
							tag += coment.coment;
							tag += "</textarea>";	
							// 나중에 추가한 태그는 event 안먹음 선택자를 document로 해야한다.
							tag += "<input type='hidden' name='re_no' value='"+coment.re_no+"'/>";
							tag += "<input type='button' value='수정하기'/>";
							tag += "</form>";
							tag += "</div>";
						} else {
							tag += "<p>"+coment.coment+"</p></div>";	
						}
						tag += "</li>";
						$("#replyList").append(tag);
					});
				},
				error:function(e){
					console.log(e.responseText);
				}
			});
		}
		// 댓글쓰기
		$("#replyfrm").submit(function() {
			// form 태그의 기본 이동의 기능을 가진 action으로 이동하는 것을 해제
			// ajax로 이미 서버를 이용하기 때문에 action으로 이동할 필요 없음
			event.preventDefault();
			
			// 0. 댓글 입력 확인
			if($("#coment").val() == ""){
				alert("댓글을 입력하세요..");
				return false;
			}
			// 1. 데이터 준비 no=99&coment=댓글내용 -> 폼내의 값을 쿼리로 만들어주는 함수(serialize())
			var params = $("#replyfrm").serialize();
			console.log('params', params);
			
			// 2. 서버 호출
			$.ajax({
				url: '/home/reply/replyWrite',
				type: 'POST',
				data: params,
				success:function(result){
					console.log(result);
					// 이미 디비에 등록된 글 폼에서 지우기
					$("#coment").val("");
					// 댓글 리스트 재출력 (작성중에 다른이가 작성가능하기에, 수정 삭제에도 적용됨(재호출))
					replyAllList();
				},
				error:function(e){
					console.log(e.reponseText)
				}
			});
			
		});
		// 댓글 수정 폼
		// $("#replyList input[value==Edit]").click();
		// 			이벤트 종류 / 대상 / 함수
		$(document).on('click', '#replyList input[value=Edit]', function(){
			// 해당댓글은 숨기기
			$(this).parent().css('display', 'none');
			// 수정폼은 보여주고 
			$(this).parent().next().css('display', 'block');
			
		
		})
		// 댓글 수정(DB)
		$(document).on('click', '#replyList input[value=수정하기]', function(){
			let params = $(this).parent().serialize(); // re_no=??&coment=??
			
			$.ajax({
				url:'/home/reply/replyEditOk',
				data:params,
				type:'POST',
				success:function(result){
					if(result==0){
						alert("댓글 수정 실패");
					} else{
						alert("댓글 수정 완료");
						replyAllList();
					}
				},
				error:function(e){
					console.log(e.responseText)
				}
			})
		})
		// 댓글 삭제
		$(document).on('click', '#replyList input[value=Del]', function(){
			// 댓글번호    attr(), prop()
			// 			attr('title'), attr('title', '200')
			
			if (!confirm("댓글을 삭제하시겠습니까?")) {
				return false;
			} 
			let re_no = $(this).attr('title')
			$.ajax({
				url:"/home/reply/replyDel",
				data: {
					re_no:re_no
				},
				success:function(result){
					if(result=='0'){
						alert("댓글이 삭제되지 않았습니다");
					} else{
						alert("댓글 삭제 완료")
						replyAllList();
					}
				},
				error:function(e){
					console.log(e.responseText);
				}
			});

		});
		
		
		// 해당 글의 댓글 목록 
		replyAllList();
	});
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
	<style>
		#coment {
			width:500px;
			height:80px;
		}
		#replyList>li {
			border-bottom:1px solid #ddd;
			padding:5px 0px;
		}
	</style>
	<!-- 댓글달기 -->
	<div id="reply">
		<!-- 로그인시 댓글 폼 -->
		<c:if test="${logStatus=='Y'}">
		<!-- 자바스크립트실행 후 action이 실행되는데 미설정 시 현재 페이지로 이동 -->
			<form method="post" id="replyfrm">
				<input type="hidden" value="${dto.no }" name="no"><!-- 원글번호 -->
			<!-- comment 오라클에서의 예약어라 사용 x -->
				<textarea name="coment" id="coment"></textarea>
				<input type="submit" value="댓글등록하기" />
			</form>
		
		</c:if>
		<hr />
		<ul id="replyList">
			
		</ul>
	</div>
</main>