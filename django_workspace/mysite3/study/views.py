from django.shortcuts import render, redirect
from django.http import HttpResponse

scoreList = [
    {"id":0 ,"name":'A', "kor":100, "eng":90, "mat":95},
    {"id":1 ,"name":'B', "kor":90, "eng":85, "mat":90},
    {"id":2 ,"name":'C', "kor":80, "eng":80, "mat":80},
    {"id":3 ,"name":'D', "kor":70, "eng":95, "mat":85},
]
    
def index(request):
    return HttpResponse('<h1>study  </h1>')


# Create your views here.
def list(request):
    return render(request, "study/study_list.html", {"scoreList": scoreList})

def write(request):
    return render(request, 'study/study_write.html')

def save(request):
    name = request.POST.get('name')
    kor = request.POST.get('kor')
    eng = request.POST.get('eng')
    mat = request.POST.get('mat')
    id = len(scoreList)
    scoreList.append( {"id":id, "name":name, "kor": kor, "eng":eng, "mat":mat})
    # 등록 후 list 페이지로 이동한다.
    # 보통 게시글 등록 후 list페이지로 이동하는데 직접 함수를 호출하지 못한다
    # 브라우저에서 정보를 보낸것처럼 해야한다.
    return redirect("study:list")

# view/<int:id> 일때는 함수의 매개변수로 온다
# get -> request.GET.get('form태그의 name속성')
# post -> request.POST.get('form태그의 name속성')

def view(request, id):
    if id < 0 or id >= len(scoreList):
        return HttpResponse('데이터 없음')
    # html파일과 결합하려면 render함수를 쓴다.
    return render(request, 'study/study_view.html', {"data":scoreList[id]})