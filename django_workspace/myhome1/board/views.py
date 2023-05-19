from django.shortcuts import render, redirect
from django.views.generic import ListView, DetailView
from board.models import Board
from board.forms import BoardForm
from django.utils import timezone
# Create your views here.
# view 생성
class BoardList(ListView):  # 목록자료
    model = Board           # object_list로 전달

class BoardDetail(DetailView):  # 상세사죠
    model = Board               # object로 전달

def write(request):
    context = {"form": BoardForm(request.POST)}
    return render(request, "board/board_write.html", context)

def save(request):
    form = BoardForm(request.POST)
    board = form.save(commit=False) # 예비저장 commit=False 임시저장
    board.wdate = timezone.now()
    board.hit = 0
    board.save()
    return redirect("board:list")