const menu = prompt('음료를 입력해주세요');
const cash = parseInt(prompt('돈을 넣어주세요'));
let price = 0;

switch (menu) {
    case '솔의눈':
        price = 400;
        break;
    
    case '콜라':
        price = 500;
        break;
    
    case '밀키스':
        price = 600;
        break;
    
    case '오렌지주스':
        price = 700;
        break;
}

if (!cash | cash < 0) {
    document.write('돈을 넣어주세요');
} else if (cash < price) {
    document.write('돈이 모자랍니다')
} else if (cash > 5000) {
    document.write('금액이 너무 큽니다')
} else {
    document.write(`${menu}의 가격은 ${price}입니다. ${cash}에 대한 거스름돈 ${cash-price}입니다`)
}

// drow = function(){
//     // document.write('솔의 눈 400원입니다');
//     // let price = document.getElementsByClassName('price')[0].value;
//     // document.write(price);
// }
// function choice(obj_value, obj_text){
//     menu = obj_value
//     price = obj_text
//     document.write(menu, price)
//     // // price = document.getElementsByClassName(classname)[0].value;
//     // // document.write(price);
// }