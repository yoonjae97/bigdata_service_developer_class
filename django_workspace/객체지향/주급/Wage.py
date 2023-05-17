# scoreData.py
# 데이터 클래스
class calc:
    
    def __init__(self, name='', hourlywage=0, workhour=0):
        # self - 나
        self.name = name
        self.hourlywage = hourlywage
        self.workhour = workhour
        self.process() # 값 입력할때마다 자동으로 총 평균 구하기

    def process(self):
        self.weeklywage = self.hourlywage * self.workhour

    def toString(self):
        return f'{self.name} {self.hourlywage} {self.workhour} {self.weeklywage}'

# print(__name__) # 내장변수  파일 직접 실행시 __main__
# 또다른 파일에서 import하고 실행하면 scoreData

# if __name__ == '__main__': # 테스트 파일 생성
# 현재 파일을 직접 실행시킬경우만 실행
# 다른 곳에서 파일 실행시 테스트파일은 작동 안함









