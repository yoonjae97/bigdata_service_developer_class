// 1. 사용자로부터 시작숫자와 마지막 숫자를 입력받는다.
// 2. 시작부터 마지막 숫자까지 합을 구한다
// 3. 콘솔창으로 출력
// num1 부터 num2 까지의 합은 >> total 입니다

let num1 = Number(prompt('시작 숫자를 입력해주세요'))
let num2 = Number(prompt('마지막 숫자를 입력해주세요'))
let total = 0;

for (let temp = num1; temp <= num2; temp++) {
    total += temp
}

console.log(`${num1} 부터 ${num2} 까지의 합은 >> ${total} 입니다`)