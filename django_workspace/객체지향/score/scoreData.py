# scoreData.py
# 데이터 클래스
class Score:
    
    def __init__(self, name='홍길동', kor=0, eng=0, mat=0):
        # self - 나
        self.name = name
        self.kor = kor
        self.eng = eng
        self.mat = mat
        self.process() # 값 입력할때마다 자동으로 총 평균 구하기

    def process(self):
        self.total = self.kor + self.eng + self.mat
        self.avg = self.total / 3
        
        if self.avg >= 90:
            self.grade = '수'
        elif self.avg >= 80:
            self.grade = '우'
        elif self.avg >= 70:
            self.grade = '미'
        elif self.avg >= 60:
            self.grade = '양'
        else:
            self.grade = '가'

    def toString(self):
        return f'{self.name} {self.kor} {self.eng} {self.mat} {self.total} {self.avg} {self.grade}'



# print(__name__) # 내장변수  파일 직접 실행시 __main__
# 또다른 파일에서 import하고 실행하면 scoreData

# if __name__ == '__main__': # 테스트 파일 생성
# 현재 파일을 직접 실행시킬경우만 실행
# 다른 곳에서 파일 실행시 테스트파일은 작동 안함









