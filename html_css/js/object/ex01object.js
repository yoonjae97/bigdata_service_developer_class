// 객체 : 여러 데이터를 하나의 변수에 저장할 수 있는 데이터 타입

// 구조 : {key1:value1, key2:value2,...}
// key : 객체의 속성(property)
// value : 속성의 값 / 기본 자료형, 함수, 배열, 객체 다 가능

let person = {
    name : "이호준",
    age : 30,
    wearGlasses : false,
    introduce : function() {
        console.log('오늘도 공부를 열심히 합니다.');
    },
    favorite : {
        food : "chicken",
        drink : 'americano',
    },
    smhrd : ['이람다', '최성우', '김윤호']
}

// 객체 접근
console.log(person);

person.name = '김민지';
console.log(person);

person.class = 'B';
console.log(person);




