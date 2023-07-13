<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function setThumbnail(event) {
		var reader = new FileReader();

		reader.onload = function(event) {
			var img = document.createElement("img")
			img.setAttribute("src", event.target.result)
			document.querySelector("div#image_container").appendChild(img)
		}

		reader.readAsDataURL(event.target.files[0])
	}
	
	function upload() {
		const imageInput = $("#imageInput")[0]
		
		if(imageInput.files.length==0) {
			alert("파일을 선택해주세요")
			return;
		}
		
		const formData = new FormData();
		formData.append("image", imageInput.files[0])
		
		$.ajax({
			type:"POST",
			url:'${pageContext.request.contextPath}/certify/imgCertify',
			processData:false,
			contentType:false,
			data:formData,
			success:function(result) {
				console.log(result)
			},
			error:function(error) {
				console.log(e.reponseText)
			}
		})
	}
</script>
<form id="imgCertify">
	<input type="file" id="imageInput" value="파일첨부"
		onchange="setThumbnail(event)" />
	<div id="image_container"></div>
	<button onclick="">제출하기</button>
	<p>업로드결과:</p>  <p id="resultUploadPath"></p>
</form>
