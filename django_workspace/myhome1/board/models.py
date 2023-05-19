from django.db import models

# Create your models here.
# 테이블 스키마를 직접 안만들고 여기서 클래스 만들고
# migrations -> 필요한 쿼리 생성
# migrate -> 테이블 생성

class Board(models.Model):
    title = models.CharField("제목", max_length=400)
    writer = models.CharField("작성자", max_length=40)
    content = models.TextField("내용")
    wdate = models.DateTimeField() # 작성일
    hit = models.IntegerField() # 조회수
    filename = models.CharField(max_length=200)

# python manage.py makemigrations board
# python manage.py migrate 테이블 생성
# select table_name from tabsl
# desc 테이블명