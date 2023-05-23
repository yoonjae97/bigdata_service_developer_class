# Generated by Django 4.2.1 on 2023-05-23 06:22

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="Member",
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
                ("userid", models.CharField(max_length=100, unique=True)),
                ("password", models.CharField(max_length=100)),
                ("username", models.CharField(max_length=100)),
                ("email", models.CharField(max_length=100)),
                ("phone", models.CharField(max_length=100)),
                ("wdate", models.DateTimeField()),
            ],
        ),
    ]
