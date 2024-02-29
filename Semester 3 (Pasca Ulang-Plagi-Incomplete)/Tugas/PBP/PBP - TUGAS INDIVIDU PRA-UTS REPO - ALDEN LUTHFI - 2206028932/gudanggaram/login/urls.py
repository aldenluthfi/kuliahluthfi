from django.urls import path
from login.views import register, login_user, logout_user

app_name = 'login'

urlpatterns = [
    path('register/', register, name='register'),
    path('login/', login_user, name='login'),
    path('logout/', logout_user, name='logout'),
]