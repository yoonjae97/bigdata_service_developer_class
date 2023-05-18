from django import forms
from study.models import Study

class StudyForm(forms.ModelForm):
    class Meta: # 클래스안에 Meta라는 이름의 클래스를 또 만들어야 한다.
        model = Study
        fields = ["name", 'kor', 'eng', 'mat']
        labels = {
            "name":"이름",
            "kor":"국어",
            "eng":"영어",
            "mat":"수학"
        }