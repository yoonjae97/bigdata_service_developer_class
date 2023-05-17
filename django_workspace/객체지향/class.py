
# 클래스 - 한사람분의 데이터
class Person:
    name = '홍길동' # 다른 언어라면 계속 새로 만들어진다. 그러나 파이썬은 그냥 쓰다가
    phone = '010-0000-0000'  # 변수의 값을 바꿀 때 새로 만든다.
    # 클래스 정의할 때 딱 한번 생성. list, dict 타입은 같은 메모리 공유
    # 충돌문제 발생할 수 있음. 
    # 생성자라는 특수한 함수가 있는데 주로 여기서 변수를 생성
    # 생성자는 객체가 생성될 때 자동으로 호출되는 메서드(함수)이다.
    # 생성자를 시스템이 호출 - 내가 만들면 시스템이 호출 __init__
    # 파이썬은 파일안에 함수의 이름이 하나이어야 한다. 클래스 내 동일한 이름의 함수 생성 불가능
    # 객체지향언어의 특징 : 추상성, 은닉성, 다형성, 상속성
    # 추상성, 상속성 최근 경향이 은닉성을 약화시킨다.
    # 다형성 : 이름이 하나인데 모습이 다르다. print(4)  print('a')  print(4, 5)
    # 파이썬에서는 다형성을 기본값으로 지원한다.

    def __init__(self, name='', phone='', address=''):
        self.name = name
        self.phone = phone
        self.address = address
        print('생성자 호출')

    def output(self):     # 클래스안에 있는 함수는 첫 번째 매개변수가 self이다. 
                          # 객체자신에 대한 참조
        print(self.name, self.phone, self.address)

# 객체만들기
p1 = Person()
p1.output()

p1.name = '임꺽정' # 변수 공간 새로 생성

p2 = Person('임꺽정')
p3 = Person('장길산', '010-0000-0001')
p4 = Person('강감찬', '010-0000-0002', '낙성대')

p1.output()
p2.output()
p3.output()
p4.output()

# 객체배열만들기
personList = [

    Person('A', '1', '총신대역'),
    Person('B', '2', '낙성대역'),
    Person('C', '3', '신림역'),
    Person('D', '4', '봉천역'),
    Person('E', '5', '서울대입구역'),
]

for person in personList:
    person.output()
