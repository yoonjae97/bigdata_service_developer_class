// 1. 사용자로부터 숫자를 입력받는다.
// 2. 입력값을 백단위만 남긴다.
// 3. 3421 -> 3400만 남도록(백의 자리 이하 버리도록)

let a = parseInt(prompt('첫 번쨰 값'));34
console.log(a - (a%100));

// 자바스크립트는 // 연산자가 없으므로
// /로 나눈 값에 정수로 바꿔주면 몫을 구할 수 있다.

