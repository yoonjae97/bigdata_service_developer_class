// 반복문 : 어떤 조건을 만족할 때 까지 같은 처리를 반복하여 실행하는 구문

// 1. while문

// let num1 = 0;
// while(num1 < 3) {
//     console.log(num1);
//     num1++;
// } 

// let num2 = 3;
// while(true) {
//     console.log(num2);
//     num2++;
//     if(num==2) break;
// }

// function washdish() {
//     document.write('설거지 했습니다<br>');
    
// }

// let i = Number(prompt('접시가 몇장인가요?'));

// for (i; i >0; i--) {
//     washdish()
// }

// while (i > 0) {
//     washdish();
//     i--;
// }

// do {
//     washdish();
//     i--;
// } while (i > 0)

// let num3 = 9;

// while (num3<9) {
//     console.log('while문', num3);
// }

// do {
//     console.log('while문', num3);
// } while (num3 < 9)

// for(let num4=9; num4<12; num4++) {
//     console.log(num4);
// }

function washDishes(dishType) {
    document.write(dishType + ' 설거지 했습니다.<br>');
}

// const dishes = ['dish1', 'dish2', 'dish3', 'dish4', 'dish5']

// for (let i = 0; i < dishes.length; i++) {
//     washDishes(dishes[i]);
// }

const dishWasher = ['plate', 'bowl', 'cup'];
for (let dishes of dishWasher) {
    washDishes(dishes);
}