from django.contrib import admin
from django.urls import path

# . 동일한 폴더에 있는 views라는 파일 사용
from . import views

# list타입, path라이브러리는 url과 파일을 연결
# /pybo ==> views.index함수 호출

urlpatterns = [
    path("", views.index),
]
