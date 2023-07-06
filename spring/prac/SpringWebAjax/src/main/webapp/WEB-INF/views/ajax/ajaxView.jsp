<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링에서 ajax 구현하기</title>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<style>
	#resultView {
	height:400px;
	background-color: blue;
	}
</style>


<script>
$(function(){
    // 서버에 비동기식으로 접속하여 문자열 받아오기
    //       0번째 버튼
    $("button").eq(0).click(function(){
        // 서버로 보낼 데이터 <a href="/a/?age=20">
        var params = "name=홍길동&age=25";
        $.ajax({
            data: params,
            type: 'GET',
            url: '/myapp/ajaxString',
            success: function(result){ // 서버에서 응답받은 정보가 result에 담긴다.
                // html(), text(), append(), appendTo(), insert(), prepend(),,,,
                $("#resultView").append("<li>" + result + "</li>");
            },
            error: function(error){
                console.log(error.responseText);
            }
        }); // ajax 호출 함수
    });
    $("button").eq(1).on('click', function(){
    	var params = "username=이순신&addr=서초구";
    	var url = "${pageContext.request.contextPath}/ajaxObject";
    	$.ajax({
    		type:"get",
    		data:params,
    		url:url,
    		success:function(result){
    			console.log(result);
    			var tag = "<li>";
    			tag += "번호:"+result.num;
    			tag += "이름:"+result.username;
    			tag += "전화번호:"+result.tel;
    			tag += "주소:"+result.addr;
    			tag += "</li>";
    			$("#resultView").append(tag);
    		},
    		error:function(e){
    			console.log(e.responseText);
    		}
    	});
    });
    
    $("button").eq(2).click(function(){
    	// json으로 전송
    	var data = {
    			productName : "컴퓨터",
    			price : 100    			
    	}
    	
    	$.ajax({
    		data:data,
    		dataType:'json',
    		url:'/myapp/ajaxList',
    		success:function(result){
    			console.log(result);
    			// 서버에서 List<DTO>리턴하면
    			// 배열에 DTO를 JSON으로 돌려준다.
    			let memberTag = "<li><ol>"
    			// 						index, dto
    			$(result).each(function(idx, data){
    				memberTag += "<li>num:"+data.num+", username:"+data.username;
    				memberTag += ", tel:"+data.tel+", addr:"+data.addr+"</li>";
    			});
    			memberTag += "</li></ol>";
    			$("#resultView").append(memberTag);
    		},
    		error:function(e){
    			console.log(e.responseText);
    		}
    	});
    });
    
    // 서버에서 dTO, VO를 받아오기
    $("button").eq(3).click(function(){
    	// 서버에서 Map데이터 가져오기
		$.ajax({
			url : "/myapp/ajaxMap",
			success:function(result){
				console.log(result);
				var tag = "<li>";
				
				tag += "<div>총레코드수:"+result.total+", 현재페이지:"+result.page+"</div>";
				tag += "<div>";
				$(result.list).each(function(i, d){
					tag += "(" + i + ")" + d.no + ". " + d.subject +"<br/>";
				});
				tag += "</div></li>";
				
				$("#resultView").append(tag);
			},
			error:function(e){
				console.log(e.responseText);
			}
		});
    });
    $("button").eq(4).click(function(){
    	
    	$.ajax({
    		url:'/myapp/ajaxJson',
    		success:function(result){
    			console.log(result);
    			// JSON.parse() : 문자열 -> json
    			// JSON.stringify() : json->문자열
    			var jsonData = JSON.parse(result);
    			console.log(jsonData);
    			var tag = "<li>";
    			tag += "이름->"+jsonData.name+"<br/>";
    			tag += "나이->"+jsonData.age+"<br/>";
    			tag += "이메일1->"+jsonData.email.first+"<br/>";
    			tag += "이메일2->"+jsonData.email.second+"<br/>";
    			tag += "</li>";
    			$("#resultView").append(tag);
    		},
    		error:function(e){
    			console.log(e.responseText);
    		}
    	});
    });
}); // jquery
</script>
</head>
<body>
<h1>비동기식 처리(ajax)</h1>
<button>ajaxString:문자열</button>
<button>ajaxObject:DTO, VO</button>
<button>ajaxList</button>
<button>ajaxMap</button>
<button>ajaxJson</button>
<hr />
<div id="resultView">
	<ul></ul>
</div>
</body>
</html>
