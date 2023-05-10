// 내장객체 : 자바스크립트에서 제공하는 객체
// Math, Date, Array 

let today = new Date();
console.log(today);

let yesterday = new Date(2023, 4, 9);
console.log(yesterday);

let tYear = today.getFullYear();
let tMonth = today.getMonth() + 1; // 0~11로 반환
let tDate = today.getDate();
let tDay = today.getDay(); // 0~6로 반환
let tHour = today.getHours();
let tMinute = today.getMinutes();
let tSeconds = today.getSeconds();

// 요일 0~6 -> 일요일~토요일까지

switch(tDay) {
    case 0 : tDay='일'; break;
    case 1 : tDay='월'; break;
    case 2 : tDay='화'; break;
    case 3 : tDay='수'; break;
    case 4 : tDay='목'; break;
    case 5 : tDay='금'; break;
    case 6 : tDay='토'; break;
}

// 출력형태
// 00년 00월 00일 00요일 00시 00분 00초 입니다. -> 동적으로 변환

document.write(`${tYear}년 ${tMonth}월 ${tDate}일 <br> ${tDay}요일 ${tHour}시 ${tMinute}분 ${tSeconds}초 입니다.`)