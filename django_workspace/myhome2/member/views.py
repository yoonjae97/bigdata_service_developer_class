from django.shortcuts import render
from django.http import JsonResponse
from member.models import Member
# Create your views here.
# path('write', views.write), # 회원가입폼으로 이동
# path('save', views.save), # 회원가입
# path('idcheck', views.idcheck), # 아이디 중복체크
# path('logon', views.logon), # 페이지 이동용
# path('logout', views.logout), # 페이지 이동용
# path('logon_proc', views.logon_proc), # 로그온처리
# path('logout_proc', views.logout_proc), # 로그아웃 처리

def write(request):
    return render(request, 'member\member_write.html')

def logon(request):
    return render(request, 'member\logon.html')

def logout(request):
    return render(request, 'member\logout.html')

from member.forms import MemberForm
from django.utils import timezone
def save(request):
    try:
        memberForm = MemberForm(request.POST)
        member = memberForm.save(commit=False)
        member.wdate = timezone.now()
        member.save()
        result = {"result":"success"}
    except Exception as ex:
        print(ex)
        result = {"result":"fail"}
    return JsonResponse(result)

def logon_proc(request):
    result = {"result":"OK"}
    return JsonResponse(result)

def logout_proc(request):
    result = {"result":"OK"}
    return JsonResponse(result)

def idcheck(request):
    userid = request.POST.get('userid')
    print(userid)
    try:
        Member.objects.get(userid=userid) # 이미 아이디 사용중
        result = {"result":"fail"}
    except:
        result = {"result":"success"} # 아이디 안쓰고 있음 사용 가능
    return JsonResponse(result)
