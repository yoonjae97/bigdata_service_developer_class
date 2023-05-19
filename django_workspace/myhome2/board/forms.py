from django import forms

# 모델 클래스 import
# from 폴더명.파일명 import 클래스명
from board.models import Board

class BoardForm(forms.ModelForm):
    class Meta:
        model = Board # model 변수에 모델 클래스를 연결
        fields = ['title', 'writer','contents']
        labels = {
            'title':'제목',
            'writer':'작성자',
            'contents':'내용'
        }