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
from . import views

app_name = 'person'
# redirect(app_name:name속성값)
# person 폴더
# 목록, 등록, 상세화면 기본적으로 3가지 필요
urlpatterns = [
    path("", views.index),
    path("list/", views.list, name='list'), # 목록
    path('write/', views.write), # 등록화면 이동
    path('save/', views.save), # 등록
    path('view/<int:id>', views.view), # 상세화면
]
