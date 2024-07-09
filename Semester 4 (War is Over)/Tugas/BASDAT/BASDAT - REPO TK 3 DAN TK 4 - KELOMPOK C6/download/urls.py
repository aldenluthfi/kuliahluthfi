from django.urls import path
from .views import index, add_download_view, delete_show

app_name = 'download'

urlpatterns = [
    path('', index, name='index'),
    path('add/', add_download_view, name='add_download'),
    path('delete/', delete_show, name='delete_show'),
    path('list/', index, name='download_list'),
]