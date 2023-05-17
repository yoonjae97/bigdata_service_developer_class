# 람다 = 1줄짜리 쓰고 버리는 함수
# 함수를 만들때 이름도 없고 그냥 한줄짜리 만들어서 쓰고 버리는 함수
# lambda 매개변수들 : 코드

"""
def add (x, y):
    return x + y
"""
add = lambda x, y : x+y # return 생략
#print(add(4, 5)) # 일반 함수들의 경우에는 메모리 항상 차지
                 # 람다는 메모리를 항상 차지할 필요가 없다

nums = [4, 2, 11, 15, 41, 7, 9, 12]
evenList = []
oddList = []

# filter(함수, 배열) : 함수를 두번째 매개변수로 전달된 리스트에 요소마다 한번씩 호출
#                    : 첫번째 인자로 전될되는 함수는 매개변수가 하나고, 반환값이 True 또는 False 인 것만 보내준다.

def even(x):
    if x % 2 == 0:
        return True
    else:
        return False

evenList = list(filter(even, nums))
#print(evenList)

evenList = list(filter(lambda x : x % 2 == 0, nums))
#print(evenList)

wordList = ['school', 'person', 'lunch', 'desk', 'hospital', 'house']
hList = list(filter(lambda x : x.startswith('h'), wordList))
print(hList)





