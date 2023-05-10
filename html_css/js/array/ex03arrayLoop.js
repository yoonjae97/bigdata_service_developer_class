// 배열에 저장된 데이터를 반복문으로 출력하기

//  for문
// let nameList = ['이람다', '최성우', '김수인'];

// for (let i = 0; i < nameList.length; i++) {
//     document.write(nameList[i] + '<br>');
// }

// for of문
// let foodList = ['아이스크림', '치킨', '피자', '떡볶이'];

// for(let food of foodList) {
//     document.write(food + '<br>');
// }

// forEach 문
let colorList = new Array('빨강', '하늘', '검정', '보라');
colorList.forEach((element, index) => {
    document.write(index, element + '<br>');
});