from django.shortcuts import render
from django.views.generic import ListView, DetailView
from board.models import Board

# Create your views here.
# view 생성
class BoardList(ListView):
    model = Board

class BoardDetail(DetailView):
    model = Board
