"""
URL configuration for config project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.http import HttpResponse

def index(request):
    response =  HttpResponse('<h1>hi</h1>')
    return response

# 함수들은 매개변수로 request를 가져와야하고 return은 response 객체이어야 한다
def test1(request):
    response = HttpResponse("<h1>test</h1>")
    return response

def test2(request):
    response = HttpResponse('<h1>황윤재</h1>')
    return response

def add(request):
    x = int(request.GET.get('x')) # GET방식의 데이터 받아오기
    y = int(request.GET.get('y'))

    response = HttpResponse(f"{x}+{y}={x+y}")
    return response

def sub(request):
    x = int(request.GET.get('x')) # GET방식의 데이터 받아오기
    y = int(request.GET.get('y'))

    response = HttpResponse(f"{x}-{y}={x-y}")
    return response

# GET방식 POST방식
# GET : 2048byte 이내의 보안을 요하지 않는 간단한 정보
# POST : 무한정, 보안을 요하는 정보

# GET전송방식
# http://127.0.0.1:8000/add?x=7&y=8
# url / add
# x라는 정보와 y라는 정보를 서버로 전송
# ? 뒤에가 정보. 키1=값1&키2=값2 형태로 전송

# GET 업그레이드 - 추천
# http://127.0.0.1:8000/mul/7/8

# path(mul/<x>/<y>)
# django 함수의 첫 파라미터는 항상 request
def mul(request, x, y):
    x = int(x)
    y = int(y)
    response = HttpResponse(f"{x}*{y}={x*y}")
    return response

# 구구단
# http://127.0.0.1:8000/gugu1?dan=5

def gugu1(request):
    dan = int(request.GET.get('dan'))
    result = ''
    for i in range(1, 10):
        result += f'{dan}*{i}={dan*i}<br>'

    res = HttpResponse(result)
    return res

# http://127.0.0.1:8000/gugu2/5
def gugu2(request, dan):
    result = ''
    for i in range(1, 10):
        result += f'{dan}*{i}={dan*i}<br>'

    res = HttpResponse(result)
    return res

# http://127.0.0.1:8000/score/Tom/90/80/100
# Tom의 총점은 270이고 평균은 90입니다
def score(request, name, a, b, c):
    lst = [a, b, c]
    total = sum(lst)
    avg = round(total/len(lst))
    res = HttpResponse(f'{name}의 총점은 {total}이고 평균은 {avg}입니다')
    return res

urlpatterns = [
    path("admin/", admin.site.urls),
    path('', index),
    path('test1', test1), # url과 함수를 연동
    path('test2', test2),
    path('add', add),
    path('sub', sub),
    path('mul/<x>/<y>', mul),
    path('gugu1', gugu1),
    path('gugu1/<int:dan>', gugu2),
    path('score/<name>/<int:a>/<int:b>/<int:c>', score),
]






