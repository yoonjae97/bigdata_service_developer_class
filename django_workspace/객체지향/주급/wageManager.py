# 데이터 관리 클래스

from Wage import calc

class weeklywageManager:
    
    def __init__(self):
        self.dataList = list()
        self.dataList.append(calc("A", 30000, 20))
        self.dataList.append(calc("B", 40000, 40))
        self.dataList.append(calc("C", 50000, 50))

    def append(self):
        data = calc()
        data.name = input('이름 : ')
        data.hourlywage = int(input('시간당급여액 : '))
        data.workhour = int(input('근무시간 : '))
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
        resultList = list(filter(lambda x: x.name == name, self.dataList))
        for data in resultList:
            print(data.toString())

    def modify(self):
        name = input('수정할 이름 : ')
        resultList = list(filter(lambda x: x.name == name, self.dataList))
        if len(resultList) == 0:
            print(name + '을 찾을 수 없습니다')
            return # 여기서 함수 종료

        # 성공적일 때
        score = resultList[0]
        score.name = input('바꿀 이름 : ')
        score.hourlywage = int(input('시간당급여액 :'))
        score.workhour = int(input('근무시간 :'))
        score.process()

    def delete(self):
        name = input('삭제할 이름 : ')
        resultList = list(filter(lambda x: name in x.name, self.dataList))
        if len(resultList) == 0:
            print(name + '을 찾을 수 없습니다')
            return # 여기서 함수 종료  
        for data in resultList:
            self.dataList.remove(data)
        # self.dataList.remove(resultList[0])

    def sort(self):
        sortList = sorted(self.dataList, key=lambda x : x.weeklywage, reverse=True)
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


