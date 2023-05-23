from django.db import models
from django.views.generic import DetailView, ListView

# Create your models here.
class Board(models.Model):
    title = models.CharField(max_length=100)
    writer = models.CharField(max_length=100)
    contents = models.TextField()
    wdate = models.DateTimeField()
    hit = models.IntegerField(default=0)

# class Answer(models.Model):
#     writer = models.ForeignKey(Board, on_delete=models.CASCADE)
#     review = models.TextField()
#     wdate = models.DateTimeField()

# python manage.py makemigrations board
# python manage.py migrate
# settings.py 에서 작업 (db정보 바꾸고) 후 진행