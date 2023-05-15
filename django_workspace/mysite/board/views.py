from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
# 웹서버(서비스를 제공하는 측, h/w 또는 s/w)
# 웹클라이언트(서비스를 제공받는 측), 브라우저 

# 브라우저 -----------> request 객체에 담아 보낸다
# 서버 ---------------> response

# request는 브라우저로부터 요청을 받아온다
def index(request):
    return HttpResponse('<h1>게시판</h1>')

