from django.shortcuts import render, redirect
from django.http import HttpResponse
from django.views.generic import ListView, DetailView
from study.models import  Study
from study.forms import StudyForm

scoreList = [
    {"id":0 ,"name":'A', "kor":100, "eng":90, "mat":95},
    {"id":1 ,"name":'B', "kor":90, "eng":85, "mat":90},
    {"id":2 ,"name":'C', "kor":80, "eng":80, "mat":80},
    {"id":3 ,"name":'D', "kor":70, "eng":95, "mat":85},
]
    
def index(request):
    return HttpResponse('<h1>study  </h1>')


# Create your views here.
# def list(request):
#     return render(request, "study/study_list.html", {"scoreList": scoreList})

def write(request):
    form = StudyForm(request.POST)
    context = {"form":form}
    return render(request, 'study/study_form.html', context)

def save(request):
    # name = request.POST.get('name')
    # kor = request.POST.get('kor')
    # eng = request.POST.get('eng')
    # mat = request.POST.get('mat')
    # id = len(scoreList)
    # scoreList.append( {"id":id, "name":name, "kor": kor, "eng":eng, "mat":mat})
    # 등록 후 list 페이지로 이동한다.
    # 보통 게시글 등록 후 list페이지로 이동하는데 직접 함수를 호출하지 못한다
    # 브라우저에서 정보를 보낸것처럼 해야한다.
    
    # form 객체 전부 가져온다
    form = StudyForm(request.POST)
    if form.is_valid():
        result = form.save(commit=True) # DB에 저장
        # 알아서 저장된다. - insert query를 자동으로 생성한다.
    return redirect("study:list")

# view/<int:id> 일때는 함수의 매개변수로 온다
# get -> request.GET.get('form태그의 name속성')
# post -> request.POST.get('form태그의 name속성')

# def view(request, id):
#     if id < 0 or id >= len(scoreList):
#         return HttpResponse('데이터 없음')
#     # html파일과 결합하려면 render함수를 쓴다.
#     return render(request, 'study/study_view.html', {"data":scoreList[id]})

# 장고프레임워크가 제공
class StudyList(ListView):
    model = Study
    # 테이블 모듈명_모듈명(study_study) 테이블에서 데이터를 읽어온다
    # 내부에 object_list라는 변수가 있는데 이 변수에 값을 저장하고
    # templates/study/study_list.html파일을 호출하라

class StudyView(DetailView):
    model = Study
    # 해당항목 가져와서 object에 담아 보낸다