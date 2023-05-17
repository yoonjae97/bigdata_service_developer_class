from wageManager import weeklywageManager


# print(__name__) # 내장변수  파일 직접 실행시 __main__
# 또다른 파일에서 import하고 실행하면 scoreData

# if __name__ == '__main__': # 테스트 파일 생성
# 현재 파일을 직접 실행시킬경우만 실행
# 다른 곳에서 파일 실행시 테스트파일은 작동 안함2

if __name__ == '__main__':
    m = weeklywageManager()
    m.start()