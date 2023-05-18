from django.db import models

# Create your models here.
class Study (models.Model):
    name = models.CharField("이름", max_length=40)
    kor = models.IntegerField("국어")
    eng = models.IntegerField("영어")
    mat = models.IntegerField("수학")

    # 객체를 출력하면 이 함수가 호출되면서 데이터 내용이 출력된다.
    # __str__ : 이미 부모클래스에 존재하는 함수를 다시 정의한다
    def __str__(self):
        return f"{self.name} {self.kor} {self.eng} {self.mat}"