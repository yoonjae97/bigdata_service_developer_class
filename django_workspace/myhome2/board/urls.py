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

# redirect에서 필요
app_name = 'board'

urlpatterns = [
    path("list/", views.list), # orm방식
    path('list2/<int:pg>', views.list2, name='list'), # 직접 쿼리 실행
    path('view/<int:id>', views.views, name='view'),
    path('write', views.write),
    path('save', views.save),
    path('modify', views.modify),
    #path('review_write', views.review_write)
]
