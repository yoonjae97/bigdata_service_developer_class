<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="regFormdiv">
	<form action="<%=request.getContextPath()%>/register/registerOk"
		method="post" class="regForm" name="regForm">
		<fieldset>
			<legend>회원가입</legend>
			<ul>
				<li><label for="userid">아이디</label> <input type="text"
					name="userid" id="userid" style="width: 100%;" placeholder="아이디"
					required /></li>
				<li><label for="userpwd">비밀번호</label> <input type="password"
					name="userpwd" id="userpwd" style="width: 100%;" placeholder="비밀번호"
					required /></li>
				<li><label for="userpwd2">비밀번호 확인</label> <input
					type="password" name="userpwd2" id="userpwd2" style="width: 100%;"
					placeholder="비밀번호 확인" required /> <font id="chkNotice" size="2"></font>
				</li>
				<li><label for="username">이름</label> <input type="text"
					name="username" id="username" style="width: 100%;" placeholder="이름"
					required /></li>
				<li><label for="userbirth">생년월일</label> <input type="text"
					name="userbirth" id="userbirth" style="width: 100%;"
					placeholder="생년월일(8자리)" required /></li>
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
					name="usertel" id="usertel" style="width: 100%;" placeholder="전화번호(숫자만)">
				</li>
				<li><label for="useremail">이메일</label> <input type="text"
					name="useremail" id="useremail" style="width: 100%;" placeholder="이메일(선택)">
				</li>
				<li>
					<button type="button" onclick="history.back();">이전페이지로</button>
					<button type="button" onclick="regFormCheck();">회원가입</button>
				</li>
			</ul>
		</fieldset>
	</form>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function regFormCheck() {
		var userId = document.getElementById("userid");
		var userPwd = document.getElementById("userpwd");
		var userPwd2 = document.getElementById("userpwd2");
		var userName = document.getElementById("username");
		var birth = document.getElementById("userbirth");
		var female = document.getElementById("female");
		var male = document.getElementById("male");
		var tel = document.getElementById("usertel");
		var email = document.getElementById("useremail");

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

		var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
		if (!pwdCheck.test(userPwd.value)) {
			alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
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

		var birthChk = /^[0-9]{8}$/;
		if (birth.value == "") {
			alert("생년월일를 입력하세요.");
			birth.focus();
			return false;
		}
		;
		if (!birthChk.test(birth.value)) {
			alert("생년월일은 숫자 8자리를 입력해야합니다.");
			birth.focus();
			return false;
		}
		;

		var phone = /^[0-9]+/g;
		if (tel.value == "") {
			alert("전화번호를 입력하세요.");
			tel.focus();
			return false;
		}
		;
		if (!phone.test(tel.value)) {
			alert("전화번호는 숫자만 입력할 수 있습니다.");
			tel.focus();
			return false;
		}
		;
		document.regForm.submit();

	}

	document.addEventListener('DOMContentLoaded', function() {
		var userpwdInput = document.getElementById('userpwd');
		var userpwd2Input = document.getElementById('userpwd2');
		var chkNotice = document.getElementById('chkNotice');

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

				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

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
</script>