from django.db import models

# Create your models here.
class Member(models.Model):
    userid = models.CharField(max_length=100, unique=True)
    password = models.CharField(max_length=100)
    username = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    phone = models.CharField(max_length=100)
    wdate = models.DateTimeField()

# python manage.py makemigration member