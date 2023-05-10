// 1. 데이터를 담은 배열 생성
let numList = [1, 2, 3, 4, 5, 6, 7, 8, 9];
let oddList = [];

// 2. 데이터에서 홀수인 숫자를 찾고 갯수를 세 준다.
for (let i = 0; i < numList.length; i++) {
    if (numList[i] % 2 !== 0) {
        oddList.push(numList[i]);

    }
}

alert(`list에 들어있는 홀수는 ${oddList}이며, 총 ${oddList.length}개 입니다.`)