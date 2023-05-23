from django.shortcuts import render, redirect
from django.utils import timezone
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
from common.CommonUtil import dictfetchall, commonPage
# db 연결자를 가져온다.

#/board/list2/1
#/board/list2?pg=1

def list2(request, pg):
    cursor = connection.cursor()
    # cursor 객체가 db에 데이터 읽고 쓰기를 담당
    # 전체 데이터 건수를 알아야 페이징이 가능하다
    sql = "select count(*) from board_board"
    cursor.execute(sql)
    totalCnt = int(cursor.fetchone()[0])
    cp = commonPage(totalCnt, pg, 10)
    sql = f"""
    select A.id, A.title, A.writer, A.contents, 
        to_char(A.wdate, 'yyyy-mm-dd') wdate, A.hit    
    from 
    (
        select id, title, writer, contents, wdate, hit,
        row_number() over(order by id desc) num,
        ceil(row_number() over(order by id desc)/10)-1 pg
        from board_board
    )A
    where A.pg={pg}
    """
    cursor.execute(sql)
    # fetchall은 tuple형태의 데이터이므로 dict로 바꾸는 함수가 필요
    boardList = dictfetchall(cursor)
    # Board모델 클래스안에 objects 요소 부모클래스에 존재함
    # boardList = None
    return render(request, "board/board_list.html", {'boardList':boardList, "commonPage":cp})


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

def write(request):
    return render(request, '"board/board_write.html"')

def views(request, id):
    # 장고 프레임워크
    board = Board.objects.get(id=id)
    board.hit = board.hit + 1
    board.save()

    return render(request, 'board/board_view.html', {'boardItem':board})

from board.forms import BoardForm
def write(request):
    return render(request, 'board/board_write.html')

def save(request):
    # request.POSt 파라미터 값 받아온거로 BoardForm
    form = BoardForm(request.POST)
    # 비어있는 필드가 있으면 저장못함, 임시 저장
    board = form.save(commit=False)
    # 쿼리셋 객체를 생성 - 완전히 디비에 바로 저장하려면
    # 나머지 필드들에 값이 없어서 저장불가
    board.wdate = timezone.now()
    board.hit = 0
    board.save()
    
    return redirect("board:list", pg=0)
    # redirect - namespace app_name='board' urls.py파일에서 path의 name속성값하고 합친거

# Ajax - 데이터를 json형태로 주고 받음
#      - 백그라운드에서 데이터 json 형태로 주고받을 수 있게 해준다
# {'키1':'값1',...}
from django.http import JsonResponse
import json
def modify(request):
    #jsonObject = json.loads(request.body)
    result = {"result": "success", "title":"title",
              "writer":"writer"}
    return JsonResponse(result, status=200)

# from board.forms import reviewForm
# def review_write(request, id):
#     form = reviewForm(request.POST)
#     board = form.save(commit=False)
#     board.wdate = timezone.now()
#     board.save()

#     return redirect('board:view', id=id)