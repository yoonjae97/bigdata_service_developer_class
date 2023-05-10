// 두 개의 숫자 입력

let num1 = Number(prompt('첫 번째 숫자를 입력해주세요'));
let num2 = Number(prompt('두 번째 숫자를 입력해주세요'));

// 1, 함수 선언문
// function 함수(파라미터) { 실행문 }
function sum1(num1, num2) {
    return (num1 + num2)
}
console.log(sum1(num1, num2));

// 2. 함수 표현식
// const 함수 = function(파라미터) { 실행문 }
const sum2 = function(num1, num2) {
    return (num1 + num2)
}
console.log(sum2(num1, num2));

// 3. 화살표 함수
// const 함수 = (파라미터) => {}
const sum3 = (num1, num2) => {
    return (num1 + num2)
}
console.log(sum3(num1, num2));