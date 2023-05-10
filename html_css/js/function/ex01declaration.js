// 함수 : 특정 기능을 수행하는 소스코드를 하나로 묶어서 필요할 때마다 사용하기 위한 구조

// 파이썬 : def function ~
// 자바스크립트 : function 이름 () {소스코드}

// function 소개() {
//     document.write(`SW반에서 요정을 맡은 임용진입니다.<br>`);
// }



// 화살표 함수: 함수 표현식 형태, function의 키워드 대신 =>
// let a = function() {}
// let a = () => {}
var 소개 = () => {
    document.write(`SW반에서 요정을 맡은 임용진입니다.<br>`);
}

소개();

// // 매개변수

// function 함수(파라미터) {
//     document.write(`SW반에서 비주얼을 담당하고 있는 ${파라미터}입니다.<br>`)
// }

// 함수('장호찬');

// // 매개변수 + return

// function 함수3(파라미터) {
//     return `SW반에서 귀여움을 맡은 ${파라미터}입니다.`
// }

// 함수3('박석민');
// document.write(함수3('박석민'));


