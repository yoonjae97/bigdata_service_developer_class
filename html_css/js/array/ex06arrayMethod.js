let nameList = ['이람다', '최성우', '김윤호'];

// 배열함수

// 1. 마지막 인덱스 추가 : push()
nameList.push('박병관');
document.write(`마지막 추가 : ${nameList}<br>`);

// 2. 마지막 인덱스 제거 : pop()
nameList.pop();
document.write(`마지막 삭제 : ${nameList}<br>`);

// 3. 첫 번째 인덱스에 데이터 추가 : unshift()
nameList.unshift('임용진');
document.write(`첫번째 추가 : ${nameList}<br>`);

// 4. 첫 번째 인덱스에 데이터 삭제 : shift()
nameList.shift();
document.write(`첫번째 삭제 : ${nameList}<br>`);

// 5. 원하는 위치에 데이터 추가 혹은 삭제
// 추가 : splice(원하는 인덱스, 0, 추가할 데이터)
nameList.splice(1, 0, '박소미');
document.write(`splice 삭제 : ${nameList}<br>`);
// 삭제 : splice(원하는 인덱스, 1)
nameList.splice(1, 1);
document.write(`splice 추가 : ${nameList}<br>`);

