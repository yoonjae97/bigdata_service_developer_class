let cafeMenu = prompt('어떤 음료로 하시겠어요?');

// cafeMenu == '아메리카노'
// ? document.write('주문하신 메뉴 나왔습니다.')
// : document.write(`${cafeMenu}는 대기시간 5분 입니다.`);
// 조건문 : 조건 ? 참일때 : 거짓일 때;


// if (cafeMenu == '아메리카노') {
//     document.write('주문하신 메뉴 나왔습니다.');
// } else if (cafeMenu == '딸기라떼') {
//     document.write('겨울 한정메뉴 입니다.');
// } else {
//     document.write(`${cafeMenu}는 대기시간 5분 입니다.`);
// }

// cafeMenu == '딸기라떼' && document.write('겨울 한정메뉴 입니다.');

//  switch(대상)

switch(cafeMenu){
    case '아메리카노': 
        document.write('주문하신 메뉴 나왔습니다.'); 
        break;
    case '카페라떼' : 
        document.write('카페라떼는 대기시간 3분 입니다.'); 
        break;
    case '카라멜 마끼아또': 
        document.write(`카라멜 마끼아또 대기시간 4분 입니다.`); 
        break;
    case '딸기라떼':
        document.write('겨울 한정메뉴 입니다.');
        break;
}