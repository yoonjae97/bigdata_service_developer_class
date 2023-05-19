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
from board.views import BoardList, BoardDetail

app_name = 'board'

# redirect(app_name:path의 name)
urlpatterns = [
    path("list/", BoardList.as_view(), name='list'), #/board/board_list.html
    path('detail/<int:pk>/', BoardDetail.as_view()), #/board/board_detail.html
    path("write/", views.write),
    path('save/', views.save),
]
