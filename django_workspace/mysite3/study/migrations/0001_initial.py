# Generated by Django 4.2.1 on 2023-05-18 02:10

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="Study",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("name", models.CharField(max_length=40, verbose_name="이름")),
                ("kor", models.IntegerField(verbose_name="국어")),
                ("eng", models.IntegerField(verbose_name="영어")),
                ("mat", models.IntegerField(verbose_name="수학")),
            ],
        ),
    ]
