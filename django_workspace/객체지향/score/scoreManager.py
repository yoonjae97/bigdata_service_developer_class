# 데이터 관리 클래스

from scoreData import Score

class ScoreManager:
    
    def __init__(self):
        self.dataList = list()
        self.dataList.append(Score("A", 90, 80, 70))
        self.dataList.append(Score("B", 80, 80, 70))
        self.dataList.append(Score("C", 70, 70, 70))

    def append(self):
        data = Score()
        data.name = input('이름 : ')
        data.kor = int(input('국어 : '))
        data.eng = int(input('영어 : '))
        data.mat = int(input('수학 : '))
        data.process()
        self.dataList.append(data)
    
    def menuDisplay(self):
        print()
        print("1. 전체출력")
        print("2. 추가   ")
        print("3. 검색   ")
        print("4. 수정   ")
        print("5. 삭제   ")
        print("6. 정렬   ")
        print("0. 종료   ")        

    def output(self):
        for data in self.dataList:
            print(data.toString())

    def search(self):
        name = input('찾을 이름: ')
        resultList = list(filter(lambda score: score.name == name, self.dataList))
        for data in resultList:
            print(data.toString())

    def modify(self):
        name = input('수정할 이름 : ')
        resultList = list(filter(lambda score: score.name == name, self.dataList))
        if len(resultList) == 0:
            print(name + '을 찾을 수 없습니다')
            return # 여기서 함수 종료

        # 성공적일 때
        score = resultList[0]
        score.name = input('바꿀 이름 : ')
        score.kor = int(input('국어 :'))
        score.eng = int(input('영어 :'))
        score.mat = int(input('수학 :'))
        score.process()

    def delete(self):
        name = input('삭제할 이름 : ')
        resultList = list(filter(lambda score: score.name == name, self.dataList))
        if len(resultList) == 0:
            print(name + '을 찾을 수 없습니다')
            return # 여기서 함수 종료  
        
        self.dataList.remove(resultList[0])

    def sort(self):
        sortList = sorted(self.dataList, key=lambda x : x.total, reverse=True)
        for data in sortList:
            print(data.toString())


    def start(self):
        while True:
            self.menuDisplay()
            sel = input("선택 : ")
            if sel == "1":
                self.output()
            elif sel == "2":
                self.append()
            elif sel == "3":
                self.search()
            elif sel == "4":
                self.modify()
            elif sel == "5":
                self.delete()
            elif sel == "6":
                self.sort()
            elif sel == "0":
                return #함수를 종료한다


