// 두 가지 정보를 입력
// 1. 이모티콘 2. 숫자
// 숫자만큼의 층을 가진 직각삼각형 만들기

let emo = prompt('이모티콘을 입력해주세요')
let num1 = Number(prompt('숫자를 입력해주세요'))

function triangle(emo, num1) {
    for (i = 1; i <= num1; i++) {
        document.write(emo.repeat(i)+'<br>');
    }
}

triangle(emo, num1);

// 함수 선언문
// function triangle(emo, num1) {
//     for (let i=1; i<=num1; i++) {
//         for (let j=1; j<=i; j++) {
//             document.write(emo);
//         }
//         document.write('<br>')
//     }
// }

// // 함수 표현식
// const pyramid = function (emo, num1) {
//     for (let i=1; i<=num1; i++) {
//         for (let j=1; j<=i; j++) {
//             document.write(emo);
//         }
//         document.write('<br>')
//     }
// }

// // 화살표 함수
// const pyramid3 = (emo, num1) => {
//     for (let i=1; i<=num1; i++) {
//         for (let j=1; j<=1; j++) {
//             document.write(emo);
//         }
//         document.write('<br>')
//     }
// }