# 기본값 : 함수의 매개변수에 기본값을 할당하면 함수를 여러가지 방법으로 다양하게 호출 가능

def myfunc(a=1, b=2, c=3):
    return a+b+c

print(myfunc())
print(myfunc(10))
print(myfunc(10, 20))
print(myfunc(10, 20, 30))
print(myfunc(b=100))



