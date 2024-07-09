from django.urls import path
from authentication.views import index, login, register, logout

app_name = 'authentication'

urlpatterns = [
    path('', index, name='index'),
    path('auth/login/', login, name='login'),
    path('auth/register/', register, name='register'),
    path('auth/logout/', logout, name='logout'),
]