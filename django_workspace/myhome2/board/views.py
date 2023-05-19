from django.shortcuts import render
from board.models import Board
# Create your views here.

# ORM 방식 - 테이블이 복잡해지면 사용이 불편
def list(request):
    # Board모델 클래스안에 objects 요소 부모클래스에 존재함
    # boardList = Board.objects.all()
    boardList = Board.objects.order_by('-id')
    return render(request, "board/board_list.html", {'boardList':boardList})

# http://pythonstudy.xyz/python/article/310-Django-%EB%AA%A8%EB%8D%B8-API

# 직접 쿼리 실행하기
from django.db import connection
from common.CommonUtil import dictfetchall
# db 연결자를 가져온다.
def list2(request):
    cursor = connection.cursor()
    # cursor 객체가 db에 데이터 읽고 쓰기를 담당
    sql = """
    select A.id, A.title, A.writer, A.contents, to_char(A.wdate, 'yyyy-mm-dd') wdate, A.hit
    from board_board A
    order by id desc
    """
    cursor.execute(sql)
    print(cursor)
    # fetchall은 tuple형태의 데이터이므로 dict로 바꾸는 함수가 필요
    boardList = dictfetchall(cursor)
    # Board모델 클래스안에 objects 요소 부모클래스에 존재함
    # boardList = None
    for board in boardList:
        print(board)
    return render(request, "board/board_list.html", {'boardList':boardList})


# 데이터 반복 생성
# def list3(request):
#     cursor = connection.cursor()
#     # cursor 객체가 db에 데이터 읽고 쓰기를 담당

#     for i in range(12, 20):
#         sql = f"insert into board_board(title, writer, contents, wdate, hit) values('제목{i}', '작성자{i}', '내용{i}',sysdate, 0)"
#         cursor.execute(sql)
#         # cursor.commit()
#     connection.commit()
#     # cursor = 'select * from board_board'
#     # print(cursor.fetchall())
#     # Board모델 클래스안에 objects 요소 부모클래스에 존재함
#     # boardList = None]
#     return None
