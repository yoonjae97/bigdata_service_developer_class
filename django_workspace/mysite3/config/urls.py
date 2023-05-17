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
from django.urls import path, include

# include = 외부의 url 파일을 연동
urlpatterns = [
    path("admin/", admin.site.urls),
    path('person/', include('person.urls')),
    # include ('경로명.파일명)
    # 앞으로 url이 person/이 오면 person폴더의 url.py가 다 처리
    path('study/', include('study.urls'))
]
