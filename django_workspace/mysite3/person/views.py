from django.shortcuts import render, redirect
from django.http import HttpResponse

# list -> dict을 넣어서 데이터를 작성해본다
personList = [
    {"id":0, 'name':"홍길동", 'phone':'010-0000-0001'},
    {"id":1, 'name':"임꺽정", 'phone':'010-0000-0002'},
    {"id":2, 'name':"장길산", 'phone':'010-0000-0003'},
    {"id":3, 'name':"이순신", 'phone':'010-0000-0004'},
    {"id":4, 'name':"강감찬", 'phone':'010-0000-0005'},

]

# Create your views here.
def index(request):
    return HttpResponse('<h1>Person</h1>')

def list(request):
    # html과 연동하고 싶다.
    data = {"dataList":personList}
    return render(request, 'person/person_list.html', data)

def write(request):
    return render(request, 'person/person_write.html')

def save(request):
    name = request.POST.get('name')
    phone = request.POST.get('phone')
    
    personList.append( {"name":name, "phone":phone})
    # 등록 후 list 페이지로 이동한다.
    # 보통 게시글 등록 후 list페이지로 이동하는데 직접 함수를 호출하지 못한다
    # 브라우저에서 정보를 보낸것처럼 해야한다.
    return redirect("person:list")

# view/<int:id> 일때는 함수의 매개변수로 온다
# get -> request.GET.get('form태그의 name속성')
# post -> request.POST.get('form태그의 name속성')

def view(request, id):
    if id < 0 or id >= len(personList):
        return HttpResponse('데이터 없음')
    # html파일과 결합하려면 render함수를 쓴다.
    return render(request, 'person/person_view.html', {"data":personList[id]})
# {"data":personList[id]} -> 데이터 전송 시 dict타입으로 전송해야한다.