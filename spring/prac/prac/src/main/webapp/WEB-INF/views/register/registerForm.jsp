<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	let emailChkNum = "";

	function regFormCheck() {
		let userId = document.getElementById("userid");
		let userPwd = document.getElementById("userpwd");
		let userPwd2 = document.getElementById("userpwd2");
		let userName = document.getElementById("username");
		let birth = document.getElementById("userbirth");
		let female = document.getElementById("female");
		let male = document.getElementById("male");
		let tel = document.getElementById("usertel");
		let email = document.getElementById("useremail");

		// 아이디 정규식

		if (userId.value == "") {
			alert("아이디를 입력하세요.");
			userId.focus();
			return false;
		}
		;

		if (userPwd.value == "") {
			alert("비밀번호를 입력하세요.");
			userPwd.focus();
			return false;
		}
		;

		if (userPwd.value !== userPwd2.value) {
			alert("비밀번호가 일치하지 않습니다..");
			userPwd.focus();
			return false;
		}
		;

		if (!female.checked && !male.checked) { //둘다 미체크시
			alert("성별을 선택해 주세요.");
			female.focus();
			return false;
		}
		;

		if (birth.value == "") {
			alert("생년월일를 입력하세요.");
			birth.focus();
			return false;
		}
		;

		if (tel.value == "") {
			alert("전화번호를 입력하세요.");
			tel.focus();
			return false;
		}
		;

		if ($("#dupChk").val() == "N") {
			alert("아이디 중복검사를 해주세요.")
			return false;
		}

		if ($("#emailChk").val() == "N") {
			alert("이메일 인증을 해주세요.")
			return false;
		}

		if ($("#useremailChk").val() != emailChkNum) {
			alert("이메일 인증번호가 올바르지 않습니다.")
			return false;
		}
		document.regForm.submit();

	}
	document.addEventListener('DOMContentLoaded', function() {
		let userpwdInput = document.getElementById('userpwd');
		let userpwd2Input = document.getElementById('userpwd2');
		let chkNotice = document.getElementById('chkNotice');

		userpwdInput.addEventListener('keyup', function() {
			chkNotice.innerHTML = '';
		});

		userpwd2Input.addEventListener('keyup', function() {
			if (userpwdInput.value !== userpwd2Input.value) {
				chkNotice.innerHTML = '비밀번호 일치하지 않음<br><br>';
				chkNotice.style.color = '#f82a2aa3';
			} else {
				chkNotice.innerHTML = '비밀번호 일치함<br><br>';
				chkNotice.style.color = '#199894b3';
			}
		});
	});

	function findAddr() {
		new daum.Postcode({
			oncomplete : function(data) {

				let addr = ''; // 주소 변수
				let extraAddr = ''; // 참고항목 변수

				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				if (data.userSelectedType === 'R') {
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					document.getElementById("detailAdr").value = extraAddr;
				} else {
					document.getElementById("detailAdr").value = '';
				}
				document.getElementById('zipcode').value = data.zonecode;
				document.getElementById("streetAdr").value = addr;
				document.getElementById("detailAdr").focus();
			}
		}).open();
	}

	$(document).ready(function() {

		let idJ = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
		let pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
		let nameCheck = /^[가-힣a-zA-Z]+$/;
		let phone = /^[0-9]{11}$/;
		let birthChk = /^[0-9]{8}$/;

		$("#userid").keyup(function() {
			if (idJ.test($("#userid").val())) {
				$("#idChk").text("");
			} else {
				$("#idChk").text("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
				$('#idChk').css('color', 'red');
				$('#idChk').css('font-weight', 'bold');
			}
		})

		$("#userpwd").keyup(function() {
			if (pwdCheck.test($("#userpwd").val())) {
				$("#pwdChk").text("");
			} else {
				$("#pwdChk").text("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
				$('#pwdChk').css('color', 'red');
				$('#pwdChk').css('font-weight', 'bold');
				return false;
			}
		})

		$("#username").keyup(function() {
			if (nameCheck.test($("#username").val())) {
				$("#nameChk").text("");
			} else {
				$("#nameChk").text("이름에는 한글 및 영어만 사용가능합니다.");
				$('#nameChk').css('color', 'red');
				$('#nameChk').css('font-weight', 'bold');
				return false;
			}
		})

		$("#userbirth").keyup(function() {
			if (birthChk.test($("#userbirth").val())) {
				$("#birthChk").text("");
			} else {
				$("#birthChk").text("생년월일은 숫자8자리를 입력해야 합니다.");
				$('#birthChk').css('color', 'red');
				$('#birthChk').css('font-weight', 'bold');
				return false;
			}
		})

		$("#usertel").keyup(function() {
			if (phone.test($("#usertel").val())) {
				$("#telChk").text("");
			} else {
				$("#telChk").text("연락처는 숫자11자리를 입력해야 합니다.");
				$('#telChk').css('color', 'red');
				$('#telChk').css('font-weight', 'bold');
				return false;
			}
		})

		$("#useremailChk").keyup(function() {
			if ($("#useremailChk").val() != emailChkNum) {
				$("#useremailChkTxt").text("이메일 인증번호가 올바르지 않습니다");
				$('#useremailChkTxt').css('color', 'red');
				$('#useremailChkTxt').css('font-weight', 'bold');

			} else {
				$("#useremailChkTxt").text("이메일 인증번호가 확인되었습니다.")
				$('#useremailChkTxt').css('color', 'blue');
				$('#useremailChkTxt').css('font-weight', 'bold');
				$("#useremailChkTxt").attr("value", "Y");
			}
		})

		$("#emailChk").click(function() {
			var email = document.getElementById("useremail").value;
			if (email == "" || email == null) {
				alert("이메일을 입력해주세요")
				return false;
			}
			$.ajax({
				data : {
					"email" : email
				},
				url : "${pageContext.request.contextPath}/register/emailChk",
				type : "POST",
				success : function(result) {
					alert("이메일로 인증번호가 전송되었습니다.");
					$("#useremailChk").prop("type", "text");
					emailChkNum = result;
					$("#emailChk").attr("value", "Y");

				},
				error : function(e) {
					console.log(e.responseText);
				}
			})

		})
		$("#dupChk").on('click', function() {
			var id = document.getElementById("userid").value;
			console.log(id);
			$.ajax({
				data : {
					'id' : id
				},
				url : '${pageContext.request.contextPath}/register/dupChk',
				success : function(result) {
					console.log(result)
					if (result == 1) {
						alert("이미 사용중인 아이디입니다.");
						$("#dupChk").css('color', 'red');
						$("#dupChk").css('font-weight', 'bold');

					} else {
						alert("사용가능한 아이디입니다.");
						$("#dupChk").css('color', 'blue');
						$("#dupChk").css('font-weight', 'bold');
						$("#dupChk").attr("value", "Y");

					}
				},
				error : function(e) {
					console.log(e.responseText);
				}
			})
		})

	});
</script>
<div class="regFormdiv">
	<form action="<%=request.getContextPath()%>/register/registerOk"
		method="post" class="regForm" name="regForm">
		<fieldset>
			<legend>회원가입</legend>
			<ul>
				<li><label for="userid">아이디</label> <input type="text"
					name="userid" id="userid" style="width: 100%;" placeholder="아이디"
					required /> <span id="idChk"></span></li>

				<li><label for="userpwd">비밀번호</label> <input type="password"
					name="userpwd" id="userpwd" style="width: 100%;" placeholder="비밀번호"
					required /> <span id="pwdChk"></span></li>

				<li><label for="userpwd2">비밀번호 확인</label> <input
					type="password" name="userpwd2" id="userpwd2" style="width: 100%;"
					placeholder="비밀번호 확인" required /> <font id="chkNotice" size="2"></font>
				</li>

				<li><label for="username">이름</label> <input type="text"
					name="username" id="username" style="width: 100%;" placeholder="이름"
					required /><span id="nameChk"></span></li>

				<li><label for="userbirth">생년월일</label> <input type="text"
					name="userbirth" id="userbirth" style="width: 100%;"
					placeholder="생년월일(8자리)" required /><span id="birthChk"></li>

				<li><label for="usergender">성별</label> 남<input type="radio"
					value="남" name="usergender" id="male"> 여<input type="radio"
					value="여" name="usergender" id="female"></li>

				<li class="form-group"><label for="zipcode">우편번호</label> <input
					type="text" id="zipcode" class="form-control" name="zipcode"
					placeholder="클릭하여 우편번호 입력" readonly onclick="findAddr()"
					style="width: 100%;"></li>

				<li class="form-group"><label for="streetAdr">도로명 주소</label> <input
					type="text" id="streetAdr" name="streetAdr" class="form-control"
					placeholder="도로명 주소" readonly style="width: 100%;"></li>

				<li class="form-group"><label for="detailAdr">상세주소</label> <input
					type="text" id="detailAdr" name="detailAdr" class="form-control"
					placeholder="상세주소" style="width: 100%;"></li>
				<li><label for="usertel">연락처</label> <input type="text"
					name="usertel" id="usertel" style="width: 100%;"
					placeholder="전화번호(숫자만)"><span id="telChk"></span></li>

				<li><label for="useremail">이메일</label> <input type="text"
					name="useremail" id="useremail" style="width: 100%;"
					placeholder="이메일(선택)"></li>
				<li><input type="hidden" name="useremailChk" id="useremailChk"
					style="width: 100%;" placeholder="이메일 인증번호"><span
					id="useremailChkTxt"></span></li>
				<li>
					<button type="button" onclick="history.back();">이전페이지로</button>
					<button type="button" onclick="regFormCheck();">회원가입</button>
					<button type="button" id="dupChk" value="N">아이디 중복체크</button>
					<button type="button" id="emailChk" value="N">인증번호 받기</button>
				</li>
			</ul>
		</fieldset>
	</form>
</div>

