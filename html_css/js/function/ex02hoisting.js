// 호이스팅 : 선언문이 코드의 선두로 끌어올려진 것처럼 동작하는 현상

// 소스코드 처리 과정 : 평가 -> 실행
// 소스코드 평가 : 모든 선언문(변수, 함수 등)등록
// 소스코드 실행 (런타임)


// 변수 호이스팅
    // var gohome = '집에 가고 싶다.';
    // console.log(gohome);

// 선언한 시점과 상관 없이 끌어올려져서 선언된 것처럼 작동한다.

func1('위 :');
// func2('아래 :')
function func1(val) {
    document.write(val, '함수 선언문');
}

const func2 = function(val) {
    document.write(val, '함수 선언문');
}

func1('아래: ')
func2('아래: ')