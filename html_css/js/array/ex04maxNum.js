// 1. 데이터를 담은 배열 생성

let numList = [23, 12, 35, 64, 46];
let maxNum = -1e9;

// 2. 최댓값을 찾아서 출력
for (let num of numList) {
    if (num > maxNum) {
        maxNum = num;
    }
}

alert(`최댓값 >> ${maxNum}`)